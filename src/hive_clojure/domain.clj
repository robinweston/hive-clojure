(ns hive-clojure.domain)

(defrecord Board [played-tiles tiles-in-hand turn-number]

  )

(defrecord Tile [insect color])