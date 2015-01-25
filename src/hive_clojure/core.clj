(ns hive-clojure.core)

(defn- calculate-grid-width [grid-lines]
  (let [longest-grid-line (if (empty? grid-lines)
                            0
                            (max (map #(count %) grid-lines)))
        _ (print "longest grid line" longest-grid-line)]
    (if (= longest-grid-line 0)
      1
      (/ (+ longest-grid-line 7) 4))))

(defn- calculate-grid-height [grid-lines]
  (let [total-grid-lines (count grid-lines)]
    (case total-grid-lines
      0 1
      3 3
      (/ total-grid-lines 2))))

(defn- create-empty-grid [width height]
  (->> "-" (repeat width) vec (repeat height) vec))

(defn parse-grid-text [raw-grid]
  (print "Parsing grid\n")
  (print raw-grid)
  (print "\n\n")

  (let [split-lines (clojure.string/split-lines raw-grid)
        grid-lines (filter #(not (clojure.string/blank? %)) split-lines)
        grid-width (calculate-grid-width grid-lines)
        grid-height (calculate-grid-height grid-lines)]
    (create-empty-grid grid-width grid-height)))
