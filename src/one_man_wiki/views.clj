(ns one-man-wiki.views
  (:use [hiccup.page :only [html5 include-css include-js]]
        [hiccup.form :only [text-area submit-button]]
        [ring.util.anti-forgery :only [anti-forgery-field]]))

(defn view-page [name content]
  (html5
   [:head
    [:title (format "OneManWiki Viewing: %s" name)]
    (include-css "/css/style.css")]
   [:body
    [:h1 name]
    [:pre content]
    [:a {:href (format "/%s/edit" name) :id "edit-page"} "Edit"]
    (include-js "//ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js")
    (include-js "/js/shortcuts.js")]))

(defn edit-page [name content]
  (html5
   [:head
    [:title (format "OneManWiki Editing: %s" name)]
    (include-css "/css/style.css")]
   [:body
    [:h1 (format "Editing: %s" name)]
    [:div {:class "editor"}
     [:form {:method "POST" :action (format "/%s/edit" name)}
      (text-area "content" content)
      (anti-forgery-field)
      (submit-button "Save page")]]
    (include-js "//ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js")
    (include-js "/js/shortcuts.js")]))