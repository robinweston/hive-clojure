(ns hive-clojure.turn-ordering)

(defn- current-player-from-turn-number [turn-number]
  (if (-> turn-number (mod 2) (= 0))
    :white
    :black
    ))

(defn current-player [game-state]
  (current-player-from-turn-number (:turn-number game-state)))

(defn current-opposition [game-state]
  (current-player-from-turn-number (-> game-state :turn-number inc)))
