(ns aoc2020.core
  (:require [clojure.string :as str]))

;; Day 3
(def sample_input "..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#")

(def input (slurp "resources/day03.in"))
(count (first (str/split-lines sample_input)))

(defn read_position
  "detects trees at position x,y on map. If x > width, wraps around."
  [tree_map x y]
  (let [lines (str/split-lines tree_map)
        line (nth lines y)
        width (count line)
        x_rem (mod x width)
        feature (nth line x_rem)]
    feature))

(defn read_positions
  "detects all trees encountered before reaching the bottom if the path follows
   the given slope."
  [tree_map slope_x slope_y]
  (let [max_y (count (str/split-lines tree_map))]
    (loop [x 0 y 0 acc '()]
      (if (>= y max_y)
        acc
        (recur (+ x slope_x) 
               (+ y slope_y)
               (cons (read_position tree_map x y) acc))))))

(count (filter #(= \# %) (read_positions sample_input 3 1)))
(count (filter #(= \# %) (read_positions input 3 1)))

; part 2

(def slopes '([1 1] [3 1] [5 1] [7 1] [1 2]))

(defn count_trees
  [tree_map slope_x slope_y]
  (count (filter #(= \# %) (read_positions tree_map slope_x slope_y))))

(count_trees input (first (second slopes)) (second (second slopes)))

(defn slopes_counts
  [tree_map slopes]
  (loop [m (first slopes)
         remaining (rest slopes)
         acc '()]
    (if (not m)
      acc
      (recur (first remaining)
             (rest remaining)
             (cons (count_trees tree_map (first m) (second m)) acc)))))

(slopes_counts sample_input slopes)
(apply * (slopes_counts input slopes))
