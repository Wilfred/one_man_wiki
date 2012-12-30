(ns one-man-wiki.views
  (:use [hiccup core page form])
  (:require [one-man-wiki.models :as models]))

(defn view-page [page-name]
  (let [content (:content (models/get-page page-name))]
    (html5
     [:head
      [:title (format "Viewing: %s" page-name)]]
     [:body
      [:pre
       (if (nil? content)
         "No content here yet."
         content)]])))

(defn edit-page [page-name]
  (let [content (or
                 (:content (models/get-page page-name))
                 "No content on this page yet.")]
    (html5
     [:head
      [:title (format "Editing: %s" page-name)]]
     [:body
      [:form
       (text-area "content" content)
       (submit-button "Save page")]])))