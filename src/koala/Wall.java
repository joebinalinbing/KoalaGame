package koala;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

public class Wall implements Observer {

    boolean breakableWall;

    private int coolDown, width, height;

    protected int x, y, Yspeed;

    protected BufferedImage img;

    boolean show;

    boolean weakWall = true;

    public Wall(BufferedImage img, int x, int y, int Yspeed, boolean weakWall) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.coolDown = 0;
        this.weakWall = weakWall;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.show = true;
    }

    public void draw(ImageObserver obs, Graphics2D g) {
        if (show && coolDown == 0) {
            g.drawImage(img, x, y, obs);
        } else {
            coolDown--;
        }
    }

    public boolean isWeakWall() {
        return weakWall;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void update(Observable obj, Object arg) {
        GameEvents ge = (GameEvents) arg;
        if (ge.type == 2) {
            String msg = (String) ge.event;
        }
    }
}
