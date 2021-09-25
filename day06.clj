(ns aoc2020.core
  (:require [clojure.string :as str]
            [ clojure.set :as set]))

;; Day 6


(def sample_input "abc

a
b
c

ab
ac

a
a
a
a

b")
(def input (slurp "resources/day06.in"))

(defn split_groups
  [answers]
  (str/split answers #"\n\n"))

(defn strip_whitespace
  [group_answers]
  (str/replace group_answers #"\n| " ""))

(defn count_group_answers
  [group_answers]
  (count (into #{} group_answers)))

(defn groups_sum
  [groups_answers]
  (apply + 
         (map (comp count_group_answers strip_whitespace) 
         (split_groups groups_answers))))

(groups_sum sample_input)
(groups_sum input)

; part 2
(map str/split-lines (split_groups sample_input))

(set/intersection (into #{} "hello") 
                  (into #{} "world"))

(map #(into #{} %) ["abc" "bcd"])

(defn count_unanimous_answers
  [group_answers]
  (let [answer_sets (map #(into #{} %) group_answers)
        all_answered (apply set/intersection answer_sets)]
    (count all_answered)))

(map count_unanimous_answers (map str/split-lines (split_groups sample_input)))

(defn sum_u_answers
  [groups]
  (apply + (map count_unanimous_answers
             (map str/split-lines (split_groups groups)))))

(sum_u_answers sample_input)
(sum_u_answers input)
