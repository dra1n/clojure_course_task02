(ns clojure-course-task02.core
  (:import java.io.File)
  (:gen-class))


(defn find-files [file-name path]
  (doseq [f (.listFiles (File. path))]
    (if (.isDirectory f)
      (find-files file-name f)
      (println (re-matches (re-pattern file-name) (.getName f))))))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))

(def d (File. "./"))

(.listFiles d)

