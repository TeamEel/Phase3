package drawing.builders;

import drawing.animation.Animation;
import drawing.animation.AnimationSet;
import java.io.IOException;

/**
 *
 * @author drm
 */
public class BuildAnimationSet {
    public static AnimationSetBuilder buildAnimationSet() {
        return new AnimationSetBuilder();
    }
    
    public static AnimationSet singleImage(String resourcePath) throws IOException {
        return new AnimationSetBuilder().animation(BuildAnimation.singleFrame(resourcePath)).done();
    }
    
    public static AnimationSet singleAnimation(Animation animation) {
        return new AnimationSetBuilder().animation(animation).done();
    }
}
