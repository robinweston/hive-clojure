(defproject hive-clojure "0.1.0-SNAPSHOT"
  :description "Game engine for Hive"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot hive-clojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
