package drawing.animation;

import drawing.Coordinate;
import java.awt.Graphics;

/**
 *
 * @author drm
 */
public class AnimationSet {

    private Animation[] animations;
    private int currentAnimation;

    public AnimationSet(Animation[] animations) {
        this.animations = animations;
    }

    public void select(int animation) {
        validateAnimation(animation);
        currentAnimation = animation;
        animations[currentAnimation].reset();
    }
    
    public void ensureSelected(int animation) {
        if (animation != currentAnimation) {
            select(animation);
        }
    }

    public void advance() {
        animations[currentAnimation].advance();
    }

    public void paint(Graphics g, Coordinate c) {
        animations[currentAnimation].paint(g, c);
    }

    public boolean validAnimation(int animation) {
        return animation >= 0 && animation < animations.length;
    }

    private void validateAnimation(int animation) {
        if (!validAnimation(animation)) {
            throw new IllegalArgumentException("Trying to select animation " + animation +
                                               " outside the valid range [0, " + animations.length + ")");
        }
    }
}
