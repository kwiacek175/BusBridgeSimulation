/*
 *  Program: Aplikacja okienkowa : animacja przejazdu autobusow przez waski most
 *     Plik: NarrowBridgeAnimation.java
 *
 *    Autor: Kacper Wiącek 259378
 *     Data: grudzień 2022 r.
 */
package gui;

import data.Bus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NarrowBridgeAnimation extends JPanel {
    private static final long serialVersionUID = 1L;
    NarrowBridgeSimulation bridge;
    public NarrowBridgeAnimation(NarrowBridgeSimulation bridge) {
        this.bridge = bridge;
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Animacja przejazdu przez waski most");
        jFrame.setSize(500, 700);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setLocationRelativeTo(bridge);
        jFrame.setLocation(bridge.getWidth() + 10, 0);
        jFrame.setContentPane(this);
        jFrame.setVisible(true);
        (new Thread(() -> {
            while(true) {
                this.repaint();

                try {
                    Thread.sleep(20L);
                } catch (InterruptedException var2) {
                }
            }
        })).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 40;
        int y = (this.getWidth() - 4 * x) / 3;
        Graphics2D graphics2D = (Graphics2D)g;
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, 0, y, this.getHeight() - 1);
        g.fillRect(this.getWidth() - y - x - 1, 0, y, this.getHeight() - 1);
        g.setColor(new Color(0, 0, 255, 100));
        g.fillRect((this.getWidth() - y) / 2, 0, y, this.getHeight() - 1);
        g.setColor(new Color(255, 0, 0, 50));
        g.fillRect((this.getWidth() - y) / 2 - x, 0, x, this.getHeight() - 1);
        g.fillRect((this.getWidth() + y) / 2, 0, x, this.getHeight() - 1);
        Font font1 = new Font("SansSerif", 1, 14);
        Font font2 = new Font("SansSerif", 1, 32);
        g.setFont(font2);
        graphics2D.rotate(-1.5707963267948966, 0.0, 0.0);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("P A R K I N G         P A R K I N G", -550, 30);
        g.drawString("P A R K I N G         P A R K I N G", -550, this.getWidth() - 10);
        g.setColor(Color.WHITE);
        g.drawString("R O A D       R O A D       R O A D", -550, this.getWidth() / 4 - 10);
        g.drawString("R O A D       R O A D       R O A D", -550, 3 * this.getWidth() / 4 + 30);
        g.drawString("G A T E       G A T E       G A T E", -550, (this.getWidth() - y) / 2 - x + 30);
        g.drawString("G A T E       G A T E       G A T E", -550, (this.getWidth() + y) / 2 + 30);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("  B R I D G E         B R I D G E  ", -550, this.getWidth() / 2 + 10);
        graphics2D.rotate(1.5707963267948966, 0.0, 0.0);
        graphics2D.setFont(font2);
        g.setFont(font1);
        float a = (float)(x / 2);
        long currentTimeMillis = System.currentTimeMillis();
        synchronized(this.bridge.allBuses) {
            float b = (float)x;
            if (this.bridge.getHeight() - 2 * x < this.bridge.allBuses.size() * x) {
                b = (float)(this.bridge.getHeight() - 2 * x) / (float)this.bridge.allBuses.size();
            }

            for(Iterator var13 = this.bridge.allBuses.iterator(); var13.hasNext(); a += b) {
                Bus bus = (Bus)var13.next();
                bus.draw(graphics2D, bus.busStatusTime(currentTimeMillis, x, y), (int)a);
            }

        }
    }

    public void drawBusOnTheLeft(Graphics2D g, int x, int y) {
        int[] tab1 = new int[]{-15 + x, 5 + x, 15 + x, 15 + x, -15 + x, -15 + x};
        int[] tab2 = new int[]{-10 + y, -10 + y, 0 + y, 10 + y, 10 + y, -10 + y};
        g.setColor(Color.BLACK);
        g.fillOval(-15 + x, 7 + y, 10, 10);
        g.fillOval(x, 7 + y, 10, 10);
        BasicStroke _10100111 = new BasicStroke(3.0F);
        g.setStroke(_10100111);
        g.setColor(Color.GREEN);
        g.fillPolygon(tab1, tab2, 5);
        g.setColor(Color.GREEN);
        g.drawPolygon(tab1, tab2, 5);
        g.setColor(Color.BLACK);
        g.drawString("123", -13 + x, 7 + y);
    }

    public void drawBusOnTheRight(Graphics2D g, int x, int y) {
        int[] tab1 = new int[]{15 + x, -5 + x, -15 + x, -15 + x, 15 + x, 15 + x};
        int[] tab2= new int[]{-10 + y, -10 + y, 0 + y, 10 + y, 10 + y, -10 + y};
        g.setColor(Color.BLACK);
        g.fillOval(-10 + x, 7 + y, 10, 10);
        g.fillOval(5 + x, 7 + y, 10, 10);
        BasicStroke _10101111 = new BasicStroke(3.0F);
        g.setStroke(_10101111);
        g.setColor(Color.RED);
        g.setColor(Color.YELLOW);
        g.fillPolygon(tab1, tab2, 5);
        g.setColor(Color.RED);
        g.drawPolygon(tab1, tab2, 5);
        g.setColor(Color.BLACK);
        g.drawString("123", -10 + x, 7 + y);
    }
}