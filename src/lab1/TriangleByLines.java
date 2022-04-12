package lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class TriangleByLines extends JFrame {
    private static final int FW = 400; // frame-width
    private static final int FH = 400; // frame-height
    public TriangleByLines(){

        setLayout(new BorderLayout()); // layout of frame
        setSize(FW, FH);                // set width & height of frame
        setTitle("Graphics Primitives 2D");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawTriangleByLines panel = new DrawTriangleByLines(FW, FH); ///////////////////////
        add(panel, BorderLayout.CENTER); // add panel in frame

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new TriangleByLines();
    }
}
class DrawTriangleByLines extends JPanel {
    private static int PW; // panel-width
    private static int PH; // panel-height
    public DrawTriangleByLines(int W , int H){
        PW = W;
        PH = H;
        setPreferredSize(new Dimension(PW, PH)); // set width & height of panel
        this.setBackground(Color.white);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        g2.drawLine(50, 50, 250, 50); // AB
        g2.drawLine(250, 50, 250, 150); // BC
        g2.drawLine(50, 50, 250, 150); // AC



    }
}
