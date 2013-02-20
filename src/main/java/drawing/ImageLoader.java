package drawing;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Utility class to help with loading images from class resources
 * 
 * @author drm
 */
public class ImageLoader {

    public static Image imageResource(String resourcePath) throws IOException {
        URL resourceURL = ImageLoader.class.getResource(resourcePath);
        if (resourceURL == null) {
            throw new FileNotFoundException(resourcePath);
        }
        return ImageIO.read(resourceURL);
    }
}
