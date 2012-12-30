(ns one-man-wiki.controllers
  (:require [one-man-wiki.views :as views])
  (:require [one-man-wiki.models :as models]))

(defn view-page [page-name]
  (let [content (or
                 (:content (models/get-page page-name))
                 "No content on this page yet.")]
    (views/view-page page-name content)))

(defn edit-page [page-name]
  (let [content (or
                 (:content (models/get-page page-name))
                 "No content on this page yet.")]
    (views/edit-page page-name content)))