(ns hive-clojure.hive-parsing-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.helpers.test-hive-parser :refer :all]))

(facts "Tiles are positioned correctly when parsing grid"
       (fact "Empty hive has no tiles"
             (let [tiles (parse-test-hive-tiles "empty")]
               (empty? tiles) => true))

       (fact "Single white queen is in correct position"
             (let [tiles (parse-test-hive-tiles "single-white-queen")]
               (count tiles) => 1
               tiles => (contains {:color    :white
                                   :insect   :queen
                                   :position {:x 1, :y 2}})))

       (fact "Two horizontal queens in correct positions"
             (let [tiles (parse-test-hive-tiles "two-horizontal-queens")]
               (count tiles) => 2
               tiles => (contains {:color    :white
                                   :insect   :queen
                                   :position {:x 1, :y 2}})
               tiles => (contains {:color    :black
                                   :insect   :queen
                                   :position {:x 2, :y 3}})
               )))
