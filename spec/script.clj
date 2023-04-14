(ns script
  (:require [clojure.spec.alpha :as s]))

(def email-regex #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")

(s/def :test/name string?)
(s/def :test/phone string?)
(s/def :test/email (s/and string? #(re-matches email-regex %)))

(s/def :test/person1 (s/keys :req [:test/name :test/email]
                            :opt [:test/phone]))

(def person1 {:test/name "a" :phone "123" :test/email "dsa@dsa.com"})

(s/def :test/person2 (s/keys :req-un [:test/name :test/email]
                            :opt-un [:test/phone]))

(def person2 {:name "a" :phone "123" :email "dsa@dsa.com"})

;(s/def ::person2 (s/keys :req-un [:test/name :test/email]
;                         :opt-un [:test/phone]))

; in other ns
; :script/person2 instead of :test/person2

(s/def :test/metadata (s/map-of keyword? string?))
(s/def :test/person3 (s/keys :req-un [:test/name :test/email :test/metadata]
                             :opt-un [:test/phone]))

(def person3 {:name "a" :phone "123" :email "dsa@dsa.com" :metadata {:a "b"}})

