(ns hive-clojure.tile-parser)

(defn- parse-tile-insect [tile-string]
  (case (clojure.string/upper-case tile-string)
    "Q" :queen
    "A" :ant))

(defn- parse-tile-color [tile-string]
  (if (= (clojure.string/upper-case tile-string) tile-string) :black :white))   ; todo: use RegEx

(defn parse-tile [tile-string]
  (let [tile-color (parse-tile-color tile-string)
        insect (parse-tile-insect tile-string)]
    {:insect insect, :color tile-color}))