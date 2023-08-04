(ns github.solutions
  (:require [github.core :as core])
  (:gen-class))

;; Exercise 1: Get the total number of commits for users in all repos. Assume the happy path.
(defn exercise1 [repo-user-list]
  (let [futures (map #(future (core/get-commit-count-async %)) repo-user-list)
        sequenced (future (doall (map deref futures)))]
    (reduce + 0 @sequenced)))

;; Exercise 2: Get the total number of commits for a particular user for all repos. Ignore repos that do not exist.
(defn recover-with [fut default]
  (future (try @fut (catch Exception _ default))))


(defn exercise2 [repo-user-list user]
  (let [filtered-users (filter (fn [[_ _ u]] (= u user)) repo-user-list)
        futures (map #(recover-with (future (core/get-commit-count-async %)) 0) filtered-users)
        sequenced (future (doall (map deref futures)))]
    (reduce + 0 @sequenced)))

;; Exercise 3: Get the total number of commits for each user as map of user and commits. Ignore repos that do not exist.
(defn exercise3 [repo-user-list]
  (let [futures (map #(recover-with (future (core/get-commit-count-with-user-async %)) 0) repo-user-list)
        sequenced (future (doall (map deref futures)))]
    (reduce (partial merge-with +) {} @sequenced)))


(defn -main [& _]
  (time (println (exercise1 core/org-repo-users)))
  (time (println (exercise2 core/org-repo-users :user2)))
  (time (println (exercise2 core/org-repo-users :user3)))
  (time (println (exercise2 core/org-repo-users :user1)))
  (time (println (exercise3 core/org-repo-users)))
  (shutdown-agents))
