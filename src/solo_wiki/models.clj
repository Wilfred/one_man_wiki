(ns solo-wiki.models
  (:use [clojure.java.jdbc])
  (:refer-clojure :exclude [resultset-seq]))

(def db
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "wiki.db"})

(defn get-latest-revision [page-name]
  (with-connection db
    (with-query-results rs
      ["SELECT * FROM pages WHERE name = ? ORDER BY created DESC LIMIT 1"
       page-name]
      (into {} rs))))

(defn get-revision [page-name version]
  (with-connection db
    (with-query-results rs
      ["SELECT * FROM pages WHERE name = ? ORDER BY created ASC LIMIT 1 OFFSET ?"
       page-name version]
      (into {} rs))))

(defn update-page [page-name content]
  (with-connection db
    (insert-records
     :pages
     {:name page-name
      :content content})))

(defn create-db []
  (with-connection db
    (create-table
     :pages
     [:name "varchar(255)"]
     [:content :text]
     [:created :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
  (update-page "Home" "hello world!"))
