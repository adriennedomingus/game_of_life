(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest makes-board
  (is (= 5 (count (make-board 5 5))))
  (is (= 4 (count (first(make-board 5 4))))))

(deftest represents-row
  (is (= "00000" (print-row [true true true true true]))))

(deftest represents-board
  (let [board (make-board 2 2)]
    (is (= "  \n  " (print-board board)))))

(deftest find-neighbors
  (is (= 8 (count (neighbors [0 0])))))

(def test-board
  [[true true] [true false]])

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

(deftest test-they-all-die
  (let [board [[true true] [false false]]]
    (is ( = [[false false] [false false]] (next-board board)))))

(deftest test-run-multiple-generations
  (let [board [[false false false] [true true true] [false false false]]]
    (is ( propagate board 30)))
  )