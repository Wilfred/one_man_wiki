(ns one-man-wiki.handler
  (:use compojure.core
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [one-man-wiki.views :as views]
            [one-man-wiki.controllers :as controllers]))

(defroutes app-routes
  (GET "/:page-name/edit" [page-name] (controllers/edit-page page-name))
  (POST "/:page-name/edit"
        {{page-name :page-name content :content} :params}
        (controllers/save-page page-name content))
  (GET "/:page-name" [page-name] (controllers/view-page page-name))
  (route/resources "/")
  (GET "/" _ {:status 302
              :headers {"Location" "/Home"}})
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (wrap-base-url)))