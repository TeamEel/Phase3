package drawing;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author drm
 */
public class ImageLoader {
    public static Image imageResource(String resourcePath) throws IOException {
        return ImageIO.read(ImageLoader.class.getResource(resourcePath));
    }
}
