/*
 *  Program: Operacje na obiektach klasy Bus
 *     Plik: BusStatus.java
 *           - definicja typu wyliczeniowego Status
 *
 *    Autor: Kacper Wiącek 259378
 *     Data: grudzień 2022 r.
 */

package data;

public enum BusStatus {
    BOARDING,
    GO_TO_THE_BRIDGE,
    GET_ON_THE_BRIDGE,

    RIDE_THE_BRIDGE,
    GET_OFF_THE_BRIDGE,
    GO_TO_THE_PARKING,
    UNLOADING;

    private BusStatus() {
    }
}
