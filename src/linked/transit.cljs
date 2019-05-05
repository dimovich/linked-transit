(ns linked.transit
  (:require [cognitect.transit :as t]
            [linked.set]
            [linked.map]))




(def read-handlers
  {"linked/set" linked.set/->linked-set
   "linked/map" linked.map/->linked-map})




(def write-handlers
  {linked.set/LinkedSet (t/write-handler (constantly "linked/set")
                                         (partial into []))
   linked.map/LinkedMap (t/write-handler (constantly "linked/map")
                                         (partial into []))})


;;pss/BTSet (t/ListHandler.)

