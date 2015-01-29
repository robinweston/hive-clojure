(ns hive-clojure.initial-move-tests
  (:require [midje.sweet :refer :all]))


(defn valid-moves [game-state]
  [{:color    :white
    :insect   :ant
    :position {:x 0, :y 0}}]
  )

(facts "about initial moves"
       (fact "At start of game and with only one tile white jas only one valid move"
             (let [played-tiles []
                   white-tiles [{:color :white, :insect :ant}]
                   game-state {:turn-number  0
                               :played-tiles played-tiles
                               :white-tiles  white-tiles}
                   valid-moves (valid-moves game-state)]
               valid-moves => (just [{:color    :white
                                      :insect   :ant
                                      :position {:x 0, :y 0}}
                                     ])
               ))
       )
