(ns hive-clojure.test.queen-move-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.valid-move-generation :refer [valid-next-game-states]])
  (:require [hive-clojure.test.helpers.hive-parser :refer [parse-test-hive-tiles]]))

(future-facts "about queen movement"
       (fact "queen can be moved one space"
             (let [game-state {:turn-number   2
                               :played-tiles  (parse-test-hive-tiles "two-horizontal-queens")
                               :tiles-in-hand {:white []}}
                   valid-moves (valid-next-game-states game-state)]
               (count valid-moves) => 2
               valid-moves => (contains [
                                         {:color    :white
                                          :insect   :queen
                                          :position {:x 1, :y 2}}
                                         {:color    :white
                                          :insect   :queen
                                          :position {:x 3, :y 1}}
                                         ] :in-any-order)
               )))
