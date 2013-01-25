package icarus.parser;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * Class that opens a window to display the Game World picture
 *
 * @author Team Haddock
 */
public class Picture extends JFrame {

    JLabel label1; // creating a label to display the image within the window

    /**
     *
     * @param title Overrides the JFrame package to initialise the title of the Window
     *
     * Default constructor which sets the layout of the image
     */
    public Picture(String title) {

        super(title);
        ImageIcon image = new ImageIcon(getClass().getResource("reactor.jpg"));
        label1 = new JLabel(image, JLabel.CENTER); // to center the image in the
        // window
        add(label1); // adding label for image
        setLayout(new FlowLayout()); // setting the layout of the image

    }

    /**
     * Method to create the Window and initialise its settings
     */
    private static void createAndShowGUI() {

        Picture myWindow = new Picture("Project Icarus"); // to initialise the
        // title of the window
        myWindow.setSize(950, 950); // setting dimensions of the window
        myWindow.setVisible(true); // to make sure the window is visible
        myWindow.setLocation(750, 0); // setting the location of the window to
        // top right
        myWindow.pack(); // to make sure that the window size covers the just
        // the image and nothing else

    }

    /**
     * Main method of Picture class
     */
    public static void showPicture() // Method to run the class and generates
    // the window
    {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

    /**
     * A method to close the window
     *
     * @param e an object of WindowEvent
     */
    public void windowClosing(WindowEvent e) { // A method to close the window
        dispose();
        System.exit(0);
    }
}
