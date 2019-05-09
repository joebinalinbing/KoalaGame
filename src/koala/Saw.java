package koala;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

public class Saw extends GameObject implements Observer {

    boolean isUp, isDown, isLeft, isRight;
    private ID id;
    int type;
    private int Yspeed;
    public Saw(Image img, int x, int y, int Yspeed, ID id) {
        super(img, x, y, Yspeed);
        isUp = true;
        this.id = id;
        isRight = true;
        this.Yspeed=Yspeed;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void draw(ImageObserver obs, Graphics2D g) {
        g.drawImage(img, x, y, obs);
    }

    public void setYspeed(int Yspeed) {
        this.Yspeed = Yspeed;
    }

    public void update() {

         if (isUp && this.id == id.VSaw) {
            y += Yspeed ;
            if (y == 480) {
                isUp = false;
                isDown = true;
            }
        }
        if (isDown && this.id == id.VSaw) {
            y -= Yspeed ;
            if (y == 120) {
                isUp = true;
                isDown = false;
            }
        }
        
        if (isUp && this.id == id.Saw) {
            y += Yspeed ;
            if (y == 560) {
                isUp = false;
                isDown = true;
            }
        }
        if (isDown && this.id == id.Saw) {
            y -= Yspeed ;
            if (y == 240) {
                isUp = true;
                isDown = false;
            }
        }

        if (isRight == true && this.id == id.HSaw) {

            x += Yspeed ;

            if (x == 560) {
                isRight = false;
                isLeft = true;
            }
        }

        if (isLeft && this.id == id.HSaw) {
            x -= Yspeed ;
            if (x == 320) {
                isRight = true;
                isLeft = false;
            }
        }
        
        if (isRight == true && this.id == id.HSaw1) {

            x += Yspeed ;

            if (x == 480) {
                isRight = false;
                isLeft = true;
            }
        }

        if (isLeft && this.id == id.HSaw1) {
            x -= Yspeed ;
            if (x == 200) {
                isRight = true;
                isLeft = false;
            }
        }
    }

    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
