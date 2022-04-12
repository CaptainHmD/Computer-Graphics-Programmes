package lab6;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.text.DecimalFormat;

public class ParametricBezierCurve extends JFrame {

    private static final int FRAME_WIDTH = 800;  // frame-width
    private static final int FRAME_HEIGHT = 800; // frame-height

    public ParametricBezierCurve() {

        setLayout(new BorderLayout( ));     // layout of frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT); // set width & height of frame
        setTitle("Parametric Bezier Curve");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ParametricBezierCurvePanel panel = new ParametricBezierCurvePanel();
        add(panel, BorderLayout.CENTER); // add panel in frame

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {

        new ParametricBezierCurve();
    }
}

class ParametricBezierCurvePanel extends JPanel {

    private static final int PANEL_WIDTH  = 800;
    private static final int PANEL_HEIGHT = 800;

    private static final int N = 200;
    Point P0, P1, P2, P3;


    int Pt_x[] = new int[N + 1];   // array to store P(t)_x
    int Pt_y[] = new int[N + 1];   // array to store P(t)_y

    public ParametricBezierCurvePanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.white);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(2.0f));

        P0 = new Point(100, 196);
        P1 = new Point(556, 148);
        linearBezier(P0, P1, N);
        g2.setColor(Color.red);
        drawPointArry(g2);

        P0 = new Point(100, 196);
        P1 = new Point(380, 353);
        P2 = new Point(556, 148);
        quadraticBezier(P0, P1, P2, N);
        g2.setColor(Color.green);
        drawPointArry(g2);


        P0 = new Point(100, 196);
        P1 = new Point(280, 353);
        P2 = new Point(380, 23);
        P3 = new Point(556, 148);
        cubicBezier(P0, P1, P2, P3, N);
        g2.setColor(Color.blue);
        drawPointArry(g2);

    }

    public void drawPointArry(Graphics2D g2) {
        for (int i = 0; i <= N; i++) {
            g2.drawLine(Pt_x[i], Pt_y[i], Pt_x[i], Pt_y[i]);
        }
    }

    /** Linear Bezier
     *  P(t) = (1 - t)*P0 + t*P1
     */
    public void linearBezier(Point P0, Point P1, int N) {


        double x, y;
        double delta = 1.0 / N;
        double t = 0;

        System.out.println("Linear Bezier");
        DecimalFormat formater1 = new DecimalFormat("0.000");
        // computer n+1 uniform parameterized values b/w 0 and 1
        for (int i = 0; i <= N; i++) {
            t = i * delta;
            x = (1 - t) * P0.getX() + t * P1.getX();
            y = (1 - t) * P0.getY() + t * P1.getY();
            Pt_x[i] = (int) Math.round(x);
            Pt_y[i] = (int) Math.round(y);

            System.out.println("i=" + i + ", " + "t=" + formater1.format(t) + "\t" + "P=" + "(" + Pt_x[i] + "," + Pt_y[i] + ")");
        }
    }

    /** Quadratic Bezier*/
    public void quadraticBezier(Point P0, Point P1, Point P2, int N) {

        double x, y;
        double delta = 1.0 / N;
        double t = 0;

        System.out.println("quadratic Bezier");
        DecimalFormat formater1 = new DecimalFormat("0.000");
        // computer n+1 uniform parameterized values b/w 0 and 1
        for (int i = 0; i <= N; i++) {
            t = i * delta;

            x= Math.pow((1 - t),2)*P0.getX()+(2*t)*(1-t)*P1.getX()+(t*t)*P2.getX();
            y= Math.pow((1 - t),2)*P0.getY()+(2*t)*(1-t)*P1.getY()+(t*t)*P2.getY();

            Pt_x[i] = (int) Math.round(x);
            Pt_y[i] = (int) Math.round(y);

            System.out.println("i=" + i + ", " + "t=" + formater1.format(t) + "\t" + "P=" + "(" + Pt_x[i] + "," + Pt_y[i] + ")");
        }


    }

    /** Cubc Bezier */

    public void cubicBezier(Point P0, Point P1, Point P2, Point P3, int N) {
        double x, y;
        double delta = 1.0 / N;
        double t = 0;

        System.out.println("cubic Bezier");
        DecimalFormat formater1 = new DecimalFormat("0.000");
        for (int i = 0; i <= N; i++) {
            t = i * delta;

            x= Math.pow((1-t),3)*P0.getX()+(3*t)*Math.pow((1-t),2)*P1.getX()+3*(t*t)*(1-t)*P2.getX()+Math.pow(t,3)*P3.getX();
            y= Math.pow((1-t),3)*P0.getY()+(3*t)*Math.pow((1-t),2)*P1.getY()+3*(t*t)*(1-t)*P2.getY()+Math.pow(t,3)*P3.getY();

            Pt_x[i] = (int) Math.round(x);
            Pt_y[i] = (int) Math.round(y);

            System.out.println("i=" + i + ", " + "t=" + formater1.format(t) + "\t" + "P=" + "(" + Pt_x[i] + "," + Pt_y[i] + ")");
        }
    }

}
