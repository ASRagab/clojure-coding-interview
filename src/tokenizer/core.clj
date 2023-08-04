(ns tokenizer.core
  (:gen-class))

(def dict #{"small", "red" "house" "car" "cart" "horse" "ear" "plug" "earplug" "plugin" "in"})

;; Should return a list of tokens which consumes the whole input text, if there are invalid characters 
;; remaining then return an empty list
(defn tokenize [phrase])

;; Should return all valid tokenizations of the input text, if there are invalid characters
;; remaining then return an empty list
(defn tokenize-multi [phrase])

(defn test-single [fn]
  (println (fn "smallredhouse")) ;; should be ("small" "red" "house")
  (println (fn "AAAsmallredhouse")) ;; should be []
  (println (fn "smallredhouseAAA")) ;; should be []
  (println (fn "carthorse")) ;; should be ("car" "horse")
  (println (fn "earplugin"))) ;; should be ("earplug" "in")


(defn test-multiple [fn]
  (println (fn "smallredhouse")) ;; should be [("small" "red" "house")]
  (println (fn "AAAsmallredhouse")) ;; should be []
  (println (fn "smallredhouseAAA")) ;; should be []
  (println (fn "carthorse")) ;; should be [("car" "horse")]
  (println (fn "earplugin"))) ;; should be [("earplug" "in") ("ear" "plug" "in") ("ear" "plugin")]
