
package lab4;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
//
public class RectsByMouseClickStoreInShapeArray extends JFrame {

    private static final int FW = 800; // frame width
    private static final int FH = FW; // frame height

    public RectsByMouseClickStoreInShapeArray() {

        setLayout(new BorderLayout()); // layout of frame
        setSize(FW, FH);                // set width & height of frame
        setTitle("Rectangles By Mouse Click");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawingPanel panel = new DrawingPanel(FW, FH);
        add(panel, BorderLayout.CENTER); // add panel in frame

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new RectsByMouseClickStoreInShapeArray();
    }
}

class DrawingPanel extends JPanel {

    private static int RW = 200;  // rectangle width
    private static int RH = 100;  // rectangle height

    private static int MAX_SHAPES = 100; // number of rectangles
    private Shape shapeAry[] = new Shape[MAX_SHAPES];
    private static int shapeCount = 0;

    private static int FW; // panel-width
    private static int FH; // panel-height

    public DrawingPanel(int W, int H) {
        FW = W;
        FH = H;

        setPreferredSize(new Dimension(FW, FH)); // set width & height of panel
        this.setBackground(Color.white);

        // register listener
        MouseClickListener listener = new MouseClickListener();
        addMouseListener(listener);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (shapeCount > 0) {
            for (int k = 0; k < shapeCount; k++) {
                if (k % 2 == 0)
                    g2.setColor(Color.blue);
                else
                    g2.setColor(Color.red);
                g2.draw(shapeAry[k]);
            }
        }


    } // End of paintComponent() method

    // inner class to handle mouse events
    private class MouseClickListener extends MouseAdapter {

        int Mousex = 0;
        int MouseY = 0;

        public void mouseClicked(MouseEvent event) {
            int mouseX = event.getX();
            int mouseY = event.getY();


            if (shapeCount == MAX_SHAPES) {

                shapeCount = 0;
            }

            shapeAry[shapeCount] = new Rectangle2D.Double(mouseX, mouseY, RW, RH);
            shapeCount++;
            shapeAry[shapeCount] = new Line2D.Double(this.Mousex, this.MouseY, mouseX, mouseY);
            shapeCount++;
            this.Mousex = mouseX;
            this.MouseY = mouseY;


            System.out.println("shapeCount = " + shapeCount);
            System.out.println("mouseX, mouseY = " + mouseX + "," + mouseY);

            repaint();
        }
    }

}
