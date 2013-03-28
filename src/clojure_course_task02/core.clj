(ns clojure-course-task02.core
  (:import java.io.File)
  (:gen-class))

(def results (ref #{}))

(defn check-name [name pat]
  (if-let [match (re-matches (re-pattern pat) name)]
    (dosync (alter results conj match))))

(defn make-search! [file-name d]
  (doall (pmap (fn [f]
    (if (.isDirectory f)
      (make-search! file-name f)
      (check-name (.getName f) file-name)))
    (.listFiles d))))

(defn find-files [file-name path]
  (make-search! file-name (File. path))
  @results)

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path)))))
      (shutdown-agents))