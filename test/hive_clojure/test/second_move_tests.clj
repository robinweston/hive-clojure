(ns hive-clojure.test.second-move-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.valid-move-generation :refer [valid-moves]])
  (:require [hive-clojure.test.helpers.hive-parser :refer [parse-test-hive-tiles]]))

(facts "about second moves"
       (fact "White second move with one tile available has correct moves"
             (let [white-tiles [{:color :white, :insect :ant}]
                   game-state {:turn-number  2
                               :played-tiles (parse-test-hive-tiles "two-horizontal-queens")
                               :white-tiles  white-tiles}
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
               ))

       ;(fact "Black second move with one tile available has correct moves"
       ;      (let [black-tiles [{:color :black, :insect :ant}]
       ;            game-state {:turn-number  3
       ;                        :played-tiles (parse-test-hive-tiles "two-queens-and-a-white-spider")
       ;                        :black-tiles  black-tiles}
       ;            valid-moves (valid-moves game-state)]
       ;        (count valid-moves) => 3
       ;        valid-moves => (contains [
       ;                                  {:color    :black
       ;                                   :insect   :ant
       ;                                   :position {:x 4, :y 2}}
       ;                                  {:color    :black
       ;                                   :insect   :ant
       ;                                   :position {:x 4, :y 4}}
       ;                                  {:color    :black
       ;                                   :insect   :ant
       ;                                   :position {:x 3, :y 5}}
       ;                                  ] :in-any-order)
       ;        )))
       )