package koala;

import java.awt.Image;
import java.awt.MediaTracker;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class BufferedImageLoader {

    private BufferedImage image;

    private Image img;

    public BufferedImage loadImage(String path) {
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public Image getSprite(String name) {
        URL url = GameWorld.class.getResource(name);
        img = java.awt.Toolkit.getDefaultToolkit().getImage(url);
        try {
            MediaTracker tracker = new MediaTracker(GameWorld.class.newInstance());
            tracker.addImage(img, 0);
            tracker.waitForID(0);
        } catch (Exception e) {
        }
        return img;
    }

    public Image getImg() {
        return img;
    }
}
