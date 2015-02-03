(ns hive-clojure.test.queen-placement-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.valid-move-generation :refer [valid-moves]])
  (:require [hive-clojure.test.helpers.hive-parser :refer [parse-test-hive-tiles]]))

(future-facts "about queen placement"
              (fact "White queen must be played by turn 5"
                    (let [white-tiles [{:color :white, :insect :ant}]
                          game-state {:turn-number   2
                                      :played-tiles  (parse-test-hive-tiles "two-horizontal-queens")
                                      :tiles-in-hand {:white white-tiles}}
                          valid-moves (valid-moves game-state)]
                      (count valid-moves) => 3
                      valid-moves => (contains [
                                                {:color    :white
                                                 :insect   :ant
                                                 :position {:x 1, :y 0}}
                                                {:color    :white
                                                 :insect   :ant
                                                 :position {:x 0, :y 1}}
                                                {:color    :white
                                                 :insect   :ant
                                                 :position {:x 0, :y 3}}
                                                ] :in-any-order)
                      )))
