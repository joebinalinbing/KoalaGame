package koala;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

public class Switch extends GameObject implements Observer {

    boolean isUp, isDown, show;
    ID id;
    public Switch(Image img, int x, int y, int Yspeed, boolean show, ID id) {
        super(img, x, y, Yspeed);
        isUp = true;
        this.id=id;
        
    }

    public void draw(ImageObserver obs, Graphics2D g) {
        g.drawImage(img, x, y, obs);
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
