(ns one-man-wiki.controllers
  (:require [one-man-wiki.views :as views]
            [one-man-wiki.models :as models]
            [clojure.string :as str])
  (:use [hiccup.util :only [escape-html]]))

(defn linkify-wikiwords [content]
  (clojure.string/replace
   content
   #"[A-Z]\w+?[A-Z](\w+)?"
   "<a href=\"/$0\">$0</a>"))

(defn linkify-urls [content]
  (clojure.string/replace
   content
   #"(https?://\S+)"
   "<a href=\"$0\">$0</a>"))

(defn linkify-content
  [content]
  (-> content escape-html linkify-wikiwords linkify-urls))

(defn view-page [page-name]
  (let [content (or
                 (:content (models/get-page page-name))
                 "No content on this page yet.")]
    (views/view-page page-name (linkify-content content))))

(defn edit-page [page-name]
  (let [content (or
                 (:content (models/get-page page-name))
                 "No content on this page yet.")]
    (views/edit-page page-name content)))

(defn save-page [params]
  ;; TODO: can we do this destructuring in the handler?
  (let [page-name (:page-name params)
        content (:content params)]
    ;; update the page
    (models/set-page page-name content)
    ;; now redirect to it
    {:status 302
     :headers {"Location" (format "/%s" page-name)}}))