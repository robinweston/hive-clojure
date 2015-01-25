(ns hive-clojure.core)

(defn- calculate-grid-width [grid-lines]
  (let [longest-grid-line (if (empty? grid-lines)
                            0
                            (max (map #(count %) grid-lines)))]
    (if (= longest-grid-line 0)
      1
      (/ (+ longest-grid-line 7) 4))))

(defn- calculate-grid-height [grid-lines]
  (let [total-grid-lines (count grid-lines)]
    (case total-grid-lines
      0 1
      3 3
      (/ total-grid-lines 2))))

(defn parse-grid-text [raw-grid]
  (print "Parsing grid\n")
  (print raw-grid)
  (print "\n\n")

  (let [grid-lines (clojure.string/split-lines raw-grid)
        grid-width (calculate-grid-width grid-lines)
        grid-height (calculate-grid-height grid-lines)]
    (make-array Character/TYPE grid-height grid-width)))
