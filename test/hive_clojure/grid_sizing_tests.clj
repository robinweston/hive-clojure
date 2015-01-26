(ns hive-clojure.grid-sizing-tests
  (:require [midje.sweet :refer :all]) ; TODO: namespace tests
  (:require [hive-clojure.core :refer [parse-grid-text]])
  (:require [hive-clojure.helpers.test-grid-parser :refer :all]))

(defn- retrieve-grid-dimensions [grid]
  (vector (count (first grid)) (count grid)))

(facts "grid sizing"
       (fact "Empty grid is sized correctly"
             (let [parsed-grid (parse-test-grid "empty")
                   grid-dimensions (retrieve-grid-dimensions parsed-grid)]
               (nth grid-dimensions 0) => 1
               (nth grid-dimensions 1) => 1))

       (fact "Single white queen grid is sized correctly"
             (let [parsed-grid (parse-test-grid "single-white-queen")
                   grid-dimensions (retrieve-grid-dimensions parsed-grid)]
               (nth grid-dimensions 0) => 3
               (nth grid-dimensions 1) => 3))

       (fact "Two horizontal queens grid is sized correctly"
             (let [parsed-grid (parse-test-grid "two-queens-horizontal")
                   grid-dimensions (retrieve-grid-dimensions parsed-grid)]
               (nth grid-dimensions 0) => 4
               (nth grid-dimensions 1) => 6)))