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
 * Animation objects from files following a pattern
 * 
 * Most animations assets are generated as a list of files with a common prefix,
 * followed by a number, followed by an extension. This class provides helpers
 * to make it easy to construct animation objects from assets following this pattern.
 * 
 * Will accept from() and to() arguments in either order, to easily allow
 * creation of 'reversed' animations.
 * 
 * @author drm
 */public class RangeBuilder {

    private String format;
    private int start;
    private int end;

    public RangeBuilder() {
    }

    /**
     * Fluent helper to set format string for image assets
     * 
     * @param format the format string to use
     * @return the Expression Builder to continue the invocation
     */
    public RangeBuilder format(String format) {
        this.format = format;
        return this;
    }

    /**
     * Fluent helper to set the start of the range of numbers for
     * image assets
     * 
     * Start number is inclusive (i.e. an image with this number will be added)
     * 
     * @param start the first number to interpolate into the format string
     * @return the Expression Builder to continue the invocation
     */
    public RangeBuilder from(int start) {
        this.start = start;
        return this;
    }

    /**
     * Fluent helper to set the stop of the range of numbers for
     * image assets
     * 
     * End number is inclusive (i.e. an image with this number will be added)
     * 
     * @param stop the last number to interpolate into the format string
     * @return the Expression Builder to continue the invocation
     */
    public RangeBuilder to(int end) {
        this.end = end;
        return this;
    }

    /**
     * Method chain terminator for creating one-shot animations.
     * 
     * @return the generated Animation
     * @throws IOException if the images cannot be loaded
     */
    public Animation stop() throws IOException {
        return new OneShotAnimation(buildArray());
    }

    /**
     * Method chain terminator for creating looping Animations
     * @return the generated Animation
     * @throws IOException if the images cannot be loaded
     */
    public Animation loop() throws IOException {
        return new LoopingAnimation(buildArray());
    }

    private Image[] buildArray() throws IOException {
        ArrayList<Image> images = new ArrayList<Image>();

        if (start < end) {
            for (int i = start; i <= end; i++) {
                images.add(loadImage(i));
            }
        } else {
            for (int i = start; i >= end; i--) {
                images.add(loadImage(i));
            }
        }

        return images.toArray(new Image[0]);
    }

    private Image loadImage(int i) throws IOException {
        return ImageLoader.imageResource(imageName(i));
    }

    private String imageName(int i) {
        return String.format(format, i);
    }
}
