(ns datasheetexperiment.core
    (:require [reagent.core :as reagent]))

;; bring in js component

(def react-datasheet (reagent/adapt-react-class (aget js/window "deps" "react-datasheet")))

(def underlying-data (reagent/atom [[{:value 1} {:value 2}] [{:value 3} {:value 4}]]))

(defn updater [old-data row column new-value]
  (assoc-in old-data [row column :value] (js/parseInt new-value)))

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "Datasheet below is editable (integers only)!"]
   [react-datasheet {:data (clj->js @underlying-data)
                        :value-renderer #(.-value %)
                     :on-change #(swap! underlying-data updater %2 %3 %4)}]
   ])

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
