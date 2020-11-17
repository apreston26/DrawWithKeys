package events;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawWithKeys extends Canvas implements KeyListener
{
    char keypressed;
    int screenSize;
    Color backgroundColor;


    /**
     * The main part of the program
     */
    public static void main(String[] args) {
        DrawWithKeys keyboardExample = new DrawWithKeys();
        keyboardExample.setupScreen(keyboardExample);
    }

    /**
     * Constructor, initializes all class variables
     */
    DrawWithKeys() {
        screenSize = 500;
        backgroundColor = new Color(239, 248, 207);
    }

    /**
     * Sets up the screen
     * @param canvas the Canvas object that is the main
     *               part of the screen
     */
    void setupScreen(DrawWithKeys canvas) {
        JFrame frame = new JFrame("Keyboard interaction"); //give screen a name
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setSize(screenSize, screenSize);
        canvas.setBackground(backgroundColor);
        canvas.addKeyListener(canvas);  //tell the canvas that you will be "listening for keyboard interactions"
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        canvas.requestFocusInWindow();
        //prints out a list of all available fonts
        canvas.listFonts();
    }

    /**
     * draws everything on the screen
     * @param g the Graphics object to draw on
     */
    public void paint(Graphics g) {
        // See https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html
        // for more information about what you can do with fonts
        Font zapfino = new Font("Zapfino", Font.BOLD, 18);  //create a font called zapfino from the Zapfino system font & set its style and size
        g.setFont(zapfino);     //set the current font to be the zapfino font you created
        g.setColor(Color.BLACK);
        g.drawString(Character.toString(keypressed), 240,250);  //draw the character "keypressed" on the screen

        int[] xPos = new int[] {screenSize / 2  - 50 , screenSize / 2  - 100, screenSize / 2 + 50};
        int[] yPos = new int[] {screenSize / 2 + 50, screenSize / 2 - 100, screenSize / 2 - 50 };

        if (keypressed == 'r') {
            g.setColor(Color.RED);
            g.fillRect(screenSize/2-50, screenSize/2-50, 100, 100);
            g.setColor(Color.BLACK);
            g.drawRect(screenSize/2-50, screenSize/2-50, 100, 100);
        } else if (keypressed == 'b') {
            g.setColor(Color.BLUE);
            g.fillOval(screenSize/2-50, screenSize/2-50, 100, 100);
            g.setColor(Color.BLACK);
            g.drawOval(screenSize/2-50, screenSize/2-50, 100, 100);
        } else if (keypressed == 'g') {
            g.setColor(Color.GREEN);
            g.fillPolygon(xPos, yPos, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(xPos,yPos,3);
        }

    }

    /**
     * This method responds to the keyTyped event
     * by setting the keypressed variable to the key
     * that was typed. keyTyped responds to "regular"
     * character keys. It does not respond to special
     * keys like return, delete, shift, etc.
     * @param e the key event that happened
     */
    public void keyTyped(KeyEvent e) {
        keypressed = e.getKeyChar();
        repaint();
    }

    /**
     * This method responds to the keyPressed event
     * by printing out the keycode for the key
     * that was typed. keyTyped responds to all
     * keys, including special keys. For a list of
     * keycodes, see: http://www.foreui.com/articles/Key_Code_Table.htm
     * @param e the key event that happened
     */
    public void keyPressed(KeyEvent e) {
        System.out.println("The keycode for this key is: " + e.getKeyCode());
        repaint();
    }

    /**
     * This method responds to the keyReleased event
     * @param e the key event that happened
     */
    public void keyReleased(KeyEvent e) {
    }

    /**
     * This method lists out all of the available fonts
     */
    void listFonts() {
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for ( int i = 0; i < fonts.length; i++ )
        {
            System.out.println(fonts[i]);
        }
    }

}