package drawing.builders;

import drawing.ImageLoader;
import drawing.animation.Animation;
import drawing.animation.LoopingAnimation;
import drawing.animation.OneShotAnimation;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Expression Builder [See Fowler&Parsons, Domain Specific Languages] to create
 * Animation objects from a list of frames
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

    /**
     * Helper method to add an image as a frame to the animation
     * 
     * @param resourcePath the path to the image resource within
     * @return the Expression Builder to continue the invocation
     * @throws IOException if the image cannot be loaded
     */
    public FrameListBuilder frame(String resourcePath) throws IOException {
        images.add(ImageLoader.imageResource(resourcePath));
        return this;
    }

    /**
     * Method chain terminator for creating looping animations
     * 
     * @return the generated animation
     */
    public Animation loop() {
        return new LoopingAnimation(images.toArray(new Image[0]));
    }

    /**
     * Method chain terminator for creating one-shot animations
     * 
     * @return the generated animation
     */
    public Animation stop() {
        return new OneShotAnimation(images.toArray(new Image[0]));
    }

    public static FrameListBuilder buildAnimation() {
        return new FrameListBuilder();
    }
}
