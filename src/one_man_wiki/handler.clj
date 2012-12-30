(ns one-man-wiki.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [one-man-wiki.views :as views]
            [one-man-wiki.controllers :as controllers]))

(defroutes app-routes
  (GET "/:page-name/edit" [page-name] (controllers/edit-page page-name))
  (GET "/:page-name" [page-name] (controllers/view-page page-name))
  (GET "/" _ {:status 302
              :headers {"Location" "/Home"}})
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
