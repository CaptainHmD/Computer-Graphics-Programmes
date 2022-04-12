package lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class DrawGraphics extends JFrame {

    private static final int FW = 600; // frame-width
    private static final int FH = 600; // frame-height

    public DrawGraphics() {

        setLayout(new BorderLayout()); // layout of frame
        setSize(FW, FH);                // set width & height of frame
        setTitle("Graphics Primitives 2D");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawGraphicsPanel panel = new DrawGraphicsPanel(FW, FH);
        add(panel, BorderLayout.CENTER); // add panel in frame

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new DrawGraphics();
    }
}


class DrawGraphicsPanel extends JPanel{

    private static int PW; // panel-width
    private static int PH; // panel-height

    public DrawGraphicsPanel(int W, int H)
    {
        PW = W;
        PH = H;
        setPreferredSize(new Dimension(PW, PH)); // set width & height of panel
        this.setBackground(Color.white);

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;


        // Line
        //drawLine(int x1, int y1, int x2, int y2)
        g2.drawLine(50, 50, 200, 150);

        g2.setColor(Color.blue);
        g2.drawLine(200, 150, 400, 70);

        // Rectangle
        //drawRect(int x, int y, int width, int height).
        int recW=100;
        int recH=150;
        g2.setColor(Color.red);
        g2.drawRect(100, 200, recW, recH);

        g2.setColor(Color.green);
        g2.fillRect(250, 200, recW, recH);

        // Oval
        //* drawOval(int x, int y, int width, int height).
        int cirW=100;
        int cirH=150;
        g2.setColor(Color.magenta);
        g2.drawOval(250, 400, cirW, cirH);

        g2.setColor(Color.yellow);
        g2.fillOval(400, 400, cirW, cirH);

    } // End of paintComponent() method

}

