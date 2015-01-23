(ns hive-clojure.grid-sizing-tests
  (:require [midje.sweet :refer :all]
            [hive-clojure.core :refer :all]))

(def single-white-queen  " ___\n/ q \\\n\\___/")                                   ; read file

(defn parse-grid [raw-grid]
      (print "Parsing grid\n")
      (print raw-grid)
      (if (empty? raw-grid)
        [[0]]
        [[0 0 0]
         [0 0 0]
         [0 0 0]]
        )                  )

(facts "grid sizing"
       (fact "Empty grid is sized correctly"
             (let [parsed-grid (parse-grid "")
                   grid-width (count (first parsed-grid))
                   grid-length (count parsed-grid)]
                  grid-width => 1
                  grid-length => 1))
       (fact "Single white queen grid is sized correctly"
             (let [parsed-grid (parse-grid single-white-queen)
                   grid-width (count (first parsed-grid))
                   grid-length (count parsed-grid)]
                  grid-width => 3
                  grid-length => 3)))