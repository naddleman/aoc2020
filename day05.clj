(ns aoc2020.core
  (:require [clojure.string :as str]
            [ clojure.set :as set]))

;; Day 5

(def sample_input
  "FBFBBFFRLR
BFFFBBFRRR
FFFBBBFRRR
BBFFBBFRLL")

(def input (slurp "resources/day05.in"))

(defn pass_to_id
  "doing this in the simplest way even though it will probably need to
  be redone for pt. 2"
  [pass]
  (let [binary (str/replace (str/replace pass #"F|L" "0") #"B|R" "1")]
    (read-string (str "2r" binary))))
    
(map pass_to_id (str/split-lines sample_input))
(apply max (map pass_to_id (str/split-lines input)))

; part 2 

(def seats (sort (map pass_to_id (str/split-lines input))))

(prn seats)
(set/difference (into #{} (range 12 872)) (into #{} seats))

;ez
