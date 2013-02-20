package drawing;

import java.awt.Graphics;

/**
 * Encapsulate a Sprite with an associated z-value
 * 
 * Provides the interface needed by SpriteSet and (indirectly) SpriteCanvas.
 * 
 * @author drm
 */
public class ZSprite implements Comparable<ZSprite> {

    private Sprite sprite;
    private int z;

    public ZSprite(Sprite sprite, int z) {
        this.sprite = sprite;
        this.z = z;
    }

    public void advance() {
        sprite.advance();
    }

    public void paint(Graphics g) {
        sprite.paint(g);
    }

    @Override
    public int compareTo(ZSprite other) {
        return new Integer(z).compareTo(other.z);
    }
}
