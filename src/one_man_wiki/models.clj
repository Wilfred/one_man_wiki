(ns one-man-wiki.models
  (:use [clojure.java.jdbc])
  (:refer-clojure :exclude [resultset-seq]))

(def db
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "wiki.db"})

(defn create-db []
  (with-connection db
    (create-table
     :pages
     [:name "varchar(255)"]
     [:content :text]
     [:created :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])))

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

(defn set-page [name content]
  (with-connection db
    (insert-records
     :pages
     {:name name
      :content content})))
