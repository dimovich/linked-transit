(ns linked.transit
  (:require [cognitect.transit :as t]
            [linked.set]
            [linked.map])
  (:import [linked.set LinkedSet]
           [linked.map LinkedMap]))



(def read-handlers
  {"linked/set" (t/read-handler linked.set/->linked-set)
   "linked/map" (t/read-handler linked.map/->linked-map)})



(def write-handlers
  {LinkedSet (t/write-handler "linked/set" (partial into []))
   LinkedMap (t/write-handler "linked/map" (partial into []))})


;;PersistentSortedSet (get t/default-write-handlers java.util.List)

