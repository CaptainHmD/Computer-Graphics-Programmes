package Lab2;

import java.awt.*;
import java.awt.event.*;

public class ArrayOfRectangles {
    public static void main(String[] args) {
        ArrayOfRectanglesFrame frame = new ArrayOfRectanglesFrame();
        frame.setTitle("Tiles of Rectangles");
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    } // end of main
}
//

class ArrayOfRectanglesFrame extends Frame {

    private static final int FW = 800; // frame width
    private static final int FH = FW; // frame height

    private static final int RW = FW / 8; // rectangle width
    private static final int RH = RW;  // rectangle height

    private static final int H = RW / 2; // horz. space b/w rectangles
    private static final int V = H;      // vert. space b/w rectangles

    private static final int NO_OF_RECT = 25;

    Rectangle rectAry[] = new Rectangle[NO_OF_RECT];

    public ArrayOfRectanglesFrame() {
        setSize(FW, FH);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int x;
        int y = V;
        int c = 0;

        for (int j = 0; j < 5; j++) {
            if (j % 2 == 1) {
                g2.setColor(Color.green);

            } else {
                g2.setColor(Color.blue);

            }
            x = H;
            for (int i = 0; i < 5; i++) {
                rectAry[c] = new Rectangle(x, y, RW, RH);
                g2.fill(rectAry[c]);
                c = c + 1;
                x = x + RW + H;

            }
            y = y + RW + H;
        }

    } // End of paint() method
}