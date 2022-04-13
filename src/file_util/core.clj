(ns file-util.core)

(defn read-file [filename]
  "Load file into memory as string. All IO is just in this file"
  (slurp (str "./../../" filename)))

