(ns hive-clojure.helpers.test-grid-parser
  (:require [local-file :refer :all])
  (:require [hive-clojure.core :refer [parse-grid-text]] ))

(defn- slurp-grid-text-from-file [grid-name]
  (slurp (clojure.java.io/file (project-dir) "test/grids/" (str grid-name ".txt"))))

(defn parse-test-grid [grid-name]
  (let [grid-text (slurp-grid-text-from-file grid-name)]
    (parse-grid-text grid-text)))
