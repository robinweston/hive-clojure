(ns hive-clojure.test.initial-move-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.valid-move-generation :refer [valid-next-game-states]])
  (:require [hive-clojure.test.helpers.hive-parser :refer [parse-test-hive-tiles]]))

(facts "about initial moves"
       (fact "At start of game and with only one tile white has only one valid move"
             (let [white-tiles [{:color :white, :insect :ant}]
                   game-state {:turn-number   0
                               :played-tiles  []
                               :tiles-in-hand {:white white-tiles}}
                   next-game-states (valid-next-game-states game-state)]
               (count next-game-states) => 1
               ; TODO: check all states increment turn number
               next-game-states => [{:turn-number   1
                                     :tiles-in-hand {:white []}
                                     :played-tiles  [{:color    :white
                                                      :insect   :ant
                                                      :position {:x 0, :y 0}}]
                                     }]
               ))

       (fact "At start of game and with two tiles white has two valid moves"
             (let [white-tiles [{:color :white, :insect :ant}
                                {:color :white, :insect :queen}]
                   game-state {:turn-number   0
                               :played-tiles  []
                               :tiles-in-hand {:white white-tiles}}
                   next-game-states (valid-next-game-states game-state)]
               (count next-game-states) => 2
               ; TODO: check all states incremement turn number
               next-game-states => (contains {:turn-number   1
                                              :tiles-in-hand {:white [{:color :white, :insect :queen}]}
                                              :played-tiles  [{:color    :white
                                                               :insect   :ant
                                                               :position {:x 0 :y 0}}]
                                              })
               next-game-states => (contains {:turn-number   1
                                              :tiles-in-hand {:white [{:color :white, :insect :ant}]}
                                              :played-tiles  [{:color    :white
                                                               :insect   :queen
                                                               :position {:x 0 :y 0}}]
                                              })
               ))

       (fact "At start of game and with two identical tiles duplicate moves are not generated"
             (let [white-tiles [{:color :white, :insect :ant}
                                {:color :white, :insect :ant}]
                   game-state {:turn-number   0
                               :played-tiles  []
                               :tiles-in-hand {:white white-tiles :black []}}
                   next-game-states (valid-next-game-states game-state)]
               (count next-game-states) => 1
               next-game-states => [{:turn-number   1
                                     :tiles-in-hand {:white [{:color :white, :insect :ant}]
                                                     :black []}
                                     :played-tiles  [{
                                                      :color    :white
                                                      :insect   :ant
                                                      :position {:x 0 :y 0}}]}]
               ))

       (fact "At start of game and with one tile black has correct valid moves"
             (let [black-tiles [{:color :black, :insect :ant}]
                   game-state {:turn-number   1
                               :played-tiles  (parse-test-hive-tiles "single-white-queen")
                               :tiles-in-hand {:black black-tiles :white []}}
                   next-game-states (valid-next-game-states game-state)]
               (count next-game-states) => 6
               next-game-states => (contains
                                     (contains {:played-tiles
                                                (just {:color    :white
                                                       :insect   :queen
                                                       :position {:x 1 :y 2}}
                                                      {:color    :black
                                                       :insect   :ant
                                                       :position {:x 1, :y 0}})}))
               next-game-states => (contains
                                     (contains {:played-tiles
                                                (just {:color    :white
                                                       :insect   :queen
                                                       :position {:x 1 :y 2}}
                                                      {:color    :black
                                                       :insect   :ant
                                                       :position {:x 0, :y 1}})}))
               next-game-states => (contains
                                     (contains {:played-tiles
                                                (just {:color    :white
                                                       :insect   :queen
                                                       :position {:x 1 :y 2}}
                                                      {:color    :black
                                                       :insect   :ant
                                                       :position {:x 2, :y 1}})}))
               next-game-states => (contains
                                     (contains {:played-tiles
                                                (just {:color    :white
                                                       :insect   :queen
                                                       :position {:x 1 :y 2}}
                                                      {:color    :black
                                                       :insect   :ant
                                                       :position {:x 0, :y 3}})}))
               next-game-states => (contains
                                     (contains {:played-tiles
                                                (just {:color    :white
                                                       :insect   :queen
                                                       :position {:x 1 :y 2}}
                                                      {:color    :black
                                                       :insect   :ant
                                                       :position {:x 1, :y 4}})}))
               next-game-states => (contains
                                     (contains {:played-tiles
                                                (just {:color    :white
                                                       :insect   :queen
                                                       :position {:x 1 :y 2}}
                                                      {:color    :black
                                                       :insect   :ant
                                                       :position {:x 2, :y 3}})}))
               ))

       (fact "At start of game and with three tiles (but two indentical) black has correct valid moves"
             (let [black-tiles [{:color :black, :insect :ant}
                                {:color :black, :insect :ant}
                                {:color :black, :insect :queen}]
                   game-state {:turn-number   1
                               :played-tiles  (parse-test-hive-tiles "single-white-queen")
                               :tiles-in-hand {:white [] :black black-tiles}}
                   next-game-states (valid-next-game-states game-state)]
               (count next-game-states) => 12
               next-game-states => (contains
                                     (contains {:played-tiles (just {:color    :white
                                                                     :insect   :queen
                                                                     :position {:x 1 :y 2}}
                                                                    {:color    :black
                                                                     :insect   :ant
                                                                     :position {:x 2, :y 3}})}))

               next-game-states => (contains
                                     (contains {:played-tiles (just {:color    :white
                                                                     :insect   :queen
                                                                     :position {:x 1 :y 2}}
                                                                    {:color    :black
                                                                     :insect   :ant
                                                                     :position {:x 1, :y 0}})}))
               next-game-states => (contains
                                     (contains {:played-tiles (just {:color    :white
                                                                     :insect   :queen
                                                                     :position {:x 1 :y 2}}
                                                                    {:color    :black
                                                                     :insect   :ant
                                                                     :position {:x 0, :y 1}})}))

               next-game-states => (contains
                                     (contains {:played-tiles (just {:color    :white
                                                                     :insect   :queen
                                                                     :position {:x 1 :y 2}}
                                                                    {:color    :black
                                                                     :insect   :ant
                                                                     :position {:x 2, :y 1}})}))

               next-game-states => (contains
                                     (contains {:played-tiles (just {:color    :white
                                                                     :insect   :queen
                                                                     :position {:x 1 :y 2}}
                                                                    {:color    :black
                                                                     :insect   :ant
                                                                     :position {:x 0, :y 3}})}))

               next-game-states => (contains
                                     (contains {:played-tiles (just {:color    :white
                                                                     :insect   :queen
                                                                     :position {:x 1 :y 2}}
                                                                    {:color    :black
                                                                     :insect   :ant
                                                                     :position {:x 1, :y 4}})}))

               next-game-states => (contains
                                     (contains {:played-tiles (just {:color    :white
                                                                     :insect   :queen
                                                                     :position {:x 1 :y 2}}
                                                                    {:color    :black
                                                                     :insect   :ant
                                                                     :position {:x 2, :y 3}})}))


               next-game-states => (contains
                                     (contains {:played-tiles (just {:color    :white
                                                                     :insect   :queen
                                                                     :position {:x 1 :y 2}}
                                                                    {:color    :black
                                                                     :insect   :queen
                                                                     :position {:x 1, :y 0}})}))

               next-game-states => (contains
                                     (contains {:played-tiles (just {:color    :white
                                                                     :insect   :queen
                                                                     :position {:x 1 :y 2}}
                                                                    {:color    :black
                                                                     :insect   :queen
                                                                     :position {:x 2, :y 1}})}))

               next-game-states => (contains
                                     (contains {:played-tiles (just {:color    :white
                                                                     :insect   :queen
                                                                     :position {:x 1 :y 2}}
                                                                    {:color    :black
                                                                     :insect   :queen
                                                                     :position {:x 0, :y 3}})}))

               next-game-states => (contains
                                     (contains {:played-tiles (just {:color    :white
                                                                     :insect   :queen
                                                                     :position {:x 1 :y 2}}
                                                                    {:color    :black
                                                                     :insect   :queen
                                                                     :position {:x 1, :y 4}})}))

               next-game-states => (contains
                                     (contains {:played-tiles (just {:color    :white
                                                                     :insect   :queen
                                                                     :position {:x 1 :y 2}}
                                                                    {:color    :black
                                                                     :insect   :queen
                                                                     :position {:x 2, :y 3}})}))

               )))