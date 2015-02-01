(ns hive-clojure.core
  (:require [hive-clojure.tile-parser :refer [parse-tile]]))

(defn- split-grid-text-into-lines [grid-text]
  (let [split-lines (clojure.string/split-lines grid-text)]
    (filter #(not (clojure.string/blank? %)) split-lines)))

(defn- re-pos [re s]
  (loop [m (re-matcher re s)
         res {}]
    (if (.find m)
      (recur m (assoc res (.start m) (.group m)))
      res)))

(defn- create-tile [tile-text line-number match-index]
  (let [tile-x (-> match-index (- 2) (/ 4) inc)
        tile-y (inc line-number)]
    (-> tile-text parse-tile (assoc-in [:position :x] tile-x) (assoc-in [:position :y] tile-y))))

(defn- parse-tiles-from-hive-line [line-text line-number]
  (let [tile-matches (re-pos #"[A-Za-z]" line-text)]
    (map #(create-tile (val %) line-number (key %)) tile-matches)))

(defn parse-hive-tiles [hive-text]

  (let [grid-lines (-> hive-text split-grid-text-into-lines vec)
        grid-line-numbers (range (count grid-lines))
        parsed-tiles (mapcat #(parse-tiles-from-hive-line (grid-lines %) %) grid-line-numbers)]
    parsed-tiles
    ))
