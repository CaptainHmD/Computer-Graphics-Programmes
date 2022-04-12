package Lab2;


import java.awt.*;
import java.awt.event.*;

class RectangleFrame {
    public static void main(String[] args) {
        DrowRectangle frame = new DrowRectangle();
        frame.setTitle("Tiles of Rectangles");
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    } // end of main
}


class DrowRectangle extends Frame {
//
    private static final int FW = 600; // frame width
    private static final int FH = 600; // frame height

    private static final int RW = FW / 8; // rectangle width
    private static final int RH = RW;  // rectangle height

    private static final int H = RW / 2; // horz. space b/w rectangles
    private static final int V = H;      // vertd. space b/w rectangles


    int DrawMidOfTheFrame = FW/2;
    int YRecLocation = FH/3;

    public DrowRectangle() {
        setSize(FW, FH);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int x;
        int y = V;
        int c = 0;
        g2.setColor(Color.red);

        g2.drawLine(DrawMidOfTheFrame,0,DrawMidOfTheFrame,FW);
        g2.drawLine(0,DrawMidOfTheFrame,FW,DrawMidOfTheFrame);
        g2.setColor(Color.blue);

        g2.drawRect(DrawMidOfTheFrame/2,YRecLocation,300,200);






    } // End of paint() method
}
