/*
 *  Program: Aplikacja okienkowa : symulacja przejazdu autobusow przez waski most
 *     Plik: NarrowBridgeSimulation.java
 *
 *    Autor: Kacper Wiącek 259378
 *     Data: grudzień 2022 r.
 */

package gui;

import data.Bus;
import data.BusDirection;
import data.BusStatus;
import data.TrafficLimit;

import java.awt.Font;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NarrowBridgeSimulation extends JFrame {
    private static final long serialVersionUID = 1L;
    private static int TRAFFIC = 2000;
    private static int TIME = 50;

    public List<Bus>  allBuses = new LinkedList();
    List<Bus> busesWaiting = new LinkedList();
    List<Bus> busesOnTheBridge = new LinkedList();
    TrafficLimit trafficLimit;
    BusDirection dir;
    int id;
    JComboBox<TrafficLimit> trafficLimitJComboBox;
    JTextField fieldBusesOnTheBridge;
    JTextField fieldBusesWaiting;
    public JTextArea fieldBridgeArea;
    public NarrowBridgeAnimation narrowBridgeAnimation;

    public static void main(String[] args) {
        NarrowBridgeSimulation bridge = new NarrowBridgeSimulation();

        while(true) {
            BusDirection busDirection;
            if (ThreadLocalRandom.current().nextInt(0, 101) < TIME) {
                busDirection = BusDirection.EAST;
            } else {
                busDirection = BusDirection.WEST;
            }

            Bus bus = new Bus(bridge,busDirection );
            (new Thread(bus)).start();

            try {
                Thread.sleep((long)(5500 - TRAFFIC));
            } catch (InterruptedException var5) {
            }
        }
    }

    public void printBridgeInfo(Bus bus, String massage) {
        StringBuilder sb = new StringBuilder();
        sb.append("Bus[" + bus.getId() + "->" + bus.getDir() + "]  ");
        sb.append(massage + "\n");
        this.fieldBridgeArea.insert(sb.toString(), 0);
        sb = new StringBuilder();
        Iterator var5 = this.busesOnTheBridge.iterator();

        Bus tmp;
        while(var5.hasNext()) {
            tmp = (Bus)var5.next();
            sb.append(tmp.getId());
            sb.append(" ");
        }

        this.fieldBusesOnTheBridge.setText(sb.toString());
        sb = new StringBuilder();
        var5 = this.busesWaiting.iterator();

        while(var5.hasNext()) {
            tmp = (Bus)var5.next();
            sb.append(tmp.getId());
            sb.append(" ");
        }

        this.fieldBusesWaiting.setText(sb.toString());
    }

    public synchronized void getOnTheBridge(Bus bus) {
        synchronized(bus) {
            bus.setCurrentTimeMillis(System.currentTimeMillis());
            bus.setStatus(BusStatus.GET_ON_THE_BRIDGE);
        }

        boolean flag = true;

        label71:
        while(true) {
            switch (this.trafficLimit) {
                case NO_LIMIT_TRAFFIC:
                    break label71;
                case TWO_WAY_TRAFFIC:
                    if (this.busesOnTheBridge.size() < 3) {
                        break label71;
                    }
                    break;
                case ONE_WAY_TRAFFIC:
                    if (this.busesOnTheBridge.isEmpty() && this.busesWaiting.isEmpty()) {
                        this.id = 0;
                        break label71;
                    }

                    if (this.busesOnTheBridge.isEmpty()) {
                        if (bus.getDir() != this.dir || bus.getDir() == this.dir && this.id < 10) {
                            break label71;
                        }
                    } else if (bus.getDir() == this.dir && this.id < 10 && this.busesOnTheBridge.size() < 3) {
                        break label71;
                    }
                    break;
                case LIMITED_TRAFFIC:
                    if (this.busesOnTheBridge.isEmpty()) {
                        break label71;
                    }
            }

            this.busesWaiting.add(bus);
            if (flag) {
                this.printBridgeInfo(bus, "CZEKA NA WJAZD");
                flag = false;
            }

            try {
                this.wait();
            } catch (InterruptedException var4) {
            }

            this.busesWaiting.remove(bus);
        }

        if (this.dir == bus.getDir()) {
            ++this.id;
        } else {
            this.dir = bus.getDir();
            this.id = 1;
        }

        this.busesOnTheBridge.add(bus);
        this.printBridgeInfo(bus, "WJEZDZA NA MOST");
    }

    public synchronized void getOffTheBridge(Bus bus) {
        synchronized(bus) {
            bus.setCurrentTimeMillis(System.currentTimeMillis());
            bus.setStatus(BusStatus.GET_OFF_THE_BRIDGE);
        }

        this.busesOnTheBridge.remove(bus);
        this.printBridgeInfo(bus, "OPUSZCZA MOST");
        this.notifyAll();
    }

    public NarrowBridgeSimulation() {
        super("Symulacja przejazdu przez waski most");
        this.trafficLimit = TrafficLimit.LIMITED_TRAFFIC;
        this.dir = BusDirection.WEST;
        this.id = 0;
        this.trafficLimitJComboBox = new JComboBox(TrafficLimit.values());
        this.fieldBusesOnTheBridge = new JTextField(30);
        this.fieldBusesWaiting = new JTextField(30);
        this.fieldBridgeArea = new JTextArea(21, 50);
        this.setSize(550, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(3);
        JMenuItem menuAuthor = new JMenuItem("Autor");
        menuAuthor.addActionListener((action) -> {
            JOptionPane.showMessageDialog(this, "Autor: Kacper Wiacek\n Data: 21 grudnia 2022 r.");
        });
        JMenuItem menuEnd = new JMenuItem("Zakończ");
        menuEnd.addActionListener((action) -> {
            System.exit(0);
        });
        JMenu menu = new JMenu("Menu");
        menu.add(menuAuthor);
        menu.add(menuEnd);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        JPanel panel = new JPanel();
        Font font = new Font("MonoSpaced", 1, 16);
        this.trafficLimitJComboBox.setSelectedItem(this.trafficLimit);
        this.trafficLimitJComboBox.addActionListener((a) -> {
            this.trafficLimit = (TrafficLimit) this.trafficLimitJComboBox.getSelectedItem();
        });
        final JSlider sliderTraffic = new JSlider(0, 500, 5000, TRAFFIC);
        sliderTraffic.setFont(font);
        sliderTraffic.setSize(300, 20);
        sliderTraffic.setMajorTickSpacing(1000);
        sliderTraffic.setMinorTickSpacing(500);
        sliderTraffic.setPaintLabels(true);
        Hashtable<Integer, JLabel> traffic = new Hashtable();
        traffic.put(500, new JLabel("Male"));
        traffic.put(5000, new JLabel("Duze"));
        sliderTraffic.setLabelTable(traffic);
        sliderTraffic.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                NarrowBridgeSimulation.TRAFFIC = sliderTraffic.getValue();
            }
        });
        final JSlider sliderDirection = new JSlider(0, 0, 100, 50);
        sliderDirection.setFont(font);
        sliderDirection.setSize(300, 20);
        sliderDirection.setMajorTickSpacing(1000);
        sliderDirection.setMinorTickSpacing(500);
        sliderDirection.setPaintLabels(true);
        Hashtable<Integer, JLabel> direction = new Hashtable();
        direction.put(0, new JLabel("Zachod"));
        direction.put(100, new JLabel("Wschod"));
        sliderDirection.setLabelTable(direction);
        sliderDirection.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                NarrowBridgeSimulation.TIME = sliderDirection.getValue();
            }
        });
        JLabel labelTrafficLimit = new JLabel("Ograniczenie ruchu:");
        JLabel labelTraffic = new JLabel("Natezenie ruchu:");
        JLabel labelDirection = new JLabel(" Kierunek ruchu:");
        JLabel labelOnTheBridge = new JLabel("       Na moście:");
        JLabel labelQueue = new JLabel("         Kolejka:");
        labelTrafficLimit.setFont(font);
        labelTraffic.setFont(font);
        labelDirection.setFont(font);
        labelOnTheBridge.setFont(font);
        labelQueue.setFont(font);
        this.fieldBridgeArea.setFont(font);
        this.fieldBusesOnTheBridge.setFont(font);
        this.fieldBusesWaiting.setFont(font);
        panel.add(labelTrafficLimit);
        panel.add(this.trafficLimitJComboBox);
        panel.add(labelTraffic);
        panel.add(sliderTraffic);
        panel.add(labelDirection);
        panel.add(sliderDirection);
        panel.add(labelOnTheBridge);
        panel.add(this.fieldBusesOnTheBridge);
        panel.add(labelQueue);
        panel.add(this.fieldBusesWaiting);
        this.fieldBridgeArea.setLineWrap(true);
        this.fieldBridgeArea.setWrapStyleWord(true);
        JScrollPane jScrollPane = new JScrollPane(this.fieldBridgeArea, 22, 30);
        panel.add(jScrollPane);
        this.setContentPane(panel);
        this.setVisible(true);
        this.narrowBridgeAnimation = new NarrowBridgeAnimation(this);
    }
}
