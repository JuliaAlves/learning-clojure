(ns tic-tac-toe.main 
  (:require [clojure.string :as s]))

(defn init-table []
  [["-" "-" "-"]
   ["-" "-" "-"]
   ["-" "-" "-"]])

(defn print-table [table]
  (mapv println table))

(defn next-player [current-player]
  (if (= current-player "X")
    "O"
    "X"))

(defn play [table player]
  (let [user-input (s/split (read-line) #":")
        row (Integer/parseInt (get user-input 0))
        col (Integer/parseInt (get user-input 1))
        new-table (assoc-in table [row col] player)]
    (print-table new-table)
    new-table))

(defn there-are-positions-empty [table]
  (some (fn [row] (some #(= % "-") row)) table))

(defn is-row-completed [table row]
  (and (not (= (get-in table [row 0]) "-"))
       (apply = (get table row))))

(defn is-column-completed [table col]
  (let [column-values (to-array (map #(get % col) table))]
    (and (not (= (get column-values 0) "-"))
         (apply = column-values))))

(defn is-game-over [table]
  (or (not (there-are-positions-empty table))
      (is-column-completed table 0)
      (is-column-completed table 1)
      (is-column-completed table 1)
      (is-row-completed table 0)
      (is-row-completed table 1)
      (is-row-completed table 2)
      (and (not (= (get-in table [0 0]) "-")) (= (get-in table [0 0]) (get-in table [1 1]) (get-in table [2 2])))
      (and (not (= (get-in table [0 2]) "-")) (= (get-in table [0 2]) (get-in table [1 1]) (get-in table [2 0])))))

(defn round [table player]
  (if (not (is-game-over table))
    (do
      (println "Is" player "turn")
      (flush)
      (recur (play table player) (next-player player)))
    (println "cabo")))

(defn start []
  (let [table (init-table)
        player "X"]
    (print-table table)
    (round table player)))
