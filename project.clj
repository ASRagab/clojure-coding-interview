(defproject github "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/core.async "1.2.603"]]
  :target-path "target/%s"
  :profiles {:github {:main ^:skip-aot github.solutions}
             :tokenizer {:main ^:skip-aot tokenizer.solutions}})
