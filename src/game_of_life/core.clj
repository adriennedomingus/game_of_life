(ns game-of-life.core)

(defn make-board [height width]
  (for [y (range height)]
    (take width (repeat 0 ))))

(defn print-row [row]
  (clojure.string/join " " row))

(defn print-board [board]
  (clojure.string/join "\n" (map print-row board)))

(defn neighbors [[x y]]
  (for [x-shift [1 0 -1]
        y-shift [1 0 -1]
        :when (not (= [0 0] [x-shift y-shift]))]
    [(+ x x-shift) (+ y y-shift)]))

(defn alive? [board [ x y ]]
  ( get-in board [ y x ]))

;; evaluate cell's current state
;; know x y coordinates of current cell
;; find all cell's neighbors
;; count living neighbors
;; evaluate rules for next state
