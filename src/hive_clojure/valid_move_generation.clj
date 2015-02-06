(ns hive-clojure.valid-move-generation
  (:require [hive-clojure.turn-ordering :refer [current-player current-opposition]])
  (:require [hive-clojure.tile-positioning :refer [add-zero-position-to-tile apply-delta-to-position adjacent-positions generate-all-tile-position-combinations]])
  (:require [clojure.set :as cs]))

(defn- find-white-initial-moves [game-state]
  (->> game-state :tiles-in-hand :white (map add-zero-position-to-tile)))

(defn- find-black-initial-moves [game-state]
  (let [white-position (-> game-state :played-tiles first :position)
        adjacent-positions (adjacent-positions white-position)
        tiles-to-play (-> game-state :tiles-in-hand :black)]
    (generate-all-tile-position-combinations adjacent-positions tiles-to-play)))

(defn- find-free-positions-adjacent-to-played-tiles [game-state color]
  (let [played-tiles (:played-tiles game-state)
        positions-of-played-tiles (map :position played-tiles)
        tiles-played-by-current-player (filter #(= (:color %) color) played-tiles)
        positions-adjacent-to-played-tiles (->> tiles-played-by-current-player (mapcat #(adjacent-positions (:position %))) distinct set)
        free-positions-adjacent-to-played-tiles (cs/difference positions-adjacent-to-played-tiles (set positions-of-played-tiles))]
    free-positions-adjacent-to-played-tiles))

(defn- tiles-in-hand [game-state current-player]
  (let [all-tiles-in-hand (-> game-state :tiles-in-hand current-player)
        third-or-greater-turn-for-player? (> (:turn-number game-state) 5)
        queen-in-hand (first (filter #(= :queen (:insect %)) all-tiles-in-hand))]
    (if (and third-or-greater-turn-for-player? queen-in-hand)
      [queen-in-hand]
      all-tiles-in-hand)))

(defn- find-post-initial-move-moves [game-state]
  (let [current-player (current-player game-state)
        opposition (current-opposition game-state)
        current-player-tiles-in-hand (tiles-in-hand game-state current-player)
        positions-adjacent-to-tiles-already-played-by-current-player (find-free-positions-adjacent-to-played-tiles game-state current-player)
        positions-adjacent-to-tiles-played-by-opposition-player (find-free-positions-adjacent-to-played-tiles game-state opposition)
        positions-adjacent-to-tiles-played-by-current-player-but-not-adjacent-to-tiles-played-by-opposition-player
        (cs/difference positions-adjacent-to-tiles-already-played-by-current-player positions-adjacent-to-tiles-played-by-opposition-player)]
    (generate-all-tile-position-combinations
      positions-adjacent-to-tiles-played-by-current-player-but-not-adjacent-to-tiles-played-by-opposition-player
      current-player-tiles-in-hand)))

(defn- remove-first [pred coll] ;TODO move to general utilities file
  (let [[pre post] (split-with #(not (pred %)) coll)]
    (concat pre (rest post))))

(defn- remove-tile-in-hand [tiles-in-hand tile-to-remove]
  (remove-first #(= (:insect %) (:insect tile-to-remove)) tiles-in-hand))

(defn- make-move [game-state move]
  (-> game-state
      (update-in [:played-tiles] conj move)
      (update-in [:turn-number] inc)
      (update-in [:tiles-in-hand :white] remove-tile-in-hand move)))

(defn- make-moves [game-state valid-moves]
  (map #(make-move game-state %) valid-moves))

(defn valid-next-game-states [game-state]
  (let [valid-moves (case (:turn-number game-state)
                      0 (find-white-initial-moves game-state)
                      1 (find-black-initial-moves game-state)
                      (find-post-initial-move-moves game-state))
        distinct-valid-moves (distinct valid-moves)
        new-game-states (make-moves game-state distinct-valid-moves)]
    new-game-states))