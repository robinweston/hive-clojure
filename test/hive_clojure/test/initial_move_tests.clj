(ns hive-clojure.test.initial-move-tests
  (:require [midje.sweet :refer :all]
            [hive-clojure.valid-move-generation :refer [valid-next-game-states]]
            [hive-clojure.test.helpers.hive-parser :refer [parse-test-hive-tiles]]
            [hive-clojure.test.helpers.game-state-helper :refer [last-moves]])
  (:import (hive_clojure.domain Board)))

(facts "about initial moves"
       (fact "At start of game and with only one tile white has only one valid move"
             (let [white-tiles [{:color :white, :insect :ant}]
                   board (Board. [] {:white white-tiles} 0)
                   next-boards (valid-next-game-states board)
                   possible-moves (last-moves next-boards)]
               (count possible-moves) => 1
               possible-moves => (just {:color    :white
                                        :insect   :ant
                                        :position {:x 0, :y 0}}
                                       )))

       (fact "At start of game and with two tiles white has two valid moves"
             (let [white-tiles [{:color :white, :insect :ant}
                                {:color :white, :insect :queen}]
                   board (Board. [] {:white white-tiles} 0)
                   next-boards (valid-next-game-states board)
                   possible-moves (last-moves next-boards)]
               (count possible-moves) => 2
               possible-moves => (contains {:color    :white
                                            :insect   :ant
                                            :position {:x 0 :y 0}})
               possible-moves => (contains {:color    :white
                                            :insect   :queen
                                            :position {:x 0 :y 0}})))

       (fact "At start of game and with two identical tiles duplicate moves are not generated"
             (let [white-tiles [{:color :white, :insect :ant}
                                {:color :white, :insect :ant}]
                   board (Board. [] {:white white-tiles} 0)
                   next-boards (valid-next-game-states board)
                   possible-moves (last-moves next-boards)]
               (count possible-moves) => 1
               possible-moves => (just {
                                        :color    :white
                                        :insect   :ant
                                        :position {:x 0 :y 0}})))

       (fact "At start of game and with one tile black has correct valid moves"
             (let [black-tiles [{:color :black, :insect :ant}]
                   board (Board. (parse-test-hive-tiles "single-white-queen") {:black black-tiles} 1)
                   next-boards (valid-next-game-states board)
                   possible-moves (last-moves next-boards)]
               (count possible-moves) => 6
               possible-moves => (contains {:color    :black
                                            :insect   :ant
                                            :position {:x 1, :y 0}})
               possible-moves => (contains {:color    :black
                                            :insect   :ant
                                            :position {:x 0, :y 1}})
               possible-moves => (contains {:color    :black
                                            :insect   :ant
                                            :position {:x 2, :y 1}})
               possible-moves => (contains {:color    :black
                                            :insect   :ant
                                            :position {:x 0, :y 3}})
               possible-moves => (contains {:color    :black
                                            :insect   :ant
                                            :position {:x 1, :y 4}})
               possible-moves => (contains {:color    :black
                                            :insect   :ant
                                            :position {:x 2, :y 3}})))

       (fact "At start of game and with three tiles (but two indentical) black has correct valid moves"
             (let [black-tiles [{:color :black, :insect :ant}
                                {:color :black, :insect :ant}
                                {:color :black, :insect :queen}]
                   board (Board. (parse-test-hive-tiles "single-white-queen") {:black black-tiles} 1)
                   next-boards (valid-next-game-states board)
                   possible-moves (last-moves next-boards)]
               (count possible-moves) => 12
               possible-moves => (contains {:color    :black
                                            :insect   :ant
                                            :position {:x 2, :y 3}})
               possible-moves => (contains {:color    :black
                                            :insect   :ant
                                            :position {:x 1, :y 0}})
               possible-moves => (contains {:color    :black
                                            :insect   :ant
                                            :position {:x 0, :y 1}})
               possible-moves => (contains {:color    :black
                                            :insect   :ant
                                            :position {:x 2, :y 1}})
               possible-moves => (contains {:color    :black
                                            :insect   :ant
                                            :position {:x 0, :y 3}})
               possible-moves => (contains {:color    :black
                                            :insect   :ant
                                            :position {:x 1, :y 4}})
               possible-moves => (contains {:color    :black
                                            :insect   :ant
                                            :position {:x 2, :y 3}})
               possible-moves => (contains {:color    :black
                                            :insect   :queen
                                            :position {:x 1, :y 0}})
               possible-moves => (contains {:color    :black
                                            :insect   :queen
                                            :position {:x 2, :y 1}})
               possible-moves => (contains {:color    :black
                                            :insect   :queen
                                            :position {:x 0, :y 3}})
               possible-moves => (contains {:color    :black
                                            :insect   :queen
                                            :position {:x 1, :y 4}})
               possible-moves => (contains {:color    :black
                                            :insect   :queen
                                            :position {:x 2, :y 3}}))))