/*
 *  Program: Operacje na obiektach klasy Bus
 *     Plik: BusDirection.java
 *           - definicja typu wyliczeniowego Direction
 *
 *    Autor: Kacper Wiącek 259378
 *     Data: grudzień 2022 r.
 */

package data;

public enum BusDirection {
    EAST,
    WEST;

    private BusDirection() {
    }

    public String toString() {
        switch (this) {
            case EAST:
                return "W";
            case WEST:
                return "Z";
            default:
                return "";
        }
    }
}
