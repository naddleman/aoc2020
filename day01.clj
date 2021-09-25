(ns aoc2020.core)

;; Day 1

(def sample_input
  [3
   1721
   979
   366
   299
   675
   1456])

(defn verify_sum
  "checks if x + y = z"
  [x y z]
  (if
    (= (+ x y) z)
    [x y]
    nil))

(defn verify_head
  "checks if any elements sum to s when added to head of vector"
  [v s]
  (let [sat (filter 
              #(verify_sum (first v) % s) v)]
    (if (not (empty? sat))
      (list (first v) (first sat))
      nil)))

(defn find_in_vector
  "finds pair of elemnts x,y in v so x + y = z"
  [v s]
  (cond
    (empty? v) nil
    (verify_head v s) (verify_head v s)
    :else (find_in_vector (rest v) s)))
    
(defn found_product
  "finds pair as above and returns their product"
  [v s]
  (apply * (find_in_vector v s)))

(found_product
  (map read-string
       (clojure.string/split-lines (slurp "resources/day01.in")))
  2020)

(defn triple_sum
  "O(n^3) version"
  [v s]
  (let [target (- s (first v))
        tail (rest v)
        pair (find_in_vector tail target)]
    (if pair
      (cons (first v) pair)
      (triple_sum tail s))))

(apply * (triple_sum
  (map read-string
       (clojure.string/split-lines (slurp "resources/day01.in")))
  2020))

