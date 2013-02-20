package drawing;

import drawing.animation.AnimationSet;
import java.awt.Graphics;

/**
 *
 * @author drm
 */
public class Sprite {

    private AnimationSet animations;
    private Coordinate position;

    public Sprite(AnimationSet animations) {
        this(animations, new Coordinate(0, 0));
    }

    public Sprite(AnimationSet animations, Coordinate position) {
        this.animations = animations;
        this.position = position;
    }

    public void selectAnimation(int state) {
        animations.select(state);
    }

    public void ensureAnimationSelected(int state) {
        animations.ensureSelected(state);
    }

    public void advance() {
        animations.advance();
    }

    public void moveTo(Coordinate c) {
        position = c;
    }

    public void moveBy(Coordinate delta) {
        position = position.plus(delta);
    }

    public void paint(Graphics g) {
        animations.paint(g, position);
    }
}
