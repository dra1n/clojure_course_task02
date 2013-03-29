(ns clojure-course-task02.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(defn files [d]
  (pmap #(if (.isDirectory %) (files %) (.getName %))
    (.listFiles d)))

(defn find-files [file-name path]
  (->>
   (files (io/file path))
   (flatten)
   (filter #(re-matches (re-pattern file-name) %))))

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