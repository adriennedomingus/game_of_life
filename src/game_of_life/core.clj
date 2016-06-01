
(ns game-of-life.core)

(defn make-board [height width]
  (vec (for [y (range height)]
    (vec (take width (repeatedly (fn [] (rand-nth [ true false false]))))))))

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
  (vec (pmap (fn [y row]
                 (vec (map (fn [x state]
                                (next-state board [x y]))
                                (range (count row)) row )))
             (range (count board)) board)))

(defn propagate [board ngen]
  (loop [board board ngen ngen]
    (if (= ngen 0)
      "Done"

      (do (println (str  "\u001b[2J" (print-board board))) (recur (next-board board) (dec ngen))))))

(defn run []
  (let [board (make-board 30 30)]
    (propagate board 50)))

(def big-board (make-board 500 500))
(time (do (next-board big-board) "done"))
