(ns hive-clojure.grid-sizing-tests
  (:require [midje.sweet :refer :all])
  (:require [local-file :refer :all])
  (:require [hive-clojure.core :refer [parse-grid-text]]))

(defn- slurp-grid-text-from-file [grid-file]
  (slurp (clojure.java.io/file (project-dir) "test/grids/" (str grid-file ".txt"))))

(defn- retrieve-grid-dimensions [grid]
  (vector (count (first grid)) (count grid)))

(facts "grid sizing"
       (fact "Empty grid is sized correctly"
             (let [grid-text (slurp-grid-text-from-file "empty")
                   parsed-grid (parse-grid-text grid-text)
                   grid-dimensions (retrieve-grid-dimensions parsed-grid)]
               (nth grid-dimensions 0) => 1
               (nth grid-dimensions 1) => 1)))