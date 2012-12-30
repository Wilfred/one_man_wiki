(ns one-man-wiki.models
  (:use [clojure.java.jdbc])
  (:refer-clojure :exclude [resultset-seq]))

(def db
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "wiki.db"})

(defn create-db []
  (with-connection db
    (create-table :pages
                  [:name :text]
                  [:content :text])))

(defn get-page [name]
  (with-connection db
    (with-query-results rs ["SELECT * FROM pages WHERE name = ?" name]
      (into {} rs))))

(defn set-page [name content]
  (with-connection db
    (insert-records
     :pages
     {:name name
      :content content})))