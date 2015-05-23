(ns hive-clojure.tile-parser
  (:require hive-clojure.domain)
  (:import [hive_clojure.domain Tile]))

(defn- parse-tile-insect [tile-string]
  (case (clojure.string/upper-case tile-string)
    "Q" :queen
    "A" :ant
    "S" :spider
    (throw (Exception. (str "Unknown insect: " tile-string)))))

(defn- parse-tile-color [tile-string]
  (if (= (clojure.string/upper-case tile-string) tile-string) :black :white))   ; todo: use RegEx

(defn parse-tile [tile-string]
  (let [tile-color (parse-tile-color tile-string)
        insect (parse-tile-insect tile-string)]
    (Tile. insect tile-color)))