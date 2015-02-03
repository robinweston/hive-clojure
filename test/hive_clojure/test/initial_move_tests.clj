(ns hive-clojure.test.initial-move-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.valid-move-generation :refer [valid-moves]])
  (:require [hive-clojure.test.helpers.hive-parser :refer [parse-test-hive-tiles]]))

(facts "about initial moves"
       (fact "At start of game and with only one tile white has only one valid move"
             (let [white-tiles [{:color :white, :insect :ant}]
                   game-state {:turn-number   0
                               :played-tiles  []
                               :tiles-in-hand {:white white-tiles}}
                   valid-moves (valid-moves game-state)]
               valid-moves => (just [{:color    :white
                                      :insect   :ant
                                      :position {:x 0, :y 0}}
                                     ])
               ))

       (fact "At start of game and with two tiles white has two valid moves"
             (let [white-tiles [{:color :white, :insect :ant}
                                {:color :white, :insect :queen}]
                   game-state {:turn-number   0
                               :played-tiles  []
                               :tiles-in-hand {:white white-tiles}}
                   valid-moves (valid-moves game-state)]
               valid-moves => (just [{:color    :white
                                      :insect   :ant
                                      :position {:x 0, :y 0}}
                                     {:color    :white
                                      :insect   :queen
                                      :position {:x 0, :y 0}}
                                     ])
               ))

       (fact "At start of game and with two identical tiles duplicate moves are not generated"
             (let [white-tiles [{:color :white, :insect :ant}
                                {:color :white, :insect :ant}]
                   game-state {:turn-number   0
                               :played-tiles  []
                               :tiles-in-hand {:white white-tiles :black []}}
                   valid-moves (valid-moves game-state)]
               valid-moves => (just [{:color    :white
                                      :insect   :ant
                                      :position {:x 0, :y 0}}
                                     ])
               ))

       (fact "At start of game and with one tile black has correct valid moves"
             (let [black-tiles [{:color :black, :insect :ant}]
                   game-state {:turn-number   1
                               :played-tiles  (parse-test-hive-tiles "single-white-queen")
                               :tiles-in-hand {:black black-tiles :white []}}
                   valid-moves (valid-moves game-state)]
               (count valid-moves) => 6
               valid-moves => (contains [{:color    :black
                                          :insect   :ant
                                          :position {:x 1, :y 0}}
                                         {:color    :black
                                          :insect   :ant
                                          :position {:x 0, :y 1}}
                                         {:color    :black
                                          :insect   :ant
                                          :position {:x 2, :y 1}}
                                         {:color    :black
                                          :insect   :ant
                                          :position {:x 0, :y 3}}
                                         {:color    :black
                                          :insect   :ant
                                          :position {:x 1, :y 4}}
                                         {:color    :black
                                          :insect   :ant
                                          :position {:x 2, :y 3}}
                                         ] :in-any-order)
               ))


       (fact "At start of game and with three tiles (but two indentical) black has correct valid moves"
             (let [black-tiles [{:color :black, :insect :ant}
                                {:color :black, :insect :ant}
                                {:color :black, :insect :queen}]
                   game-state {:turn-number   1
                               :played-tiles  (parse-test-hive-tiles "single-white-queen")
                               :tiles-in-hand {:white [] :black black-tiles}}
                   valid-moves (valid-moves game-state)]
               (count valid-moves) => 12
               valid-moves => (contains [{:color    :black
                                          :insect   :ant
                                          :position {:x 1, :y 0}}
                                         {:color    :black
                                          :insect   :ant
                                          :position {:x 0, :y 1}}
                                         {:color    :black
                                          :insect   :ant
                                          :position {:x 2, :y 1}}
                                         {:color    :black
                                          :insect   :ant
                                          :position {:x 0, :y 3}}
                                         {:color    :black
                                          :insect   :ant
                                          :position {:x 1, :y 4}}
                                         {:color    :black
                                          :insect   :ant
                                          :position {:x 2, :y 3}}
                                         {:color    :black
                                          :insect   :queen
                                          :position {:x 1, :y 0}}
                                         {:color    :black
                                          :insect   :queen
                                          :position {:x 0, :y 1}}
                                         {:color    :black
                                          :insect   :queen
                                          :position {:x 2, :y 1}}
                                         {:color    :black
                                          :insect   :queen
                                          :position {:x 0, :y 3}}
                                         {:color    :black
                                          :insect   :queen
                                          :position {:x 1, :y 4}}
                                         {:color    :black
                                          :insect   :queen
                                          :position {:x 2, :y 3}}
                                         ] :in-any-order)
               ))

       )
