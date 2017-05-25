(ns datasheetexperiment.core
    (:require [reagent.core :as reagent]))

;; bring in js component

(def react-datasheet (reagent/adapt-react-class (aget js/window "deps" "react-datasheet")))

(def simple-underlying-data (reagent/atom [[{:value 1} {:value 2}] [{:value 3} {:value 4}]]))

(defn updater [old-data row column new-value]
  (assoc-in old-data [row column :value] (js/parseInt new-value)))


(def complex-underlying-data (reagent/atom
                              [
                               [{:value " " :readOnly true}
                                {:value "A" :readOnly true}
                                {:value "B" :readOnly true}]
                               [{:value "1" :readOnly true}
                                {:value 10}
                                {:value 20}]
                               [{:value "2" :readOnly true}
                                {:value 30}
                                {:value 40}]]))


;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "Datasheets from JS!"]
   [react-datasheet {:data (clj->js @simple-underlying-data)
                        :value-renderer #(.-value %)
                     :on-change #(swap! simple-underlying-data updater %2 %3 %4)}]
   [react-datasheet {:data (clj->js @complex-underlying-data)
                     :value-renderer #(.-value %)
                     :on-change #(swap! complex-underlying-data updater %2 %3 %4)}]
   ])

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
