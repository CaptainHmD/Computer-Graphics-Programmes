package lab3;


import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class TranslatePolygon extends JFrame {

    private static final int FW = 800; // frame-width
    private static final int FH = 800; // frame-height

    public TranslatePolygon() {

        setLayout(new BorderLayout()); // layout of frame
        setSize(FW, FH);                // set width & height of frame
        setTitle("Translate Polygon");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TranslatePolygonPanel panel = new TranslatePolygonPanel(FW, FH);
        add(panel, BorderLayout.CENTER); // add panel in frame

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {

        new TranslatePolygon();
    }
}

class TranslatePolygonPanel extends JPanel {

    private static int W; // panel-width
    private static int H; // panel-height

    private static int pw = 100;  //polygon-width
    private static int ph = 150; //polygon-height


    private static int x0, y0, x1, y1, x2, y2; // polygon coordinates

    private static int cx, cy; // center of panel (computed later)


    private static int xPoly[] = new int[3]; // array to create polygon
    private static int yPoly[] = new int[3];

    public TranslatePolygonPanel(int FW, int FH) {
        W = FW;
        H = FH;

        cx = W/2; // compute center of panel x-coordinate
        cy = H/2; // compute center of panel y-coordinate

        // initialize polygon coordinates
        x0 = cx - pw/2;
        y0 = cy + ph/2;
        x1 = x0 + pw;
        y1 = y0;
        x2 = x0 + pw/2;
        y2 = y0 - ph;

        // initialize array to store polygon coordinates
        xPoly[0] = x0;
        xPoly[1] = x1;
        xPoly[2] = x2;

        yPoly[0] = y0;
        yPoly[1] = y1;
        yPoly[2] = y2;

        setPreferredSize(new Dimension(W, H));
        this.setBackground(Color.white);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;


        // create shape
        Shape poly = new Polygon(xPoly, yPoly, xPoly.length);

        g2.setColor(Color.blue);
        g2.fill(poly);

        // create an object of class AffineTransform
        AffineTransform transform = new AffineTransform();

        //Translation
        transform.translate(200,-100); // tx, ty
        poly = transform.createTransformedShape(poly);
        g2.setColor(Color.red);
        g2.fill(poly);


        //Scale
        transform.scale(0.5,0.5);
        poly = transform.createTransformedShape(poly);
        g2.setColor(Color.green);
        g2.fill(poly);

        //Rotation
        transform.setToRotation(15*Math.PI/180);
        poly = transform.createTransformedShape(poly);
        g2.setColor(Color.yellow);
        g2.fill(poly);

    }
}
