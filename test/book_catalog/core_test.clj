(ns book-catalog.core-test
  (:require [clojure.test :refer :all]
            [book-catalog.core :refer :all]))

(deftest book-test
  (testing "Search for books by substring of title."
    ;(is (not-any? #(= "Herman Melville" (:author %)) (search "not a book" catalog))
    ;    "The string \"not a book\" is not in any title ")
    ;(is (= (count (search "not a book" catalog)) 0) "The string \"not a book\" is not in any title ")
    (is (= (count (search "and" catalog)) 3) "There are three titles with \"and\" in them")
    ;(is (some #(= "William Shakespeare" (:author %)) (search "and" catalog)) "Shakespeare wrote a play with \"and\" in the title")
    ;(is (not-any? #(= "Herman Melville" (:author %)) (search "and" catalog)) "Melville did not write a book with \"and\" in the title")
    ;(is (not-any? #(= "Of Mice and Men" (:title %)) (search "and" catalog)) "We exclude books with missing author")
    ))
