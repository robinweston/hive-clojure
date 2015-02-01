(ns hive-clojure.valid-move-generation
  (:require [hive-clojure.turn-ordering :refer [next-to-play]])
  (:require [clojure.set :as cs]))

(defn- apply-delta-to-position [position delta]
  {
   :x (+ (:x position) (first delta))
   :y (+ (:y position) (second delta))
   }
  )

(defn- adjacent-positions [position]
  (let [position-deltas
        '(
           [0 -2]                                           ; above
           [-1 -1]                                          ; above left
           [1 -1]                                           ; above right
           [-1 1]                                           ; below left
           [1 1]                                            ; below right
           [0 2]                                            ; below
           )]
    (map (fn [delta]
           (apply-delta-to-position position delta))
         position-deltas)))

(defn- add-zero-position-to-tile [tile]
  (assoc tile :position {:x 0, :y 0}))

(defn- find-white-initial-moves [game-state]
  (->> game-state :white-tiles (map add-zero-position-to-tile)))

(defn- find-black-initial-moves [game-state]
  (let [white-position (-> game-state :played-tiles first :position)
        adjacent-positions (adjacent-positions white-position)
        tiles-to-play (:black-tiles game-state)]
    (for [position adjacent-positions ; TODO DRY
          tile tiles-to-play]
      (assoc tile :position position))))

(defn- opposition [color]
  (if (= color :white)
    :black
    :white)
  )

(defn- find-free-positions-adjacent-to-played-tiles [game-state color]
  (let [played-tiles (:played-tiles game-state)
        positions-of-played-tiles (map :position played-tiles)
        tiles-played-by-current-player (filter #(= (:color %) color) played-tiles)
        positions-adjacent-to-played-tiles (->> tiles-played-by-current-player (mapcat #(adjacent-positions (:position %))) distinct set)
        free-positions-adjacent-to-played-tiles (cs/difference positions-adjacent-to-played-tiles (set positions-of-played-tiles))]

    free-positions-adjacent-to-played-tiles))


(defn- find-post-initial-move-moves [game-state]
  (let [current-player (next-to-play game-state)
        opposition (opposition current-player)
        unplayed-current-player-tiles (if (= current-player :white)
                                        (:white-tiles game-state)
                                        (:black-tiles game-state))
        positions-adjacent-to-tiles-already-played-by-current-player (find-free-positions-adjacent-to-played-tiles game-state current-player)
        positions-adjacent-to-tiles-played-by-opposition-player (find-free-positions-adjacent-to-played-tiles game-state opposition)
        positions-adjacent-to-tiles-played-by-current-player-but-not-adjacent-to-tiles-played-by-opposition-player
        (cs/difference positions-adjacent-to-tiles-already-played-by-current-player positions-adjacent-to-tiles-played-by-opposition-player)]
    (for [position positions-adjacent-to-tiles-played-by-current-player-but-not-adjacent-to-tiles-played-by-opposition-player
          tile unplayed-current-player-tiles]
      (assoc tile :position position))))

(defn valid-moves [game-state]
  (let [valid-moves (case (:turn-number game-state)
                      0 (find-white-initial-moves game-state)
                      1 (find-black-initial-moves game-state)
                      (find-post-initial-move-moves game-state))]
    (distinct valid-moves)))
