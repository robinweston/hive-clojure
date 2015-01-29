(ns hive-clojure.turn-ordering)

(defn next-to-play [game-state]
  (if (-> game-state :turn-number (mod 2) (= 0))
    :white
    :black
    ))