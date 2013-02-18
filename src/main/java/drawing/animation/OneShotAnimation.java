package drawing.animation;

import java.awt.Image;

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
