(ns game-of-life.core)

(defn make-board [height width]
  (for [y (range height)]
    (take width (repeat false))))

(defn print-row [row]
  (clojure.string/join "" (map (fn [cell] (if cell "0" " ")) row)))

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
  (let [live-count (count-living-neighbors board coord)
        currently-living (alive? board coord) ]
    (cond
      (and (= live-count 2) (not currently-living)) false
      (< live-count 2) false
      (< live-count 4) true
      :else false)))

(defn next-board [board]
  (vec (map-indexed (fn [y row]
                 (vec (map-indexed (fn [x state]
                                (next-state board [x y]))
                              row )))
               board)))

(defn propagate [board ngen]
  (loop [board board ngen ngen]
    (if (= ngen 0)
      board

      (do (Thread/sleep 500) (println  (str "\u001b[2J" (print-board board))) (recur (next-board board) (dec ngen))))))
;; evaluate cell's current state
;; know x y coordinates of current cell
;; find all cell's neighbors
;; count living neighbors
;; evaluate rules for next state
