/*
 *  Program: Operacje na obiektach klasy Bus
 *     Plik: Bus.java
 *           -definicja publicznej klasy Bus
 *
 *    Autor: Kacper Wiącek 259378
 *     Data: grudzień 2022r.
 */

package data;

import gui.NarrowBridgeSimulation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.ThreadLocalRandom;


public class Bus implements Runnable {
    public static final int MIN_BOARDING_TIME = 1000;
    public static final int MAX_BOARDING_TIME = 10000;
    public static final int GETTING_TO_BRIDGE_TIME = 500;
    public static final int CROSSING_BRIDGE_TIME = 3000;
    public static final int GETTING_PARKING_TIME = 500;
    public static final int UNLOADING_TIME = 500;
    private static int numberOfBuses = 0;
    private NarrowBridgeSimulation bridge;
    private int id;
    private BusDirection dir;
    private BusStatus status;
    private long currentTimeMillis = System.currentTimeMillis();

    public BusDirection getDir() {return dir;}
    public void setDir(BusDirection dir) {this.dir = dir;}

    public BusStatus getStatus() {return status;}
    public void setStatus(BusStatus status) {this.status = status;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public NarrowBridgeSimulation getBridge() {return bridge;}
    public void setBridge(NarrowBridgeSimulation bridge) {this.bridge = bridge;}

    public long getCurrentTimeMillis() {return currentTimeMillis;}
    public void setCurrentTimeMillis(long currentTimeMillis) {this.currentTimeMillis = currentTimeMillis;}

    public static void sleep(int millis) {
        try {
            Thread.sleep((long)millis);
        } catch (InterruptedException e) {
        }
    }

    public static void sleep(int min_millis, int max_millis) {
        sleep(ThreadLocalRandom.current().nextInt(min_millis, max_millis));
    }

    public Bus(NarrowBridgeSimulation bridge, BusDirection dir) {
        this.bridge = bridge;
        this.dir = dir;
        this.id = ++numberOfBuses;
    }

    public int busStatusTime(long a, int b, int c) {
        int d;
        int e;
        switch (this.status) {
            case BOARDING:
                return b / 2;
            case GO_TO_THE_BRIDGE:
                d = b / 2;
                e = b / 2 + b + c;
                return (int)((long)d + (long)(e - d) * (a - this.currentTimeMillis) / 500L);
            case GET_ON_THE_BRIDGE:
                return b / 2 + b + c;
            case RIDE_THE_BRIDGE:
                d = b / 2 + b + c;
                e = b / 2 + 2 * (b + c);
                return (int)((long)d + (long)(e - d) * (a - this.currentTimeMillis) / 3000L);
            case GET_OFF_THE_BRIDGE:
                return b / 2 + 2 * (b + c);
            case GO_TO_THE_PARKING:
                d = b / 2 + 2 * (b + c);
                e = b / 2 + 3 * (b + c);
                return (int)((long)d + (long)(e - d) * (a - this.currentTimeMillis) / 500L);
            case UNLOADING:
                return b / 2 + 3 * (b + c);
            default:
                return 0;
        }
    }

    public void printBusInfo(String massage) {
        String busInfo = "Bus[" + this.id + "->" + this.dir + "]: " + massage + "\n";
        this.bridge.fieldBridgeArea.insert(busInfo, 0);
    }

    public void boarding() {
        synchronized(this) {
            this.currentTimeMillis = System.currentTimeMillis();
            this.status = BusStatus.BOARDING;
        }

        this.printBusInfo("Czeka na nowych pasazerow");
        sleep(1000, 10000);
    }

    public void goToTheBridge() {
        synchronized(this) {
            this.currentTimeMillis = System.currentTimeMillis();
            this.status = BusStatus.GO_TO_THE_BRIDGE;
        }

        this.printBusInfo("Jazda w strone mostu");
        sleep(500);
    }

    public void rideTheBridge() {
        synchronized(this) {
            this.currentTimeMillis = System.currentTimeMillis();
            this.status = BusStatus.RIDE_THE_BRIDGE;
        }

        this.printBusInfo("Przejazd przez most");
        sleep(3000);
    }

    public void goToTheParking() {
        synchronized(this) {
            this.currentTimeMillis = System.currentTimeMillis();
            this.status = BusStatus.GO_TO_THE_PARKING;
        }

        this.printBusInfo("Jazda w strone koncowego parkingu");
        sleep(500);
    }

    public void unloading() {
        synchronized(this) {
            this.currentTimeMillis = System.currentTimeMillis();
            this.status = BusStatus.UNLOADING;
        }

        this.printBusInfo("Rozładunek pasazerow");
        sleep(500);
    }

    public void run() {
        synchronized(this.bridge.allBuses) {
            this.bridge.allBuses.add(this);
        }

        this.boarding();
        this.goToTheBridge();
        this.bridge.getOnTheBridge(this);
        this.rideTheBridge();
        this.bridge.getOffTheBridge(this);
        this.goToTheParking();
        this.unloading();
        synchronized(this.bridge.allBuses) {
            this.bridge.allBuses.remove(this);
        }
    }

    public void draw(Graphics2D g, int x, int y) {
        int[] tab1;
        int[] tab2;
        Color color;
        if (this.dir == BusDirection.EAST) {
            tab1 = new int[]{-15 + x, 5 + x, 15 + x, 15 + x, -15 + x, -15 + x, -15 + x, x, -13 + x};
            tab2 = new int[]{-10 + y, -10 + y, 0 + y, 10 + y, 10 + y, -10 + y};
            color = Color.GREEN;
        } else {
            x = this.bridge.narrowBridgeAnimation.getWidth() - x;
            tab1 = new int[]{15 + x, -5 + x, -15 + x, -15 + x, 15 + x, 15 + x, -10 + x, 5 + x, -10 + x};
            tab2 = new int[]{-10 + y, -10 + y, 0 + y, 10 + y, 10 + y, -10 + y};
            color = Color.RED;
        }

        g.setColor(Color.BLACK);
        g.fillOval(tab1[6], 7 + y, 10, 10);
        g.fillOval(tab1[7], 7 + y, 10, 10);
        BasicStroke _11010111 = new BasicStroke(3.0F);
        g.setStroke(_11010111);
        if (this.status != BusStatus.GET_ON_THE_BRIDGE && this.status != BusStatus.GET_OFF_THE_BRIDGE) {
            g.setColor(color);
        } else {
            g.setColor(Color.YELLOW);
        }

        g.fillPolygon(tab1, tab2, 5);
        g.setColor(color);
        g.drawPolygon(tab1, tab2, 5);
        g.setColor(Color.BLACK);
        g.drawString("" + this.id, tab1[8], 7 + y);
    }
}
