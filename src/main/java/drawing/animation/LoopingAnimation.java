package drawing.animation;

import java.awt.Image;

/**
 * Concrete Animation implementation for looping animations
 * @author david
 */
public class LoopingAnimation extends Animation {

    public LoopingAnimation(Image[] images) {
        super(images);
    }

    @Override
    public void advance() {
        if (lastFrame()) {
            currentFrame = 0;
        } else {
            currentFrame++;
        }
    }
}
