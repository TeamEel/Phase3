package drawing.builders;

import drawing.Sprite;
import drawing.animation.Animation;
import java.io.IOException;

/**
 *
 * @author drm
 */
public class BuildSprite {

    public static Sprite staticSprite(String resourcePath) throws IOException {
        return new Sprite(BuildAnimationSet.singleImage(resourcePath));
    }
}
