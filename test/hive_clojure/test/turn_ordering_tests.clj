(ns hive-clojure.test.turn-ordering-tests
  (:require [midje.sweet :refer :all]
            [hive-clojure.valid-move-generation :refer [valid-next-game-states]]
            [hive-clojure.turn-ordering :refer [current-player]]))

(facts "about turn ordering"
       (fact "White plays first"
             (let [game-state {:turn-number 0}]
               (current-player game-state) => :white))

       (fact "Black plays second"
             (let [game-state {:turn-number 1}]
               (current-player game-state) => :black))

       (fact "White plays third"
             (let [game-state {:turn-number 2}]
               (current-player game-state) => :white))

       (fact "Turn number increases"
             (let [white-tiles [{:color :white, :insect :ant}]
                   game-state {:turn-number   0
                               :played-tiles  []
                               :tiles-in-hand {:white white-tiles}}
                   next-game-states (valid-next-game-states game-state)]
               next-game-states => (has every? #(= (:turn-number %) 1))
               )))