package drawing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Provide a painting surface for Sprites
 * 
 * Allows for static Z-ordering of Sprites, and arbitrary movement and animation selection
 * for Sprites. Does not allow for changes in the Z-ordering of sprites which have already been added.
 * 
 * @author drm
 */
public class SpriteCanvas extends JPanel implements ActionListener {

    private Image background;
    private SpriteSet sprites;
    private Timer timer;
    private double scaleFactor;
    
    public SpriteCanvas(Image background) {
        this.background = background;
        this.sprites = new SpriteSet();
        this.timer = new Timer(1000, this);
        this.scaleFactor = 1;
        setPreferredSize(new Dimension(background.getWidth(null), background.getHeight(null)));
    }

    public void setScaleFactor(double scaleFactor) {
        if (scaleFactor > 1 || scaleFactor <= 0) {
            throw new IllegalArgumentException("Cannot set scale factor outside the range (0,1]");
        }
        this.scaleFactor = scaleFactor;
        setPreferredSize(new Dimension((int)Math.ceil(background.getWidth(null) * scaleFactor),
                                       (int)Math.ceil(background.getHeight(null) * scaleFactor)));
    }
    
    /**
     * Set the interval between animation frames for the canvas
     * 
     * For example, a calling setFrameInterval(100) will cause the frame to
     * advance every 100ms, for a 10-frame-per-second animation.
     * 
     * @param ms the number of milliseconds to delay between frames
     */
    public void setFrameInterval(int ms) {
        timer.setDelay(ms);
    }

    /**
     * Begin animating sprites.
     * 
     * Sprites can be added freely after calling start.
     */
    public void start() {
        timer.start();
    }

    /**
     * Stop animating sprites
     */
    public void stop() {
        timer.stop();
    }

    /**
     * Add a sprite to the Canvas.
     * 
     * The z-parameter controls depth or stacking of sprites. Sprites with a higher z-value
     * will be drawn 'closer to' the user - i.e. they will be drawn over sprites with a lower
     * z-value.
     * 
     * The background is always drawn first - negative z-values will not cause sprites
     * to be drawn behind the background.
     * 
     * @param s the sprite to add
     * @param z the z-axis position of the sprite
     */
    public void add(Sprite s, int z) {
        sprites.add(s, z);
    }

    @Override
    public void paintComponent(Graphics g) {
        // mildly hacky scaling to fit onto smaller screens
        ((Graphics2D)g).scale(scaleFactor, scaleFactor);
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
