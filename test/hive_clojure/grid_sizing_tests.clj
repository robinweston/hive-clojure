(ns hive-clojure.grid-sizing-tests
  (:require [midje.sweet :refer :all]
            [hive-clojure.core :refer :all]))

(defn parse-grid [raw-grid] [[0]])

(facts "grid sizing"
       (fact "Empty grid is sized correctly"
             (let [parsed-grid (parse-grid "")
                   grid-width (count (first parsed-grid))
                   grid-length (count parsed-grid)]
                  grid-width => 1
                  grid-length => 1)))