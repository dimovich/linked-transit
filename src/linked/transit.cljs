(ns linked.transit
  (:require [cognitect.transit :as t]
            [linked.set]
            [linked.map]))



(def read-handlers
  {"linked/set" (fn [s] (linked.set/->linked-set s))
   "linked/map" (fn [m] (linked.map/->linked-map m))})



(def write-handlers
  {linked.set/LinkedSet (t/write-handler
                         (constantly "linked/set") (partial into []))
   linked.map/LinkedMap (t/write-handler
                         (constantly "linked/map") (partial into []))})





(defn read-transit-str [s]
  (t/read (t/reader :json { :handlers read-handlers }) s))


(defn write-transit-str [o]
  (t/write (t/writer :json { :handlers write-handlers }) o))
