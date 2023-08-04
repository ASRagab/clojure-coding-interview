(ns tokenizer.solutions
  (:require [tokenizer.core :as core])
  (:gen-class))

;; Should return a seq of tokens which consumes the whole input text, if there are invalid characters 
;; remaining then return an empty seq
(defn tokenize [phrase]
  (let [longest-word (first (filter #(core/dict %)
                                    (map #(subs phrase 0 %) (range (count phrase) -1 -1))))
        remaining (subs phrase (count longest-word))]
    (if (nil? longest-word)
      []
      (if (= (count longest-word) (count phrase))
        [longest-word]
        (let [remaining-tokens (tokenize remaining)]
          (if (empty? remaining-tokens)
            []
            (cons longest-word remaining-tokens)))))))

;; Should return all valid tokenizations of the input text, if there are invalid characters
;; remaining then return an empty seq
(defn tokenize-multi [phrase]
  (if (empty? phrase)
    [[]]
    (->> (range 1 (inc (count phrase)))
         (map #(subs phrase 0 %))
         (filter #(core/dict %))
         (mapcat #(map (fn [remaining] (cons % remaining))
                       (tokenize-multi (subs phrase (count %)))))
         (into []))))

(defn -main [& _]
  (println (core/test-single tokenize))
  (println (core/test-multiple tokenize-multi)))
