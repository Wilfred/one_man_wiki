(ns one-man-wiki.views
  (:use [hiccup.page :only [html5 include-css]]
        [hiccup.form :only [text-area submit-button]]))

(defn view-page [name content]
  (html5
   [:head
    [:title (format "Viewing: %s" name)]
    (include-css "/css/style.css")]
   [:body
    [:pre content]
    [:a {:href (format "/%s/edit" name)} "Edit"]]))

(defn edit-page [name content]
  (html5
   [:head
    [:title (format "Editing: %s" name)]
    (include-css "/css/style.css")]
   [:body
    [:div {:class "editor"}
     [:form {:method "POST"}
      (text-area "content" content)
      (submit-button "Save page")]]]))