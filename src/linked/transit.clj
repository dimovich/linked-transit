(ns linked.transit
  (:require [cognitect.transit :as t]
            [linked.set]
            [linked.map])
  
  (:import [linked.set LinkedSet]
           [linked.map LinkedMap]
           [java.io ByteArrayInputStream ByteArrayOutputStream]))



(def read-handlers
  {"linked/set" (t/read-handler linked.set/->linked-set)
   "linked/map" (t/read-handler linked.map/->linked-map)})



(def write-handlers
  {LinkedSet (t/write-handler "linked/set" (partial into []))
   LinkedMap (t/write-handler "linked/map" (partial into []))})




(defn read-transit [is]
  (t/read (t/reader is :json { :handlers read-handlers })))


(defn read-transit-str [^String s]
  (read-transit (ByteArrayInputStream. (.getBytes s "UTF-8"))))


(defn write-transit [o os]
  (t/write (t/writer os :json { :handlers write-handlers }) o))


(defn write-transit-bytes ^bytes [o]
  (let [os (ByteArrayOutputStream.)]
    (write-transit o os)
    (.toByteArray os)))
    

(defn write-transit-str [o]
  (String. (write-transit-bytes o) "UTF-8"))
