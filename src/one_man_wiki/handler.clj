(ns one-man-wiki.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [one-man-wiki.views :as views]))

(defroutes app-routes
  (GET "/" [] (views/index-page))
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
