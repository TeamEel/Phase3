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
public class RangeBuilder {
    private String format;
    private int start;
    private int end;
    
    public RangeBuilder() {
    }
    
    public RangeBuilder format(String format) {
        this.format = format;
        return this;
    }
    
    public RangeBuilder from(int start) {
        this.start = start;
        return this;
    }
    
    public RangeBuilder to(int end) {
        this.end = end;
        return this;
    }
    
    public Animation end() throws IOException {
        return new OneShotAnimation(buildArray());
    }
    
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
