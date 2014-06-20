(ns lamp.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [goog.dom :as dom]
            [goog.events :as events]
            [cljs.core.async :refer [<! put! chan]])
  (:import [goog.net Jsonp]
           [goog Uri]))

(def lb-url-read
  "/mongoRead.php")

(def lb-url-write
  "/mongoWrite.php")

(defn listen [el type]
  (let [out (chan)]
    (events/listen el type
      (fn [e] (put! out e)))
    out))

(defn error
  []
  (.log js/console "oops"))

(defn jsonp [uri]
  (let [out (chan)
        req (Jsonp. (Uri. uri))]
    (.send req nil (fn [res] (put! out (js->clj res))))
    out))

(defn render-db
  [result]
  (let [db (result "db")]
    (set!
      (.-innerHTML
        (dom/getElement "db"))
      (str "Database instance: " db))))

(defn render-read
  [result]
  (let [writers (result "writers")
        sorted (sort writers)
        filtered (filter #(> (val %) 0) writers)]
    (render-db result)
    (set!
      (.-innerHTML
        (dom/getElement "writers"))
      (apply str
        (for [[k v] filtered]
          (str "<li>" k ": " v "</li>"))))))

(defn render-write
  [result]
  (render-db result)
  (set!
    (.-innerHTML
      (dom/getElement "writers"))
    ""))

(defn init []
  (let [read (listen (dom/getElement "read") "click")
        write (listen (dom/getElement "write") "click")]
    (go (while true
          (<! read)
          (let [result (<! (jsonp lb-url-read))]
            (render-read result))))
    (go (while true
          (<! write)
          (let [result (<! (jsonp lb-url-write))]
            (render-write result))))))

(init)
