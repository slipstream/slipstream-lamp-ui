(defproject lamp "1.1"
  :description "display request statistics for LAMP"
  :url "https://github.com/slipstream/slipstream-lamp-ui"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2173"]
                 [org.clojure/core.async "0.1.256.0-1bf8cf-alpha"]]

  :plugins [[lein-cljsbuild "1.0.2"]]

  :source-paths ["src"]

  :cljsbuild { 
    :builds [{:id "lamp"
              :source-paths ["src"]
              :compiler {
                :output-to "lamp.js"
                :output-dir "out"
                :optimizations :none
                :source-map true}}]})
