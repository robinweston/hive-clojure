(ns hive-clojure.test.second-move-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.valid-move-generation :refer [valid-next-game-states]])
  (:require [hive-clojure.test.helpers.hive-parser :refer [parse-test-hive-tiles]]))

(facts "about second moves"
       (fact "White second move with one tile available has correct moves"
             (let [white-tiles [{:color :white, :insect :ant}]
                   game-state {:turn-number   2
                               :played-tiles  (parse-test-hive-tiles "two-horizontal-queens")
                               :tiles-in-hand {:white white-tiles :black []}}
                   next-game-states (valid-next-game-states game-state)]
               (count next-game-states) => 3
               next-game-states => (contains {:played-tiles  [{:color :white, :insect :queen, :position {:x 1, :y 2}}
                                                              {:color :black, :insect :queen, :position {:x 2, :y 3}}
                                                              {:color :white, :insect :ant, :position {:x 1, :y 0}}],
                                              :tiles-in-hand {:white [] :black []},
                                              :turn-number   3})
               next-game-states => (contains {:played-tiles  [{:color :white, :insect :queen, :position {:x 1, :y 2}}
                                                              {:color :black, :insect :queen, :position {:x 2, :y 3}}
                                                              {:color :white, :insect :ant, :position {:x 0, :y 1}}],
                                              :tiles-in-hand {:white [] :black []},
                                              :turn-number   3})
               next-game-states => (contains {:played-tiles  [{:color :white, :insect :queen, :position {:x 1, :y 2}}
                                                              {:color :black, :insect :queen, :position {:x 2, :y 3}}
                                                              {:color :white, :insect :ant, :position {:x 0, :y 3}}],
                                              :tiles-in-hand {:white [] :black []},
                                              :turn-number   3})
               ))

       (fact "Black second move with one tile available has correct moves"
             (let [black-tiles [{:color :black, :insect :ant}]
                   game-state {:turn-number   3
                               :played-tiles  (parse-test-hive-tiles "two-queens-and-a-white-spider")
                               :tiles-in-hand {:black black-tiles :white []}}
                   valid-moves (valid-next-game-states game-state)]
               (count valid-moves) => 3
               valid-moves => (contains {:played-tiles  [{:color :white, :insect :queen, :position {:x 2, :y 2}}
                                                          {:color :black, :insect :queen, :position {:x 3, :y 3}}
                                                          {:color :white, :insect :spider, :position {:x 1, :y 3}}
                                                          {:color :black, :insect :ant, :position {:x 3, :y 5}}],
                                          :tiles-in-hand {:black [] :white []},
                                          :turn-number   4})
               valid-moves => (contains {:played-tiles  [{:color :white, :insect :queen, :position {:x 2, :y 2}}
                                                         {:color :black, :insect :queen, :position {:x 3, :y 3}}
                                                         {:color :white, :insect :spider, :position {:x 1, :y 3}}
                                                         {:color :black, :insect :ant, :position {:x 4, :y 2}}],
                                         :tiles-in-hand {:black [] :white []},
                                         :turn-number   4})
               valid-moves => (contains {:played-tiles  [{:color :white, :insect :queen, :position {:x 2, :y 2}}
                                                         {:color :black, :insect :queen, :position {:x 3, :y 3}}
                                                         {:color :white, :insect :spider, :position {:x 1, :y 3}}
                                                         {:color :black, :insect :ant, :position {:x 4, :y 4}}],
                                         :tiles-in-hand {:black [] :white []},
                                         :turn-number   4}))))
