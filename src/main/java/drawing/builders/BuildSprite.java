package drawing.builders;

import drawing.Sprite;
import java.io.IOException;

/**
 * Helper methods to create Sprites
 * @author drm
 */
public class BuildSprite {

    public static Sprite staticSprite(String resourcePath) throws IOException {
        return new Sprite(BuildAnimationSet.singleImage(resourcePath));
    }
}
