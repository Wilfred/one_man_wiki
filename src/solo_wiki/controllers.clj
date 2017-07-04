(ns solo-wiki.controllers
  (:require [solo-wiki.views :as views]
            [solo-wiki.models :as models]
            [clojure.string :as str])
  (:use [hiccup.util :only [escape-html]]))

(defn linkify-wikiwords [content]
  (clojure.string/replace
   content
   #"(\s|\A)([A-Z][a-z]\w*?[A-Z](\w+)?)"
   "$1<a href=\"/$2\">$2</a>"))

(defn linkify-urls [content]
  (clojure.string/replace
   content
   #"(https?://\S+)"
   "<a href=\"$0\">$0</a>"))

(defn linkify-content
  [content]
  (-> content escape-html linkify-wikiwords linkify-urls))

(defn view-page [page-name version]
  (let [page (if version (models/get-revision page-name version)
                         (models/get-latest-revision page-name))]
    (if (empty? page)
      (views/nonexistent-page page-name)
      (views/view-page page-name
                       (linkify-content (:content page))))))

(defn edit-page [page-name]
  (let [content (or (:content (models/get-latest-revision page-name)) "")]
    (views/edit-page page-name content)))

(defn save-page [page-name content]
  ;; update the page
  (models/update-page page-name content)
  ;; now redirect to it
  {:status 302
   :headers {"Location" (format "/%s" page-name)}})
