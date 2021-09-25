(ns aoc2020.core
  (:require [clojure.string :as str]))

;; Day 2
(def sample_input "1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc")

(def input (slurp "resources/day02.in"))

(defn divide_line
  "takes a line from the passwords file and splits it into meaningful parts"
  [line]
  (let [parts (str/split line #"-| |: ")] 
    {:lo (read-string (first parts))
     :hi (read-string (second parts))
     :key (first (seq (nth parts 2)))
     :password (nth parts 3)}))

(defn validate_map
  "verifies a password/constraints map"
  [passmap]
  (let [char_count (count 
                     (filter 
                       #(= (passmap :key) %)
                       (passmap :password)))]
    (if (and (>= char_count (passmap :lo))
             (<= char_count (passmap :hi)))
      true
      false)))

(map (comp validate_map divide_line) (str/split-lines sample_input))
(count (filter true? 
               (map (comp validate_map divide_line) (str/split-lines input))))

(defn validate_2
  "verifies a password/constraints map for part 2:
   exactly one of the positions (lo, hi) must be the given character"
  [passmap]
  (let [lo_letter (nth (passmap :password) (- (passmap :lo) 1))
        hi_letter (nth (passmap :password) (- (passmap :hi) 1))
        key_letter (passmap :key)]
    (cond
      (= lo_letter hi_letter) false
      (= key_letter lo_letter) true
      (= key_letter hi_letter) true
      :else false)))

(count (filter true? 
               (map (comp validate_2 divide_line) (str/split-lines input))))
    
