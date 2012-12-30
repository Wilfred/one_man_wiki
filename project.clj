(defproject one-man-wiki "0.1.0-SNAPSHOT"
  :description "A minimal wiki designed to scale down"
  :url "https://github.com/Wilfred/one_man_wiki"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.3"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [org.xerial/sqlite-jdbc "3.7.2"]]
  :plugins [[lein-ring "0.7.5"]]
  :ring {:handler one-man-wiki.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
