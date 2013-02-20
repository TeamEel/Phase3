package drawing.builders;

import drawing.animation.Animation;
import java.io.IOException;

/**
 * Helper methods to create Expression Builders for Animations
 * @author drm
 */
public class BuildAnimation {

    public static FrameListBuilder frames() {
        return new FrameListBuilder();
    }

    public static RangeBuilder range() {
        return new RangeBuilder();
    }

    public static Animation singleFrame(String resourcePath) throws IOException {
        return frames().frame(resourcePath).stop();
    }
}
