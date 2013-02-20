package drawing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author drm
 */
public class SpriteCanvas extends JPanel implements ActionListener {

    private Image background;
    private SpriteSet sprites;
    private Timer timer;

    public SpriteCanvas(Image background) {
        this.background = background;
        this.sprites = new SpriteSet();
        this.timer = new Timer(1000, this);
        setPreferredSize(new Dimension(background.getWidth(null), background.getHeight(null)));
    }

    public void setFrameInterval(int ms) {
        timer.setDelay(ms);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void add(Sprite s, int z) {
        sprites.add(s, z);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, this);
        sprites.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == timer) {
            advance();
        }
    }

    private void advance() {
        sprites.advance();
        repaint();
    }
}
