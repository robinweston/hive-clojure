(ns hive-clojure.tile-positioning)

(defn apply-delta-to-position [position delta]
  (hash-map
    :x (+ (:x position) (first delta))
    :y (+ (:y position) (second delta))))

(defn adjacent-positions [position]
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

(defn add-zero-position-to-tile [tile]
  (assoc tile :position {:x 0, :y 0}))

(defn generate-all-tile-position-combinations [positions tiles]
  (for [position positions
        tile tiles]
    (assoc tile :position position)))
