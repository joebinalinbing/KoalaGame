package koala;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

public class Exit extends GameObject implements Observer {

    public Exit(BufferedImage img, int x, int y, int Yspeed) {
        super(img, x, y, Yspeed);
    }

    public void draw(ImageObserver obs, Graphics2D g) {
        g.drawImage(img, x, y, obs);
    }

    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
