package drawing.builders;

import drawing.ImageLoader;
import drawing.animation.Animation;
import drawing.animation.LoopingAnimation;
import drawing.animation.OneShotAnimation;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author drm
 */
public class FrameListBuilder {

    private ArrayList<Image> images;

    public FrameListBuilder() {
        this("");
    }

    public FrameListBuilder(String basePath) {
        this.images = new ArrayList<Image>();
    }

    public FrameListBuilder frame(String resourcePath) throws IOException {
        images.add(ImageLoader.imageResource(resourcePath));
        return this;
    }

    public Animation loop() {
        return new LoopingAnimation(images.toArray(new Image[0]));
    }

    public Animation end() {
        return new OneShotAnimation(images.toArray(new Image[0]));
    }

    public static FrameListBuilder buildAnimation() {
        return new FrameListBuilder();
    }
}
