(ns one-man-wiki.test.handler
  (:use clojure.test
        ring.mock.request  
        one-man-wiki.handler))

(deftest test-app
  (testing "redirect home page"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 302))))

  (testing "main route"
    (let [response (app (request :get "/Home"))]
      (is (= (:status response) 200))))
  
  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      ;; TODO: this should be 404
      (is (= (:status response) 200)))))
