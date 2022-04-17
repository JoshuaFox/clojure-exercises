(ns file-util.json-to-clj
  (:require [clojure.java.io]
            [clojure.string :refer [includes?]]
            [clojure.data.json :as json] )
  (:import (java.io FileWriter)))

(def book-cat (json/read-str (slurp (str "../../book-catalog.json" )) ))

(defn keys-as-keywords [nested-map]
  (into {} (map (fn [[k v]]
      [(let [num (parse-long k)]
         (if num num
                 (keyword k)))   ;; TODO how do I assert here? (assert (not (includes? k " ")))
       (cond (map? v) (keys-as-keywords v)
             (sequential? v) (map #(keys-as-keywords %) v)
             :else v)])
    nested-map)))


;;(def edn (prn-str (keys-as-keywords book-cat)))
(let [writer (new FileWriter "../../book-catalog.edn")]
  (clojure.pprint/pprint (keys-as-keywords book-cat) writer))

