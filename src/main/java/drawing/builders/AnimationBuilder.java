package drawing.builders;

import drawing.ImageLoader;
import drawing.animation.Animation;
import drawing.animation.LoopingAnimation;
import drawing.animation.OneShotAnimation;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author drm
 */
public class AnimationBuilder {

    private ArrayList<Image> images;
    private String basePath;

    public AnimationBuilder() {
        this("");
    }

    public AnimationBuilder(String basePath) {
        this.images = new ArrayList<Image>();
        this.basePath = basePath;
    }

    public AnimationBuilder frame(String resourcePath) throws IOException {
        images.add(ImageLoader.imageResource(resourcePath));
        return this;
    }

    public Animation loop() {
        return new LoopingAnimation(images.toArray(new Image[0]));
    }

    public Animation end() {
        return new OneShotAnimation(images.toArray(new Image[0]));
    }

    public static AnimationBuilder buildAnimation() {
        return new AnimationBuilder();
    }
}
