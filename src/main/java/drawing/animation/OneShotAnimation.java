package drawing.animation;

import java.awt.Image;

/**
 * Concrete Animation implementation for one-shot animations
 * @author david
 */
public class OneShotAnimation extends Animation {

    public OneShotAnimation(Image[] images) {
        super(images);
    }

    @Override
    public void advance() {
        if (!lastFrame()) {
            currentFrame++;
        }
    }
}
