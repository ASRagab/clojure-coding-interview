(ns github.core
  (:require [clojure.string :as str])
  (:gen-class))

(defmacro ??? []
  `(throw (UnsupportedOperationException. "Not implemented")))

(def repos-users
  {:org1 {:repo1 {:user1 15 :user2 7 :user3 4}
          :repo2 {:user1 5 :user2 9 :user3 15}}
   :org2 {:repo1 {:user1 10 :user2 5 :user3 6}
          :repo2 {:user1 15 :user2 3 :user3 13}
          :repo3 {:user1 4 :user2 8 :user3 7}}})

(defn get-repo [[org repo]]
  (if-let [result (get-in repos-users [org repo])]
    result
    (throw (Exception. (str "Repo not found for path " org "/" repo)))))

(defn get-commit-count-sync [keys]
  (let [[org repo user] keys 
        result (get-repo [org repo])]
       (if-let [count (get result user)]
         count
         0)))

(defn get-commit-count-async [keys]
  (Thread/sleep 1000) ; Simulate some API call
  (get-commit-count-sync keys))

(defn get-commit-count-with-user-async [keys]
  (let [[_ _ user] keys]
    {user (get-commit-count-async keys)}))

(defn to-triples [pairs]
  (map (fn [[org-repo user]]
         (let [[org repo] (str/split org-repo #"/")]
           [(keyword org) (keyword repo) (keyword user)]))
       pairs))

(def org-repo-users
  (to-triples [["org1/repo1" "user2"]
               ["org1/repo2" "user3"]
               ["org2/repo1" "user1"]
               ["org2/repo2" "user2"]
               ["org2/repo3" "user2"]
               ]))

;; Exercise 1: Get the total number of commits for users in all repos. Assume the happy path.
(defn exercise1 [repo-user-list] (???))

;; Exercise 2: Get the total number of commits for a particular user for all repos. Ignore repos that do not exist.
(defn exercise2 [repo-user-list user] (???))

;; Exercise 3: Get the total number of commits for each user as map of user and commits. Ignore repos that do not exist.
(defn exercise3 [repo-user-list] (???))
              

(defn -main 
  [& _]
  (doall
   [ ;; (time (exercise0 org-repo-users))
    (time (exercise1 org-repo-users))
    (time (exercise2 org-repo-users :user2))
    (time (exercise2 org-repo-users :user3))
    (time (exercise2 org-repo-users :user1))
    (time (exercise3 org-repo-users))]))

(-main)