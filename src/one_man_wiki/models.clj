(ns one-man-wiki.models
  (:use [clojure.java.jdbc]))

(def page-default "There's no content here yet.")

(def db
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "wiki.db"})

(defn create-db []
  (with-connection db
    (create-table :pages
                  [:name :text]
                  [:content :text])))