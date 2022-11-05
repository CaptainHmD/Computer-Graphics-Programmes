import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;

public class ShapesByMouseDrag3 extends JFrame {

    private static final int FW = 800;
    private static final int FH = 600;

    private Point p1;
    private Point p2;

    JRadioButton lineRB = new JRadioButton("Line", true);
    JRadioButton rectRB = new JRadioButton("Rectangle");
    JRadioButton Ellipse = new JRadioButton("Ellipse");
    JRadioButton Polygon = new JRadioButton("Polygon");
    JButton clear = new JButton("Clear");
    JButton ChooserColor = new JButton("Color");




    public static void main(String[] args) {
        new ShapesByMouseDrag3();
    }

    public ShapesByMouseDrag3() {
        setSize(FW, FH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Draw Graphics Objects by Mouse Drag");
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        PaintPanel paintPanelObj = new PaintPanel(FW, FH);


        JPanel chooseShapePanel = new JPanel();
        chooseShapePanel.setBackground(Color.WHITE);
        chooseShapePanel.setLayout(new GridLayout(6, 1));



        chooseShapePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Shapes"));

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(lineRB);
        bgroup.add(rectRB);
        bgroup.add(Ellipse);
        bgroup.add(Polygon);
        bgroup.add(clear);
        bgroup.add(ChooserColor);


        chooseShapePanel.add(lineRB);
        chooseShapePanel.add(rectRB);
        chooseShapePanel.add(Ellipse);
        chooseShapePanel.add(Polygon);
        chooseShapePanel.add(clear);
        chooseShapePanel.add(ChooserColor);



        add(paintPanelObj, BorderLayout.CENTER);
        add(chooseShapePanel, BorderLayout.WEST);
        setVisible(true);
        pack();



    }

    private class PaintPanel extends JPanel {

        private int PW; // panel-width
        private int PH; // panel-height

        ArrayList<Shape> shapeList = new ArrayList<Shape>();

        public PaintPanel(int W, int H) {
            PW = W;
            PH = H;
            setPreferredSize(new Dimension(PW, PH)); // set width & height of panel
            setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Canvas"));

            addMouseListener(new MouseClickListener());

            ChooserColor.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    JColorChooser jColorChooser = new JColorChooser();
                    color = JColorChooser.showDialog(null, "COLORS", Color.green);
                }

            });

            clear.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    polyindex1 = 0;
                    polyindex = -1;
                    colorList.clear();
                    shapeList.clear();
                    inPolygon = false;
                    repaint();

                }
            });

        }
        Color color = Color.blue;
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            g2.setStroke(new BasicStroke(2f));

            g2.setColor(Color.blue);
            if (shapeList.isEmpty()) {
                clearTheBuffer();
            }

            if (lineRB.isSelected()) {
                makeLine();
            } else if (rectRB.isSelected()) {
                makeRectangle();
            } else if (Ellipse.isSelected()) {
                makeEllipses();
            } else if (Polygon.isSelected()) {
                makePolygon(g);
            }




            for (int i = 0; i < shapeList.size(); i++) {
                g2.setColor(colorList.get(i));
                g2.draw(shapeList.get(i));
            }

            // reset
            p1 = null;
            p2 = null;

        }


        ArrayList<Color> colorList = new ArrayList<Color>();

        void addColorInTheList(){
            colorList.add(color);
        }

        public void makeLine() {
            addColorInTheList();
            if (p1 != null && p2 != null) {
                shapeList.add(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));
                inPolygon = false;
                polyindex = polyindex1;

            }
        }

        public void makeRectangle() {
            addColorInTheList();
            if (p1 != null && p2 != null) {
                if (p2.x > p1.x && p2.y > p1.y)
                    shapeList.add(new Rectangle2D.Double(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y));// top left
                else if (p1.x > p2.x && p1.y > p2.y)
                    shapeList.add(new Rectangle2D.Double(p2.x, p2.y, p1.x - p2.x, p1.y - p2.y)); // bottom right
                else if (p2.x > p1.x && p1.y > p2.y)
                    shapeList.add(new Rectangle2D.Double(p1.x, p2.y, p2.x - p1.x, p1.y - p2.y));// bottom left
                else if (p1.x > p2.x && p2.y > p1.y)
                    shapeList.add(new Rectangle2D.Double(p2.x, p1.y, p1.x - p2.x, p2.y - p1.y)); // top right
                else
                    shapeList.add(new Rectangle2D.Double(p1.x, p1.y, 0, 0));
                polyindex = polyindex1;
                inPolygon = false;
            }

        }

        public void makeEllipses() {
            addColorInTheList();
            if (p1 != null && p2 != null) {
                if (p2.x > p1.x && p2.y > p1.y)
                    shapeList.add(new Ellipse2D.Double(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y));// top left
                else if (p1.x > p2.x && p1.y > p2.y)
                    shapeList.add(new Ellipse2D.Double(p2.x, p2.y, p1.x - p2.x, p1.y - p2.y));// bottom right
                else if (p2.x > p1.x && p1.y > p2.y)
                    shapeList.add(new Ellipse2D.Double(p1.x, p2.y, p2.x - p1.x, p1.y - p2.y));// bottom left
                else if (p1.x > p2.x && p2.y > p1.y)
                    shapeList.add(new Ellipse2D.Double(p2.x, p1.y, p1.x - p2.x, p2.y - p1.y)); // top right
                else
                    shapeList.add(new Ellipse2D.Double(p1.x, p1.y, 1, 1));
                inPolygon = false;
                polyindex = polyindex1;

            }


        }

        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();

        int countPoly = 0; // number of polygon
        boolean inPolygon = false;

        public void makePolygon(Graphics g) {
            if (p1==null)
                return;
            super.paintComponent(g); // for drawing a dot

            if (!inPolygon) {
                x.clear(); // reset indices in x list
                y.clear(); // reset indices in y list
                countPoly = 0; // reset countPoly
            }

            inPolygon = true;

            x.add(p1.x); // adding the x point of mouse click in the list
            y.add(p1.y);// adding the y point of mouse click in the list

            int[] x1 = x.stream().mapToInt(i -> i).toArray(); // convert the ArrayList to normal array to pass it in the Polygon
            int[] y1 = y.stream().mapToInt(i -> i).toArray(); // convert the ArrayList to normal array to pass it in the Polygon
            Polygon poly = new Polygon(x1, y1, x1.length); // creating polygon object with  specific points
            countPoly++;
            addColorInTheList();
            shapeList.add(poly); // adding the polygon in the shapeList
            updatePolygon(poly); // update the last Polygon for multiple points

            g.drawLine(x.get(0), y.get(0), x.get(0), y.get(0)); // just drawing the first point of every polygon

        }

        int polyindex1 = 0;
        int polyindex = -1;

        private void updatePolygon(Polygon poly) {
            if (countPoly > 1) {
                for (int i = polyindex + 1; i < shapeList.size(); i++) {
                    if (shapeList.get(i).getClass().equals(poly.getClass())) {
                        countPoly--;
                        polyindex1 = i;
                        colorList.remove(i);
                        shapeList.remove(i);
                    }
                }
            }
        }

        private void clearTheBuffer() { // reset everything
            polyindex1 = 0;
            polyindex = -1;
            colorList.clear();
            shapeList.clear();
            inPolygon = false;
        }

        private class MouseClickListener extends MouseAdapter {

            public void mousePressed(MouseEvent event) {
                p1 = new Point(event.getX(), event.getY());
            }

            public void mouseReleased(MouseEvent event) {
                p2 = new Point(event.getX(), event.getY());
                repaint();
            }
        }
    }
}


