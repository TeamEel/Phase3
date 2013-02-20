package drawing;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Manage a collection of Sprites for a SpriteCanvas
 *
 * @author drm
 */
public class SpriteSet {

    ArrayList<ZSprite> sprites;

    public SpriteSet() {
        sprites = new ArrayList<ZSprite>();
    }

    /**
     * Add a sprite to the Canvas.
     *
     * The z-parameter controls depth or stacking of sprites. Sprites with a higher z-value will be drawn 'closer to'
     * the user - i.e. they will be drawn over sprites with a lower z-value.
     *
     * The background is always drawn first - negative z-values will not cause sprites to be drawn behind the
     * background.
     *
     * @param s the sprite to add
     * @param z the z-axis position of the sprite
     */
    public void add(Sprite s, int z) {
        sprites.add(new ZSprite(s, z));
        Collections.sort(sprites);
    }

    /**
     * Paint all sprites, in order, onto the given Graphics object
     * 
     * @param g the Graphics object to paint onto
     */
    public void paint(Graphics g) {
        for (ZSprite s : sprites) {
            s.paint(g);
        }
    }

    /**
     * Advance all of the sprites managed by this object by one frame
     */
    public void advance() {
        for (ZSprite s : sprites) {
            s.advance();
        }
    }
}
