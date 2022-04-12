package lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;


public class Circles extends JFrame {
    private static final int FW = 500; // frame-width
    private static final int FH = 400; // frame-height

    public Circles(){
        setLayout(new BorderLayout()); // layout of frame
        setSize(FW, FH);                // set width & height of frame
        setTitle("Graphics Primitives 2D");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawCircles panel = new DrawCircles(FW, FH);
        add(panel, BorderLayout.CENTER); // add panel in frame

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Circles();
    }
}
class DrawCircles extends JPanel {

    private static int PW; // panel-width
    private static int PH; // panel-height

    public DrawCircles(int W , int H) {
        PW = W;
        PH = H;
        setPreferredSize(new Dimension(PW, PH)); // set width & height of panel
        this.setBackground(Color.white);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int cirW=200;
        int cirH=200;

        g2.setColor(Color.red);
        g2.drawOval(30, 100, cirW, cirH);

        g2.setColor(Color.green);
        g2.fillOval(280, 100, cirW, cirH);


    }
}