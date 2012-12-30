(ns one-man-wiki.views
  (:use [hiccup.page :only [html5]]
        [hiccup.form :only [text-area submit-button]]))

(defn view-page [name content]
  (html5
   [:head
    [:title (format "Viewing: %s" name)]]
   [:body
    [:pre content]
    [:a {:href (format "/%s/edit" name)} "Edit"]]))

(defn edit-page [name content]
  (html5
   [:head
    [:title (format "Editing: %s" name)]]
   [:body
    [:form {:method "POST"}
     (text-area "content" content)
     (submit-button "Save page")]]))