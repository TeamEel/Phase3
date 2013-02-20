package drawing.animation;

import drawing.Coordinate;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author drm
 */
public abstract class Animation {

    private Image[] frames;
    protected int currentFrame;

    public Animation(Image[] images) {
        frames = images;
        currentFrame = 0;
    }

    public void paint(Graphics g, Coordinate c) {
        g.drawImage(frames[currentFrame], c.x, c.y, null);
    }

    public abstract void advance();

    public void reset() {
        currentFrame = 0;
    }

    protected boolean lastFrame() {
        return currentFrame == frames.length - 1;
    }
}
