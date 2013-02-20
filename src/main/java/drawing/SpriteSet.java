package drawing;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author drm
 */
public class SpriteSet {

    ArrayList<ZSprite> sprites;

    public SpriteSet() {
        sprites = new ArrayList<ZSprite>();
    }

    public void add(Sprite s, int z) {
        sprites.add(new ZSprite(s, z));
        Collections.sort(sprites);
    }

    public void paint(Graphics g) {
        for (ZSprite s : sprites) {
            s.paint(g);
        }
    }

    public void advance() {
        for (ZSprite s : sprites) {
            s.advance();
        }
    }
}
