(ns hive-clojure.core
  (:require [hive-clojure.tile-parser :refer [parse-tile]]))

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

(defn- split-grid-text-into-lines [grid-text]
  (let [split-lines (clojure.string/split-lines grid-text)]
    (filter #(not (clojure.string/blank? %)) split-lines)))

(defn- create-empty-grid [width height]
  (->> :empty (repeat width) vec (repeat height) vec))

(defn- place-tile-on-grid [grid x y tile]
  (let [line (grid y)                                       ; TODO: assoc in
        new-line (assoc line x tile)]
    (assoc grid y new-line)))

(defn- re-pos [re s]
  (loop [m (re-matcher re s)
         res {}]
    (if (.find m)
      (recur m (assoc res (.start m) (.group m)))
      res)))

(defn- fill-grid-old [grid grid-lines]
  (doseq [line-number (range (count grid-lines))]
    (let [line (nth grid-lines line-number)
          tile-matches (re-pos #"[A-Za-z]" line)]
      (doseq [tile-match tile-matches]
        (let [tile-x (-> (key tile-match) (- 2) (/ 4) inc)
              tile-y (inc line-number)
              tile (-> tile-match val parse-tile)]
          (place-tile-on-grid grid tile-x tile-y tile)))))
  grid)

(defn- fill-grid-from-line-matches [grid line-number tile-matches]
  (reduce (fn [new-grid tile-match]
            (let [tile-x (-> (key tile-match) (- 2) (/ 4) inc)
                  tile-y (inc line-number)
                  tile (-> tile-match val parse-tile)]
              (place-tile-on-grid new-grid tile-x tile-y tile))
            )
          grid tile-matches)
  )

(defn- fill-grid [grid grid-lines]
  (reduce (fn [new-grid line-number]
            (let [line (nth grid-lines line-number)
                  tile-matches (re-pos #"[A-Za-z]" line)]
              (fill-grid-from-line-matches new-grid line-number tile-matches)
              )) grid (range (count grid-lines))))

(defn parse-grid-text [grid-text]
  (print "Parsing grid\n")
  (print grid-text)
  (print "\n\n")

  (let [grid-lines (split-grid-text-into-lines grid-text)
        grid-width (calculate-grid-width grid-lines)
        grid-height (calculate-grid-height grid-lines)
        empty-grid (create-empty-grid grid-width grid-height)
        filled-grid (fill-grid empty-grid grid-lines)]
    filled-grid
    ))
