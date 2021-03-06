(ns hive-clojure.test.helpers.hive-parser
  (:require [local-file :refer :all])
  (:require [hive-clojure.hive-parsing :refer [parse-hive-tiles]] ))

(defn- slurp-grid-text-from-file [grid-name]
  (slurp (clojure.java.io/file (project-dir) "test/grids/" (str grid-name ".txt"))))

(defn parse-test-hive-tiles [hive-name]
  (let [hive-text (slurp-grid-text-from-file hive-name)]
    (parse-hive-tiles hive-text)))
