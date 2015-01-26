(ns hive-clojure.insect-parsing-tests
  (:require [midje.sweet :refer :all]))

(defn parse-tile [tile-string]
  (let [tile-color (if (= (clojure.string/upper-case tile-string) tile-string) :black :white)
        insect (case (clojure.string/upper-case tile-string)
                 "Q" :queen
                 "A" :ant)]                                 ; todo: use RegEx
    {:insect insect, :color tile-color}
    ))

(facts "Can parse insect types"
       (fact "Can parse white queen"
             (let [tile (parse-tile "q")]
               (:insect tile) => :queen
               (:color tile) => :white
               ))
       (fact "Can parse black queen"
             (let [tile (parse-tile "Q")]
               (:insect tile) => :queen
               (:color tile) => :black
               ))
       (fact "Can parse white ant"
             (let [tile (parse-tile "a")]
               (:insect tile) => :ant
               (:color tile) => :white
               ))
       (fact "Can parse black ant"
             (let [tile (parse-tile "A")]
               (:insect tile) => :ant
               (:color tile) => :black
               )))
