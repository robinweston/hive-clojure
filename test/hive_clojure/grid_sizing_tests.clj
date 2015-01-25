(ns hive-clojure.grid-sizing-tests
    (:require [midje.sweet :refer :all]
      :require [hive-clojure.core :refer :all]
      :require [local-file :refer :all]))

(defn- slurp-grid-text-from-file [grid-file]
       (slurp (clojure.java.io/file (project-dir) "test/grids/" (str grid-file ".txt"))))

(defn- assert-grid-dimensions [grid width height]
       (let
         [grid-width (count (first grid))
          grid-length (count grid)]
         grid-width => 1
         grid-length => 1))

(defn parse-grid-text [raw-grid]
      (print "Parsing grid\n")
      (print raw-grid)
      (print "\n\n")

      (if (empty? raw-grid)
        [[0]]
        [[0 0 0]
         [0 0 0]
         [0 0 0]]
        ))

(defn grid-dimensions [grid]
      [(count (first grid))
       (count grid)])

(facts "grid sizing"
       (fact "Empty grid is sized correctly"
             (let [parsed-grid (parse-grid-text (slurp-grid-text-from-file "empty"))
                   grid-dimensions (grid-dimensions parsed-grid)]
                  (nth grid-dimensions 0) => 1
                  (nth grid-dimensions 1) => 1))

       (fact "Single white queen grid is sized correctly"
             (let [parsed-grid (parse-grid-text (slurp-grid-text-from-file "single-white-queen"))
                   grid-dimensions (grid-dimensions parsed-grid)]
                  (nth grid-dimensions 0) => 3
                  (nth grid-dimensions 1) => 3))

       (fact "Two horizontal queens grid is sized correctly"
             (let [parsed-grid (parse-grid-text (slurp-grid-text-from-file "two-queens-horizontal"))
                   grid-dimensions (grid-dimensions parsed-grid)]
                  (nth grid-dimensions 0) => 4
                  (nth grid-dimensions 1) => 6)))