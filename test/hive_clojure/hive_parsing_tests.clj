(ns hive-clojure.hive-parsing-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.helpers.test-grid-parser :refer :all]))

(facts "Tiles are positioned correctly when parsing grid"
       (fact "Empty hive has no tiles"
             (let [tiles (parse-test-hive-tiles "empty")]
               (empty? tiles) => true))

       (fact "Single white queen is in correct position"
             (let [tiles (parse-test-hive-tiles "single-white-queen")]
               (println "Parsed tiles" tiles)
               (count tiles) => 1
               (-> tiles first :color) => :white
               (-> tiles first :insect) => :queen)))
