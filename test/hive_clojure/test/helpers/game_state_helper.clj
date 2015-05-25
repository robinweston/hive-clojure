(ns hive-clojure.test.helpers.game-state-helper)

(defn last-moves [game-states]
  (map (fn [game-state] (-> game-state :played-tiles last)) game-states))