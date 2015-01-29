(ns hive-clojure.initial-move-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.turn-ordering :refer [next-to-play]]))


(defn valid-moves [game-state]
  (let [tiles-to-play (if (= (next-to-play game-state) :white)
                        (:white-tiles game-state)
                        (:black-tiles game-state))
        add-position-to-tile (fn [tile] (assoc tile :position {:x 0, :y 0}))]
    (map add-position-to-tile tiles-to-play)
    ))

(facts "about initial moves"
       (fact "At start of game and with only one tile white has only one valid move"
             (let [white-tiles [{:color :white, :insect :ant}]
                   game-state {:turn-number  0
                               :played-tiles []
                               :white-tiles  white-tiles}
                   valid-moves (valid-moves game-state)]
               valid-moves => (just [{:color    :white
                                      :insect   :ant
                                      :position {:x 0, :y 0}}
                                     ])
               ))

       (fact "At start of game and with two tiles white has two valid moves"
             (let [white-tiles [{:color :white, :insect :ant}
                                {:color :white, :insect :queen}]
                   game-state {:turn-number  0
                               :played-tiles []
                               :white-tiles  white-tiles}
                   valid-moves (valid-moves game-state)]
               valid-moves => (just [{:color    :white
                                      :insect   :ant
                                      :position {:x 0, :y 0}}
                                     {:color    :white
                                      :insect   :queen
                                      :position {:x 0, :y 0}}
                                     ])
               ))

       (future-fact "Remove duplicate moves")

       (fact "At start of game and with one tile black has one valid move"
             (let [black-tiles [{:color :black, :insect :ant}]
                   game-state {:turn-number  1
                               :played-tiles []
                               :white-tiles  []
                               :black-tiles  black-tiles}
                   valid-moves (valid-moves game-state)]
               valid-moves => (just [{:color    :black
                                      :insect   :ant
                                      :position {:x 0, :y 0}}
                                     ])
               ))
       )
