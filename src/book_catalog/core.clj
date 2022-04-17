(ns book-catalog.core
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.string :refer [includes?]])
  (:import (java.io IOException PushbackReader)))


(defn load-edn
  "Load edn from an io/reader source (filename or io/resource)."
  [source]
  (try
    (with-open [r (io/reader source)]
      (edn/read (PushbackReader. r)))
    (catch IOException e
      (throw (Exception. (str "Couldn't open " (.getMessage e)))))
    (catch RuntimeException e
      (throw (Exception. (str "Error parsing edn file " (.getMessage e)))))))

(def catalog (load-edn "./book-catalog.edn"))

(defn search [in-title catalog]
  "   - Parameter query is a substring to search for in  the title;
      - Parameter catalog is a map {\"books\" list-of-books \"authors\" map-of-author-ids-to-author-name
        A book in the list in the catalog takes the form {:title \"Moby Dick\" \"author-id\" 123}.
        The map of authors in the catalog takes the form  {123 \"Herman Melville\" 567 \"Fyodor Dostoevsky\" }
      - Returns list of books whose title contain the query.
      - An example of a book in that return-value list  is
        {:title \"Moby Dick\" :author \"Herman Melville\"}
      "
  (let [

        matching-books (filter #(includes? (% :title) in-title) (catalog :books))
        {authors :authors} catalog
        books-with-or-without-author (map (fn [bk] {:title (get bk :title) :author-id (get authors (bk :author-id))})
                                          matching-books)
        books-with-author (filter #(% :author-id) books-with-or-without-author)]

    books-with-author

    ))

