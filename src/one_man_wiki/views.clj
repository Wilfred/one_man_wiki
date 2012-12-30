(ns one-man-wiki.views
  (:use [hiccup core page])
  (:require [one-man-wiki.models :as models]))

(defn view-page [page-name]
  (let [content (:content (models/get-page page-name))]
    (html5
     [:head
      [:title "Hello World"]]
     [:body
      [:pre
       (if (nil? content)
         "No content here yet."
         content)]])))
