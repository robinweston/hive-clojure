(ns hive-clojure.grid-sizing-tests
  (:require [clojure.test :refer :all]
            [hive-clojure.core :refer :all]))

(defn parse-grid [raw-grid] [[0]])

(deftest empty-grid-test
  (testing "When parsing an empty grid it has the correct size"
           (let [parsed-grid (parse-grid "")
                 grid-width (count (first parsed-grid))]
    (is (= grid-width 1)))))
