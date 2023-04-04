(ns code-cracker.main 
  (:require [clojure.string :as s]))

(def dictionary {
"!" "a" 
")" "b" 
"\"" "c" 
"(" "d" 
"£" "e" 
"*" "f" 
"%" "g" 
"&" "h" 
">" "i" 
"<" "j" 
"@" "k" 
"a" "l" 
"b" "m" 
"c" "n" 
"d" "o" 
"e" "p" 
"f" "q" 
"g" "r" 
"h" "s" 
"i" "t" 
"j" "u" 
"k" "v" 
"l" "w" 
"m" "x" 
"n" "y" 
"o" "z"
" " " "})

(defn get-real-char [char]
  (or (get dictionary (str char))
      char))

(defn decrypt [input]
  (let [decrypted-chars (map get-real-char input)
        decrypted-str (s/join "" decrypted-chars)]
    decrypted-str))
