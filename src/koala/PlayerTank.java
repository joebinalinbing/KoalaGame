package koala;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Observer;
import java.util.Observable;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import static koala.GameWorld.player1;
import static koala.GameWorld.player2;
import static koala.GameWorld.player3;

public class PlayerTank extends GameObject implements Observer {

    Random generator = new Random(1234567);

    private int health, damage, bulletDamage;

    private int up, down, left, right, fire;

    private int lifeCount;

    private boolean boom;

    private BufferedImage bulletImg, leftBulletImg, rightBulletImg, lifeImg, koalaUp, koalaDown, koalaRight, koalaLeft, koalaDead;

    private BufferedImage[] healthBars, healthImg;

    private GameObject healthbar;

    String soundFileName;

    Graphics2D g2;

    private short angle = 0;

    SoundPlayer sp;

    private boolean moveLeft, moveRight, moveUp, moveDown;

    Player t;

    int shootDelay = 0;

    int rateOfFire = 17;

    Player player;

    PlayerTank(BufferedImage img, int life, int damage, int x, int y, int Yspeed, int up, int down, int left, int right, int fire) {
        super(img, x, y, Yspeed);
        this.health = 1;
        this.damage = damage;
        this.bulletDamage = 1;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.fire = fire;
        this.boom = false;
        this.t = t;
        this.healthBars = new BufferedImage[4];
        this.lifeCount = life;
        this.width = img.getWidth(null);
        this.shootDelay = 0;
        BufferedImageLoader loader = new BufferedImageLoader();
        koalaUp = loader.loadImage("/Resources/koala_up3.png");
        koalaDown = loader.loadImage("/Resources/koala_down7.png");
        koalaRight = loader.loadImage("/Resources/koala_right3.png");
        koalaLeft = loader.loadImage("/Resources/koala_left8.png");
        koalaDead = loader.loadImage("/Resources/Koala_dead.png");
        
    }

    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage += damage;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    
    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount += lifeCount;
    }

    public void addHealth(int h) {
        this.health += h;
    }

    public void setCoolDown(int coolDown) {
        this.shootDelay = coolDown;
    }

    public int getDamage() {
        return this.damage;
    }


    public boolean getBoom() {
        return this.boom;
    }

    public void reduceHealth(int d) {
        if (this.health < d) {
            this.isDied();
        }
        this.health -= d;
    }

    public void isDied() {
        
        //sp = new SoundPlayer(2, "/Resources/Explosion.wav");
        System.out.println("player TANK explodes");
        if (this.lifeCount > 1) {
            
            lifeCount--;
            this.health = 4;
            GameWorld.player1.getPlane().x = 50;
            GameWorld.player2.getPlane().x = 50;
            GameWorld.player3.getPlane().x = 50;
            GameWorld.player1.getPlane().y = 200;
            GameWorld.player2.getPlane().y = 300;
            GameWorld.player3.getPlane().y = 400;
            player1.getPlane().Yspeed=1;
        player2.getPlane().Yspeed=1;
        player3.getPlane().Yspeed=1;
        } else {
            this.x = 50;
            this.y = 10;
            boom = true;
        }
    }

    public void draw(Graphics g, ImageObserver obs) {
        if (!boom) {
            g.drawImage(img, x, y, img.getWidth(null), img.getHeight(null), obs);
        }
        if (boom) {
            g.drawImage(koalaDead, x, y, img.getWidth(null), img.getHeight(null), obs);
        }
    }

    
    @Override
    public void update(Observable obj, Object arg) {
        GameEvents ge = (GameEvents) arg;
        if (ge.type == 1 && !boom) {
            KeyEvent e = (KeyEvent) ge.event;
            if (e.getKeyCode() == left) {
                if (e.getID() == KeyEvent.KEY_RELEASED) {
                    moveLeft = false;
                    this.setImage(GameWorld.sprites.get("koalaLeftStand"));
                } else if (e.getID() == KeyEvent.KEY_PRESSED) {
                    moveLeft = true;
                }
            } else {
            }
            if (e.getKeyCode() == right) {
                if (e.getID() == KeyEvent.KEY_RELEASED) {
                    moveRight = false;
                    this.setImage(GameWorld.sprites.get("koalaRightStand"));
                } else if (e.getID() == KeyEvent.KEY_PRESSED) {
                    moveRight = true;
                }
            } else {
            }
            if (e.getKeyCode() == up) {
                if (e.getID() == KeyEvent.KEY_RELEASED) {
                    moveUp = false;
                    this.setImage(GameWorld.sprites.get("koalaUpStand"));
                } else if (e.getID() == KeyEvent.KEY_PRESSED) {
                    moveUp = true;
                }
            } else {
            }
            if (e.getKeyCode() == down) {
                if (e.getID() == KeyEvent.KEY_RELEASED) {
                    moveDown = false;
                    this.setImage(GameWorld.sprites.get("koalaDownStand"));
                } else if (e.getID() == KeyEvent.KEY_PRESSED) {
                    moveDown = true;
                }
            } else {
            }
            
            if (e.getKeyCode() == fire) {
                if (e.getID() == KeyEvent.KEY_RELEASED) {
                    
                //moveDown = false;
                    //this.setImage(GameWorld.sprites.get("koalaDownStand"));
                } else if (e.getID() == KeyEvent.KEY_PRESSED) {
                   
                }
            } else {
            }
            
        } else if (ge.type == 2) {
            String msg = (String) ge.event;
            String[] msgArray = new String[2];
            StringTokenizer st = new StringTokenizer(msg);
            int i = 0;
            while (st.hasMoreTokens()) {
                msgArray[i] = st.nextToken();
                i++;
            }
            if (msg.equals("rock")) {
                System.out.println("rock vs wall");
                //this.setImage(GameWorld.sprites.get("dead"));
            }
            if (msg.equals("player")) {
                System.out.println("p1 push rock");
            }
            if (msg.equals("switch")) {
                System.out.println("switch");
            }
            if (msg.equals("switchBack")) {
                System.out.println("switch back");
            }
            
        }
    }

    public void updateMove() {
        if (moveLeft == true) {
            x -= Yspeed;
            this.setImage(GameWorld.sprites.get("koalaLeft"));
            moveRight=moveUp=moveDown=false;
        }
        if (moveRight == true) {
            x += Yspeed;
            this.setImage(GameWorld.sprites.get("koalaRight"));
            moveLeft=moveUp=moveDown=false;
        }
        if (moveUp == true) {
            this.setImage(GameWorld.sprites.get("koalaUp"));
            y -= Yspeed;
            moveLeft=moveRight=moveDown=false;
        }
        if (moveDown == true) {
            this.setImage(GameWorld.sprites.get("koalaDown"));
            y += Yspeed;
            moveLeft=moveUp=moveUp=false;
        }
    }

    public int getTankCenterX() {
        return x + ((img.getWidth(null) / 60) / 2);
    }

    public int getTankCenterY() {
        return y + (img.getHeight(null) / 2);
    }

    public int getAngle() {
        return angle;
    }
}
