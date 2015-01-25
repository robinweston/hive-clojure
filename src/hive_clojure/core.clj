(ns hive-clojure.core)

(defn- longest-line-length [grid-lines]
  (if (empty? grid-lines)
    0
    (apply max (map #(count %) grid-lines))))

(defn- calculate-grid-width [grid-lines]
  (let [longest-grid-line (longest-line-length grid-lines)]
    (if (= longest-grid-line 0)
      1
      (/ (+ longest-grid-line 7) 4))))

(defn- calculate-grid-height [grid-lines]
  (let [total-grid-lines (count grid-lines)]
    (case total-grid-lines
      0 1
      3 3
      (+ total-grid-lines 2))))

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
