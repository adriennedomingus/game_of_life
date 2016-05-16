(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest makes-board
  (is (= 5 (count (make-board 5 5))))
  (is (= 4 (count (first(make-board 5 4))))))

(deftest represents-row
  (is (= "0 0 0 0 0" (print-row [0 0 0 0 0]))))

(deftest represents-board
  (let [board (make-board 2 2)]
    (is (= "0 0\n0 0" (print-board board)))))

(deftest find-neighbors
  (is (= 8 (count (neighbors [0 0])))))

(def test-board
  [[true true] [true false]])

; (deftest processes-board
  ; (is (= test-board (circle test-board))))

(deftest check-if-alive?
  (is (= true ( alive? test-board [ 0 0 ])))
  (is (= nil ( alive? test-board [ -1 -1 ])))
  (is (= false ( alive? test-board [ 1 1 ]))))

(deftest test-count-living-neighbors
  (is ( = 2 ( count-living-neighbors test-board [ 0 0 ])))
  (is ( = 3 ( count-living-neighbors test-board [ 1 1 ] ))))

(deftest test-next-state-for-board-coord
  (is (= true (next-state test-board [ 1 1 ])))
  (is (= true (next-state test-board [ 0 0 ] ))))

(deftest test-next-state-for-board
  (is (= [[true true] [true true]]
         (next-board test-board))))