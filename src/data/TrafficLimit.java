/*
 *  Program: Operacje na obiektach klasy Bus
 *     Plik: TrafficLimit.java
 *           - definicja typu wyliczeniowego TrafficLimit
 *
 *    Autor: Kacper Wiącek 259378
 *     Data: grudzień 2022 r.
 */

package data;

public enum TrafficLimit {

    NO_LIMIT_TRAFFIC ("Bez ograniczeń"),
    TWO_WAY_TRAFFIC ("Ruch dwukierunkowy (max 3 busy)"),
    ONE_WAY_TRAFFIC ("Ruch jednokierunkowy (max 3 busy)"),
    LIMITED_TRAFFIC ("Ruch ograniczony (max 1 bus)");

    String limit_name;

    private TrafficLimit(String limit_name) {
        this.limit_name = limit_name;
    }

    public String toString() {
        return this.limit_name;
    }
}
