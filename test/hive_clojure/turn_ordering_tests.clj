(ns hive-clojure.turn-ordering-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.turn-ordering :refer [current-player]]))

(facts "about turn ordering"
       (fact "White plays first"
             (let [game-state {:turn-number 0}]
               (current-player game-state) => :white
               ))

       (fact "Black plays second"
             (let [game-state {:turn-number 1}]
               (current-player game-state) => :black
               ))

       (fact "White plays third"
             (let [game-state {:turn-number 2}]
               (current-player game-state) => :white
               )))