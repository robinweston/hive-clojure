(ns hive-clojure.turn-ordering)

(defn next-to-play [game-state]
  (if (= 0 (mod (:turn-number game-state) 2))
    :white
    :black
    ))