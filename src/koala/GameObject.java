package koala;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

public class GameObject {

    protected int x, y, width, height, Yspeed;

    protected Image img;

    public ImageObserver observer;

    public GameObject(BufferedImage img, int x, int y, int Yspeed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.Yspeed = Yspeed;
    }

    public GameObject(Image img, int x, int y, int Yspeed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.Yspeed = Yspeed;
    }

    public void setImage(Image img) {
        this.img = img;
        this.height = img.getWidth(observer);
        this.width = img.getHeight(observer);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setX(int a) {
        this.x = a;
    }

    public void setY(int b) {
        this.y = b;
    }

    public void draw(Graphics g, ImageObserver obs) {
        g.drawImage(img, x, y, obs);
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
