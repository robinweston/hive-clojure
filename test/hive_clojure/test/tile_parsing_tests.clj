(ns hive-clojure.test.tile-parsing-tests
  (:require [midje.sweet :refer :all])
  (:require [hive-clojure.tile-parser :refer [parse-tile]]))

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
               ))
       (fact "Can parse spider"
             (let [tile (parse-tile "S")]
               (:insect tile) => :spider
               (:color tile) => :black
               )))
