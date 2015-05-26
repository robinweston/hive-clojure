(ns hive-clojure.test.second-move-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.valid-move-generation :refer [valid-next-game-states]])
  (:require [hive-clojure.test.helpers.hive-parser :refer [parse-test-hive-tiles]])
  (:require [hive-clojure.test.helpers.game-state-helper :refer [last-moves]]))

(facts "about second moves"
       (fact "White second move with one tile available has correct moves"
             (let [white-tiles [{:color :white, :insect :ant}]
                   game-state {:turn-number   2
                               :played-tiles  (parse-test-hive-tiles "two-horizontal-queens")
                               :tiles-in-hand {:white white-tiles :black []}}
                   next-game-states (valid-next-game-states game-state)
                   possible-moves (last-moves next-game-states)]
               (count possible-moves) => 3
               possible-moves => (contains {:color :white, :insect :ant, :position {:x 1, :y 0}})
               possible-moves => (contains {:color :white, :insect :ant, :position {:x 0, :y 1}})
               possible-moves => (contains {:color :white, :insect :ant, :position {:x 0, :y 3}})))

       (fact "Black second move with one tile available has correct moves"
             (let [black-tiles [{:color :black, :insect :ant}]
                   game-state {:turn-number   3
                               :played-tiles  (parse-test-hive-tiles "two-queens-and-a-white-spider")
                               :tiles-in-hand {:black black-tiles :white []}}
                   next-game-states (valid-next-game-states game-state)
                   possible-moves (last-moves next-game-states)]
               (count possible-moves) => 3
               possible-moves => (contains {:color :black, :insect :ant, :position {:x 3, :y 5}})
               possible-moves => (contains {:color :black, :insect :ant, :position {:x 4, :y 2}})
               possible-moves => (contains {:color :black, :insect :ant, :position {:x 4, :y 4}}))))
