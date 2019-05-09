package koala;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Player {

    private int life, ID, score;

    private PlayerTank myTank;

    private int up, down, left, right, fire;

    private int playerTankDamage = 1;

    private BufferedImage tankImg1, tankImg2;

    private boolean isRescued;

    public Player(int id, int life, boolean isRescued, int up, int down, int left, int right, int fire) {
        this.ID = id;
        this.life = life;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.fire = fire;
        this.score = 0;
        this.isRescued = false;
        BufferedImageLoader loader = new BufferedImageLoader();
        tankImg1 = loader.loadImage("/Resources/Koala_stand.png");
        myTank = new PlayerTank((BufferedImage) tankImg1, this.life, playerTankDamage, 50, ID * 10, 1, up, down, left, right, fire);
    }

    public boolean isIsRescued() {
        return isRescued;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setIsRescued(boolean isRescued) {
        this.isRescued = isRescued;
    }

    public int getScore() {
        return this.score;
    }

    public int getPlayerID() {
        return this.ID;
    }

    public PlayerTank getPlane() {
        return this.myTank;
    }

    public boolean isAlive() {
        return !myTank.getBoom();
    }

    public void addScore(int s) {
        this.score += s;
    }
}
