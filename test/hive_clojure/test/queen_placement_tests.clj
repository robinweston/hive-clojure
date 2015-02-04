(ns hive-clojure.test.queen-placement-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.valid-move-generation :refer [valid-moves]])
  (:require [hive-clojure.test.helpers.hive-parser :refer [parse-test-hive-tiles]]))

(facts "about queen placement"
       (fact "White queen must be played by sixth turn"
             (let [white-tiles [{:color :white, :insect :ant}
                                {:color :white, :insect :queen}]
                   game-state {:turn-number   6
                               :played-tiles  (parse-test-hive-tiles "sixth-turn")
                               :tiles-in-hand {:white white-tiles}}
                   valid-moves (valid-moves game-state)]
               (count valid-moves) => 6
               valid-moves => (contains [
                                         {:color    :white
                                          :insect   :queen
                                          :position {:x 0, :y 2}}
                                         {:color    :white
                                          :insect   :queen
                                          :position {:x 1, :y 1}}
                                         {:color    :white
                                          :insect   :queen
                                          :position {:x 2, :y 0}}
                                         {:color    :white
                                          :insect   :queen
                                          :position {:x 5, :y 3}}
                                         {:color    :white
                                          :insect   :queen
                                          :position {:x 5, :y 5}}
                                         {:color    :white
                                          :insect   :queen
                                          :position {:x 4, :y 6}}
                                         ] :in-any-order)
               )))
