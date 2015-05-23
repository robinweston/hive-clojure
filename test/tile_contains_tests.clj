(ns tile-contains-tests
  (:require [midje.sweet :refer :all]))

(defrecord Tile [insect color])

(facts

  (fact "contains works with top level maps and records"
        (let [record-vec [(Tile. :ant :white)]]
          record-vec => (contains {:insect :ant :color :white})))

  (fact "contains doesn't work with nested maps and records"
        (let [record-vec [{:another-prop 3 :tile (Tile. :ant :white)}]]
          record-vec => (contains
                          (contains
                            {:another-prop 3
                             :tile {:insect :ant :color :white}})))))