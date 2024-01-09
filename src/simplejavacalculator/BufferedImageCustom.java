package simplejavacalculator;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * This class provides methods for working with images.
 */
public class ImageUtils {

    /**
     * Loads an image from the specified resource path.
     *
     * @param resourcePath The path to the image resource.
     * @return The loaded Image object.
     * @throws IOException If an error occurs while reading the image.
     */
    public static Image loadImage(String resourcePath) throws IOException {
        InputStream inputStream = ImageUtils.class.getResourceAsStream(resourcePath);
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        return bufferedImage;
    }
}
