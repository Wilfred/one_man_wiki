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

(defn get-page
  ([name]
      (with-connection db
        (with-query-results rs
          ;; most recent version of this page
          ["SELECT * FROM pages WHERE name = ? ORDER BY created DESC LIMIT 1"
           name]
          (into {} rs))))
  ([name version]
     (with-connection db
       (with-query-results rs
         ["SELECT * FROM pages WHERE name = ? ORDER BY created ASC LIMIT 1 OFFSET ?"
          name version]
         (into {} rs)))))

(defn set-page [name content]
  (with-connection db
    (insert-records
     :pages
     {:name name
      :content content})))
