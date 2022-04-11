(defproject clojure-koans "0.5.2-SNAPSHOT"
  :description "The Clojure koans."
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [koan-engine "0.2.5"]
                 [org.clojure/math.numeric-tower "0.0.5"]
                 ]
  :dev-dependencies [[lein-koan "0.1.5"]]
  :profiles {:dev {:dependencies [[lein-koan "0.1.5"]]}}
  :repl-options {:init-ns koan-engine.runner
                 :init    ^:displace (do (use '[koan-engine.core]))}
  :plugins [[lein-koan "0.1.5"] [lein-cljfmt "0.8.0"]]

  :main koan-engine.runner/exec)
