(ns hive-clojure.valid-move-generation
  (:require [hive-clojure.turn-ordering :refer [current-player current-opposition]])
  (:require [hive-clojure.tile-positioning :refer [add-zero-position-to-tile apply-delta-to-position adjacent-positions generate-all-tile-position-combinations]])
  (:require [clojure.set :as cs]))

(defn- find-white-initial-moves [game-state]
  (->> game-state :white-tiles (map add-zero-position-to-tile)))

(defn- find-black-initial-moves [game-state]
  (let [white-position (-> game-state :played-tiles first :position)
        adjacent-positions (adjacent-positions white-position)
        tiles-to-play (:black-tiles game-state)]
    (generate-all-tile-position-combinations adjacent-positions tiles-to-play)))

(defn- find-free-positions-adjacent-to-played-tiles [game-state color]
  (let [played-tiles (:played-tiles game-state)
        positions-of-played-tiles (map :position played-tiles)
        tiles-played-by-current-player (filter #(= (:color %) color) played-tiles)
        positions-adjacent-to-played-tiles (->> tiles-played-by-current-player (mapcat #(adjacent-positions (:position %))) distinct set)
        free-positions-adjacent-to-played-tiles (cs/difference positions-adjacent-to-played-tiles (set positions-of-played-tiles))]
    free-positions-adjacent-to-played-tiles))

(defn- find-post-initial-move-moves [game-state]
  (let [current-player (current-player game-state)
        opposition (current-opposition game-state)
        unplayed-current-player-tiles (if (= current-player :white)
                                        (:white-tiles game-state)
                                        (:black-tiles game-state))
        positions-adjacent-to-tiles-already-played-by-current-player (find-free-positions-adjacent-to-played-tiles game-state current-player)
        positions-adjacent-to-tiles-played-by-opposition-player (find-free-positions-adjacent-to-played-tiles game-state opposition)
        positions-adjacent-to-tiles-played-by-current-player-but-not-adjacent-to-tiles-played-by-opposition-player
        (cs/difference positions-adjacent-to-tiles-already-played-by-current-player positions-adjacent-to-tiles-played-by-opposition-player)]
    (generate-all-tile-position-combinations
      positions-adjacent-to-tiles-played-by-current-player-but-not-adjacent-to-tiles-played-by-opposition-player
      unplayed-current-player-tiles)))

(defn valid-moves [game-state]
  (let [valid-moves (case (:turn-number game-state)
                      0 (find-white-initial-moves game-state)
                      1 (find-black-initial-moves game-state)
                      (find-post-initial-move-moves game-state))]
    (distinct valid-moves)))
