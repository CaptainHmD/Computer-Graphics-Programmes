package lab5;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class RectangleAnimation extends JFrame {

    private static final int FRAME_WIDTH = 800; // frame-width
    private static final int FRAME_HEIGHT = 800; // frame-height

    public RectangleAnimation() {

        setLayout(new BorderLayout());     // layout of frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT); // set width & height of frame
        setTitle("Rectangle Animation 2D");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PanelRectangleAnimation panel = new PanelRectangleAnimation();
        add(panel, BorderLayout.CENTER); // add panel in frame

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {

        new RectangleAnimation();
    }

}

class PanelRectangleAnimation extends JPanel implements Runnable {

    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 400;
    private static final int RW = 40;
    private static final int RH = 40;

    int x=PANEL_WIDTH/2, y, x2, y2 = PANEL_HEIGHT / 2;

    Rectangle2D.Double rec;

    Thread mythread;

    public PanelRectangleAnimation() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.white);

        mythread = new Thread(this);
        mythread.start();
    }

    boolean direction = false;
    int count = 0;

    public void run() {

        // animation loop
        boolean flag = true;
        while (flag) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }

            // x = x + 5; // 5, 10, 15, 20, 25 ,..., 400
            if(direction) {
                x2-=5;
                y-=5;
            }else{
                x2 += 5;
                y = y + 5;

            }


            if ( y > PANEL_HEIGHT || x2 > PANEL_WIDTH ||x2<-40||y<-40) {
                //x = 0;
                if (direction) {
                    y = 0;
                    x2 = 0;
                } else {
                    y=PANEL_HEIGHT;
                    x2=PANEL_WIDTH;
                }
                if(count%2==0)
                    direction=true;
                else
                    direction=false;
                count++;
            }

            repaint();
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D g3 = (Graphics2D) g;

        g2.setColor(Color.red);
        rec = new Rectangle2D.Double(x, y, RW, RH);
        g2.fill(rec);

        g3.setColor(Color.blue);
        rec = new Rectangle2D.Double(x2, y2, RW, RH);
        g3.fill(rec);
    }
}