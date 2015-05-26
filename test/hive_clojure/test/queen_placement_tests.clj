(ns hive-clojure.test.queen-placement-tests
  (:require [midje.sweet :refer :all]
            [hive-clojure.valid-move-generation :refer [valid-next-game-states]]
            [hive-clojure.test.helpers.hive-parser :refer [parse-test-hive-tiles]]
            [hive-clojure.test.helpers.game-state-helper :refer [last-moves]]
            [hive-clojure.domain :refer [map->Board]]))

(facts "about queen placement"
       (fact "White queen must be played by sixth turn"
             (let [white-tiles [{:color :white, :insect :ant}
                                {:color :white, :insect :queen}]
                   board (map->Board {:turn-number   6
                                :played-tiles  (parse-test-hive-tiles "sixth-turn")
                                :tiles-in-hand {:white white-tiles :black []}})
                   next-boards (valid-next-game-states board)
                   possible-moves (last-moves next-boards)]
               (count possible-moves) => 3
               possible-moves => (contains {:color :white, :insect :queen, :position {:x 0, :y 2}})
               possible-moves => (contains {:color :white, :insect :queen, :position {:x 1, :y 1}})
               possible-moves => (contains {:color :white, :insect :queen, :position {:x 2, :y 0}})))

       (fact "Black queen must be played by seventh turn"
             (let [black-tiles [{:color :black, :insect :ant}
                                {:color :black, :insect :queen}]
                   game-state {:turn-number   7
                               :played-tiles  (parse-test-hive-tiles "seventh-turn")
                               :tiles-in-hand {:black black-tiles, :white []}}
                   next-game-states (valid-next-game-states game-state)
                   possible-moves (last-moves next-game-states)]
               (count possible-moves) => 3
               possible-moves => (contains {:color :black, :insect :queen, :position {:x 1, :y 7}})
               possible-moves => (contains {:color :black, :insect :queen, :position {:x 2, :y 6}})
               possible-moves => (contains {:color :black, :insect :queen, :position {:x 0, :y 6}}))))
