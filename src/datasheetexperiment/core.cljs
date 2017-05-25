(ns datasheetexperiment.core
    (:require [reagent.core :as reagent]))

;; bring in js component

(def react-datasheet (reagent/adapt-react-class (aget js/window "deps" "react-datasheet")))
(def grid (clj->js [[{:value 1} {:value 2}] [{:value 3} {:value 4}]]))

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "I want a datasheet below"]
   [react-datasheet {:data grid
                        :value-renderer (str "foo")
                        :on-change (str "foo")}]
   ])

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
