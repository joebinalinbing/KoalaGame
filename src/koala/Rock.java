package koala;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

public class Rock extends GameObject implements Observer {

    private boolean show;

    public Rock(Image img, int x, int y, int Yspeed, boolean show) {
        super(img, x, y, Yspeed);
        this.show = true;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public void draw(ImageObserver obs, Graphics2D g) {
        g.drawImage(img, x, y, obs);
    }

    public void setYspeed(int Yspeed) {
        this.Yspeed = Yspeed;
    }

    public void update() {
    }

  

    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
