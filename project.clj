(defproject one-man-wiki "1.0-SNAPSHOT"
  :description "A minimal wiki designed to scale down"
  :url "https://github.com/Wilfred/one_man_wiki"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring-anti-forgery "0.2.1"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [hiccup "1.0.2"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler one-man-wiki.handler/app}
  :min-lein-version "2.0.0"
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
