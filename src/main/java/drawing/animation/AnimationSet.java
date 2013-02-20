package drawing.animation;

import drawing.Coordinate;
import java.awt.Graphics;

/**
 * Manage a set of animations for a Sprite
 * 
 * @author drm
 */
public class AnimationSet {

    private Animation[] animations;
    private int currentAnimation;

    public AnimationSet(Animation[] animations) {
        this.animations = animations;
    }

    /**
     * Select an animation from the set of animations
     * 
     * Resets the animation back to the beginning of the sequence of frames.
     * Should therefore only be called when the animation should *change* in some way.
     * Calling this function multiple times with the same argument may cause the animation
     * to skip
     * 
     * @param state the 0-based index of the animation to select
     */
    public void select(int animation) {
        validateAnimation(animation);
        currentAnimation = animation;
        animations[currentAnimation].reset();
    }

    /**
     * Ensure that an animation is selected for the
     * 
     * If the animation is already selected, does *not* reset it.
     * Can therefore be called as many times as is convenient, without causing
     * the animation to skip
     * 
     * 
     * @param state the 0-based index of the animation to select
     */
    public void ensureSelected(int animation) {
        if (animation != currentAnimation) {
            select(animation);
        }
    }

    /**
     * Advance the current animation by one frame
     */
    public void advance() {
        animations[currentAnimation].advance();
    }

    public void paint(Graphics g, Coordinate c) {
        animations[currentAnimation].paint(g, c);
    }

    /**
     * Query whether a given animation index refers to a valid animation
     * for this animation set
     * 
     * @param animation the animation index to check
     * @return true if the index refers to a valid animation
     */
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
