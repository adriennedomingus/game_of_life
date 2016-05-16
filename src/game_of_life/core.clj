(ns game-of-life.core)

(defn make-board [height width]
  (for [y (range height)]
    (take width (repeat 0))))

(defn print-row [row]
  (clojure.string/join " " row))

(defn print-board [board]
  (clojure.string/join "\n" (map print-row board)))

(defn neighbors [[x y]]
  (for [x-shift [1 0 -1]
        y-shift [1 0 -1]
        :when (not (= [0 0] [x-shift y-shift]))]
    [(+ x x-shift) (+ y y-shift)]))

(defn alive? [board [x y]]
  (get-in board [y x]))

(defn count-living-neighbors [board coord]
  (count (filter (partial alive? board)
                 (neighbors coord))))

(defn next-state [board coord]
  (let [live-count (count-living-neighbors board coord)]
    (cond
    (< live-count 2) false
    (< live-count 4) true
    :else false)))

(defn next-board [board]
  (map-indexed (fn [y row]
                 (map-indexed (fn [x state]
                                (next-state board [x y]))
                              row ))
               board))

;; evaluate cell's current state
;; know x y coordinates of current cell
;; find all cell's neighbors
;; count living neighbors
;; evaluate rules for next state
