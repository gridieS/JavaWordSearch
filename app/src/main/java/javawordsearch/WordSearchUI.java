package javawordsearch;
import javax.swing.*;
import java.util.HashMap;
import java.awt.Dimension;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.FontMetrics;

public class WordSearchUI extends WordSearch {
    public static final Color MARKER_COLOR = new Color(0,255,255,220);
    public final Dimension DIMENSIONS;
    public final int CHAR_PIXEL_SIZE;
    public UIFrame frame;
    public UIPanel panel;

    public WordSearchUI(int charPixelSize,CharGrid charGrid) {
        super(charGrid);
        this.CHAR_PIXEL_SIZE = charPixelSize;
        this.DIMENSIONS = new Dimension(charPixelSize*charGrid.x,charPixelSize*charGrid.y);
        this.frame = new UIFrame();
        this.panel = this.frame.panel;
    }
    public WordSearchUI(int charPixelSize,String grid, int x, int y) {
        this(charPixelSize,new CharGrid(grid,x,y));
    }
    
    public class UIFrame extends JFrame {
        public final UIPanel panel;

        UIFrame(){
            this.panel = new UIPanel();
            this.add(panel);
            this.setTitle("Word Search");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.pack();
            this.setVisible(true);
            this.setLocationRelativeTo(null);
            this.setPreferredSize(getSize());
        }
    }
    public class UIPanel extends JPanel {
        UIPanel() {
            this.setPreferredSize(DIMENSIONS);
            this.setBackground(Color.white);
            this.setFocusable(true);
        }
        private void drawChars(Graphics g) {
            Font font = new Font(Font.SANS_SERIF,Font.BOLD,(int)(CHAR_PIXEL_SIZE/1.5));
            for (int x = 0;x < charGrid.x;x++) {
                for (int y = 0;y < charGrid.y;y++) {
                    String curChar = charGrid.get(x).get(y).toString();
                    double posX = x*CHAR_PIXEL_SIZE+(CHAR_PIXEL_SIZE/2);
                    double posY = y*CHAR_PIXEL_SIZE+(CHAR_PIXEL_SIZE/2);
                    FontMetrics metrics = ((Graphics2D)g).getFontMetrics();
                    Rectangle2D rect = metrics.getStringBounds(curChar, ((Graphics2D)g));
                    if (x != 0 || y != 0) { // Weird bug makes char at x,y 0,0 to be misplaced
                        posX -= rect.getWidth()/2;
                        posY -=  rect.getCenterY();
                    }
                    g.setFont(font);
                    g.drawString(curChar,((int)posX),((int)posY)); 
                }
            }
        }
        private void drawHelpLines(Graphics g) {
            g.setColor(Color.black);
            for (int x = 0;x < charGrid.x;x++) {
                g.drawLine(x*CHAR_PIXEL_SIZE,0,x*CHAR_PIXEL_SIZE,charGrid.y*CHAR_PIXEL_SIZE);
            }
            for (int y = 0;y < charGrid.y;y++) {
                g.drawLine(0,y*CHAR_PIXEL_SIZE,charGrid.x*CHAR_PIXEL_SIZE,y*CHAR_PIXEL_SIZE);
            }
        }
        private void drawSolution(Graphics g) {
            for (HashMap.Entry<String, int[][]> entry : solution.entrySet()) {
                int[] startingPosition = new int[] {(int)(entry.getValue()[0][0]*CHAR_PIXEL_SIZE),(int)(entry.getValue()[0][1]*CHAR_PIXEL_SIZE)};
                int[] endingPosition = new int[] {(int)(entry.getValue()[1][0]*CHAR_PIXEL_SIZE),(int)(entry.getValue()[1][1]*CHAR_PIXEL_SIZE)};
                g.setColor(new Color(0,0,0,50));
                g.fillRect(startingPosition[0],startingPosition[1],CHAR_PIXEL_SIZE,CHAR_PIXEL_SIZE);
                g.setColor(MARKER_COLOR);
                Graphics2D g2D = (Graphics2D)g;
                g2D.setStroke(new BasicStroke(5));
                g2D.drawLine(startingPosition[0]+(CHAR_PIXEL_SIZE/2),startingPosition[1]+(CHAR_PIXEL_SIZE/2),endingPosition[0]+(CHAR_PIXEL_SIZE/2),endingPosition[1]+(CHAR_PIXEL_SIZE/2));
            }
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.drawString("Hello world",50,50);
            drawHelpLines(g);
            drawChars(g);
            if (solution != null) {
                drawSolution(g);
            }
        }
    }
    
}
