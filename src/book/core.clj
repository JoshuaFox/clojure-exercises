(ns book.core)
(ns book.core
  (:require [clojure.data.json :as json]
            [file-util.core]))

(def catalog (json/read-str (file-util.core/read-file "book-catalog.json")))

(defn search [in-title catalog]
  "   - Parameter query is a substring of the title;
      - Parameter catalog is a map {:books list-of-books :authors map-of-author-ids-to-author-name
        A book in the list in the catalog takes the form {:title \"Moby Dick\" :author-id 123}.
        The map of authors in the catalog takes the form  {123 \"Herman Melville\" 567 \"Fyodor Dostoevsky\" }
      - Returns list of books whose title contain the query.
      - An example of a book in that return-value list  is
        {:title \"Moby Dick\" :author \"Herman Melville\"}
      "
  (let [books (filter (fn [bk] (clojure.string/includes? (get bk "title") in-title))
                      (get catalog "books"))
        authors (get catalog "authors")
        books-with-or-without-author (for [bk books]
                                       {:title (get bk "title")
                                        :author (get authors (str (get bk "author-id")))})
        books-with-author (for [bk books-with-or-without-author :when (bk :author)] bk)  ]

    books-with-author))

