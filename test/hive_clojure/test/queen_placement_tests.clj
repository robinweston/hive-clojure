(ns hive-clojure.test.queen-placement-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.valid-move-generation :refer [valid-next-game-states]])
  (:require [hive-clojure.test.helpers.hive-parser :refer [parse-test-hive-tiles]]))

(facts "about queen placement"
       (fact "White queen must be played by sixth turn"
             (let [white-tiles [{:color :white, :insect :ant}
                                {:color :white, :insect :queen}]
                   game-state {:turn-number   6
                               :played-tiles  (parse-test-hive-tiles "sixth-turn")
                               :tiles-in-hand {:white white-tiles :black []}}
                   next-game-states (valid-next-game-states game-state)]
               (count next-game-states) => 3
               next-game-states => (contains
                                     (contains {:played-tiles (just {:color :white, :insect :ant, :position {:x 2, :y 2}}
                                                                    {:color :black, :insect :ant, :position {:x 3, :y 3}}
                                                                    {:color :white, :insect :spider, :position {:x 1, :y 3}}
                                                                    {:color :black, :insect :ant, :position {:x 1, :y 5}}
                                                                    {:color :white, :insect :queen, :position {:x 0, :y 2}})}))
               next-game-states => (contains
                                     (contains {:played-tiles (just {:color :white, :insect :ant, :position {:x 2, :y 2}}
                                                                    {:color :black, :insect :ant, :position {:x 3, :y 3}}
                                                                    {:color :white, :insect :spider, :position {:x 1, :y 3}}
                                                                    {:color :black, :insect :ant, :position {:x 1, :y 5}}
                                                                    {:color :white, :insect :queen, :position {:x 1, :y 1}})}))

               next-game-states => (contains
                                     (contains {:played-tiles (just {:color :white, :insect :ant, :position {:x 2, :y 2}}
                                                                    {:color :black, :insect :ant, :position {:x 3, :y 3}}
                                                                    {:color :white, :insect :spider, :position {:x 1, :y 3}}
                                                                    {:color :black, :insect :ant, :position {:x 1, :y 5}}
                                                                    {:color :white, :insect :queen, :position {:x 2, :y 0}})}))

               ))

       (fact "Black queen must be played by seventh turn"
             (let [black-tiles [{:color :black, :insect :ant}
                                {:color :black, :insect :queen}]
                   game-state {:turn-number   7
                               :played-tiles  (parse-test-hive-tiles "seventh-turn")
                               :tiles-in-hand {:black black-tiles, :white []}}
                   next-game-states (valid-next-game-states game-state)]
               (count next-game-states) => 3
               next-game-states => (contains
                                     (contains {:played-tiles (just {:color :white, :insect :ant, :position {:x 2, :y 2}}
                                                                    {:color :black, :insect :ant, :position {:x 3, :y 3}}
                                                                    {:color :white, :insect :spider, :position {:x 1, :y 3}}
                                                                    {:color :white, :insect :queen, :position {:x 4, :y 4}}
                                                                    {:color :black, :insect :ant, :position {:x 1, :y 5}}
                                                                    {:color :black, :insect :queen, :position {:x 1, :y 7}})}))
               next-game-states => (contains
                                     (contains {:played-tiles (just {:color :white, :insect :ant, :position {:x 2, :y 2}}
                                                                    {:color :black, :insect :ant, :position {:x 3, :y 3}}
                                                                    {:color :white, :insect :spider, :position {:x 1, :y 3}}
                                                                    {:color :white, :insect :queen, :position {:x 4, :y 4}}
                                                                    {:color :black, :insect :ant, :position {:x 1, :y 5}}
                                                                    {:color :black, :insect :queen, :position {:x 2, :y 6}})}))
               next-game-states => (contains
                                     (contains {:played-tiles (just {:color :white, :insect :ant, :position {:x 2, :y 2}}
                                                                    {:color :black, :insect :ant, :position {:x 3, :y 3}}
                                                                    {:color :white, :insect :spider, :position {:x 1, :y 3}}
                                                                    {:color :white, :insect :queen, :position {:x 4, :y 4}}
                                                                    {:color :black, :insect :ant, :position {:x 1, :y 5}}
                                                                    {:color :black, :insect :queen, :position {:x 0, :y 6}})}))
               )))
