package drawing;

import drawing.animation.Animation;
import drawing.animation.AnimationSet;
import static drawing.builders.AnimationBuilder.buildAnimation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author drm
 */
public class SpriteTest extends JFrame implements ActionListener, ChangeListener {

    private SpriteCanvas canvas;
    private Sprite valve;
    private Box vbox;
    private Box hbox;
    private JButton advanceButton;
    private JButton leftButton;
    private JButton rightButton;
    private Object upButton;
    private Object downButton;
    private JButton blackButton;
    private JButton yellowButton;

    public SpriteTest() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        vbox = Box.createVerticalBox();
        add(vbox);
        hbox = Box.createHorizontalBox();
        vbox.add(hbox);

        advanceButton = addButton("Advance");
        leftButton = addButton("Left");
        upButton = addButton("Up");
        downButton = addButton("Down");
        rightButton = addButton("Right");
        blackButton = addButton("Black");
        yellowButton = addButton("Yellow");

        try {
//            canvas = new SpriteCanvas(ImageIO.read(getClass().getResource("/test_background.png")));
            canvas = new SpriteCanvas(ImageLoader.imageResource("/test_background.png"));
            vbox.add(canvas);
            Animation[] animations = {
                buildAnimation()
                .frame("/test_valvebody_1.png")
                .frame("/test_valvebody_2.png")
                .frame("/test_valvebody_3.png")
                .frame("/test_valvebody_4.png")
                .frame("/test_valvebody_5.png")
                .frame("/test_valvebody_6.png")
                .loop(),
                buildAnimation()
                .frame("/test_valvebody_yellow_1.png")
                .frame("/test_valvebody_yellow_2.png")
                .frame("/test_valvebody_yellow_3.png")
                .frame("/test_valvebody_yellow_4.png")
                .frame("/test_valvebody_yellow_5.png")
                .frame("/test_valvebody_yellow_6.png")
                .frame("/test_valvebody_yellow_1.png")
                .frame("/test_valvebody_yellow_2.png")
                .frame("/test_valvebody_yellow_3.png")
                .frame("/test_valvebody_yellow_4.png")
                .frame("/test_valvebody_yellow_5.png")
                .frame("/test_valvebody_yellow_6.png")
                .frame("/test_valvebody_yellow_1.png")
                .end()
            };
            valve = new Sprite(new AnimationSet(animations));
            canvas.add(valve, 0);
            canvas.setFrameInterval(100);
            canvas.start();
            pack();
        } catch (IOException ex) {
            System.err.println("Unable to load background image!");
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            // Ignore all exceptions here :(
        } catch (Exception e) {
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SpriteTest().setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();

        if (source == advanceButton) {
            valve.advance();
        } else if (source == leftButton) {
            valve.moveBy(new Coordinate(-10, 0));
        } else if (source == rightButton) {
            valve.moveBy(new Coordinate(10, 0));
        } else if (source == upButton) {
            valve.moveBy(new Coordinate(0, -10));
        } else if (source == downButton) {
            valve.moveBy(new Coordinate(0, 10));
        } else if (source == blackButton) {
            valve.select(0);
        } else if (source == yellowButton) {
            valve.select(1);
        }

        repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }

    private JButton addButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        hbox.add(button);
        return button;
    }
}
