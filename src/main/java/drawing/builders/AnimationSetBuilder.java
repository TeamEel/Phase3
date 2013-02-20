package drawing.builders;

import drawing.animation.Animation;
import drawing.animation.AnimationSet;
import java.util.ArrayList;

/**
 * Fluent ExpressionBuilder [See Fowler&Parsons, Domain Specific Languages]
 * to allow easy creation of animation sets.
 * @author drm
 */
public class AnimationSetBuilder {

    private ArrayList<Animation> animations;

    public AnimationSetBuilder() {
        animations = new ArrayList<Animation>();
    }

    /**
     * Fluent helper to add an animation to the AnimationSet
     * 
     * @param animation the Animation to add
     * @return the Expression Builder to continue the invocation
     */
    public AnimationSetBuilder animation(Animation animation) {
        animations.add(animation);
        return this;
    }

    /**
     * Method chain terminator
     * 
     * @return the generated AnimationSet
     */
    public AnimationSet done() {
        return new AnimationSet(animations.toArray(new Animation[0]));
    }
}
