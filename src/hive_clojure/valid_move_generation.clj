(ns hive-clojure.valid-move-generation
  (:require [hive-clojure.turn-ordering :refer [next-to-play]]))

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
    (println position-deltas)
    (map (fn [delta]
           (apply-delta-to-position position delta))
         position-deltas)))

(defn- add-zero-position-to-tile [tile]
  (assoc tile :position {:x 0, :y 0})
  )

(defn- find-black-initial-moves [game-state]
  (let [white-position (-> game-state :played-tiles first :position)
        adjacent-positions (adjacent-positions white-position)
        tiles-to-play (:black-tiles game-state)]
    (for [position adjacent-positions tile tiles-to-play]
      (assoc tile :position position))))

(defn valid-moves [game-state]
  (let [tiles-to-play (if (= (next-to-play game-state) :white)
                        (:white-tiles game-state)
                        (:black-tiles game-state))
        valid-moves (if (= 0 (:turn-number game-state))
                      (->> tiles-to-play (map add-zero-position-to-tile))
                      (find-black-initial-moves game-state)
                      )]
    (distinct valid-moves)
    ))
