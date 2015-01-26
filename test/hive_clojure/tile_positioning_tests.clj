(ns hive-clojure.tile-positioning-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.helpers.test-grid-parser :refer :all]))

(facts "Tiles are positioned correctly when parsing grid"
       (fact "Empty grid has no tiles"
             (let [grid (parse-test-grid "empty")]
               (first (first grid)) => :empty
               )))
