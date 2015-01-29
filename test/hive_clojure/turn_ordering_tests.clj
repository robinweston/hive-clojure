(ns hive-clojure.turn-ordering-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.turn-ordering :refer [next-to-play]]))

(facts "about turn ordering"
       (fact "White plays first"
             (let [game-state {:turn-number 0}]
               (next-to-play game-state) => :white
               ))

       (fact "Black plays second"
             (let [game-state {:turn-number 1}]
               (next-to-play game-state) => :black
               ))

       (fact "White plays thirds"
             (let [game-state {:turn-number 2}]
               (next-to-play game-state) => :white
               )))