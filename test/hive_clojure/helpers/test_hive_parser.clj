(ns hive-clojure.helpers.test-hive-parser
  (:require [local-file :refer :all])
  (:require [hive-clojure.core :refer [parse-hive-tiles]] ))

(defn- slurp-grid-text-from-file [grid-name]
  (slurp (clojure.java.io/file (project-dir) "test/grids/" (str grid-name ".txt"))))

(defn parse-test-hive-tiles [hive-name]
  (let [hive-text (slurp-grid-text-from-file hive-name)]
    (parse-hive-tiles hive-text)))
