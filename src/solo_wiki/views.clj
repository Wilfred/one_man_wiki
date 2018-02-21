(ns solo-wiki.views
  (:use [hiccup.page :only [html5 include-css include-js]]
        [hiccup.form :only [text-area submit-button]]
        [ring.util.anti-forgery :only [anti-forgery-field]]))

(defn site-name []
  (or (System/getenv "WIKI_NAME")
      "SoloWiki"))

(defn base-page [title body]
  (html5
   [:head
    [:title title]
    (include-css "/css/style.css")]
   (into
    (into [:body] body)
    [(include-js "/js/jquery.min.js")
    (include-js "/js/shortcuts.js")])))

(defn nonexistent-page [name]
  (base-page
   (format "%s: No such page: %s" (site-name) name)
   [[:h1 name]
    [:pre "No page with this name exists yet."]
    [:a {:href (format "/%s/edit" name) :id "edit-page"} "Create"]]))

(defn view-page [name content]
  (base-page
   (format "%s: %s" (site-name) name)
   [[:h1 name]
    [:pre content]
    [:a {:href (format "/%s/edit" name) :id "edit-page"} "Edit"]]))

(defn edit-page [name content]
  (base-page
   (format "%s: Editing: %s" (site-name) name)
   [[:h1 (format "Editing: %s" name)]
    [:div {:class "editor"}
     [:form {:method "POST" :action (format "/%s/edit" name)}
      (text-area "content" content)
      (anti-forgery-field)
      (submit-button "Save page")
      [:span " "]
      [:a {:href (format "/%s" name) :id "cancel-edit"} "Cancel"]
      [:p "&lt;Tab&gt;&lt;Enter&gt; to save"]
      [:p "&lt;Escape&gt; to cancel"]]]]))
