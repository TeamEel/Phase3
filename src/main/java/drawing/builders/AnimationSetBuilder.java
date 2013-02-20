package drawing.builders;

import drawing.animation.Animation;
import drawing.animation.AnimationSet;
import java.util.ArrayList;

/**
 *
 * @author drm
 */
public class AnimationSetBuilder {

    private ArrayList<Animation> animations;

    public AnimationSetBuilder() {
        animations = new ArrayList<Animation>();
    }

    public AnimationSetBuilder animation(Animation animation) {
        animations.add(animation);
        return this;
    }

    public AnimationSet done() {
        return new AnimationSet(animations.toArray(new Animation[0]));
    }
}
