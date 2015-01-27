(ns hive-clojure.tile-positioning-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.helpers.test-grid-parser :refer :all]))

(defn cell-at [grid x y]
         ((grid y) x))

(facts "Tiles are positioned correctly when parsing grid"
       (fact "Empty grid has no tiles"
             (let [grid (parse-test-grid "empty")]
               (cell-at grid 0 0) => :empty))

       (fact "Single white queen is in correct position"
             (let [grid (parse-test-grid "single-white-queen")]
               (:color (cell-at grid 1 2)) => :white
               (:insect (cell-at grid 1 2)) => :queen))) ; TODO: assert all others empty
