package koala;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.net.URL;
import java.util.*;
import javax.swing.*;

public class GameWorld extends JPanel implements Runnable {

    private int quadrant1, quadrant2, quadrant3, quadrant4;

    private final int gameSizeX = 640;

    private final int gameSizeY = 480;

    private Dimension windowSize;

    private BufferedImage bimg2, leftImage, rightImage;

    private Thread thread;

    private BufferedImage bimg;

    Graphics2D g2;

    Random generator = new Random(1234567);

    private final int w = 1020;

    private final int h = 760;

    GameEvents gameEvent1, gameEvent2, gameEvent3, gameEvent4;

    int frameCount = 0;

    static BufferedImage background, wall1, bulletImg, tnt, tntButton, exit, moveRight, rescued, gameOver, win;

    static Player player1, player2, player3;

    static boolean youWin = false;

    CollisionDetector CD;

    SoundPlayer sp;
    public static BufferedImage level = null, level2 = null;

    static Image[] smallExp = new Image[6];

    static Image[] bigExp = new Image[7];

    static ArrayList<Exit> exits = new ArrayList<Exit>(100000);

    static ArrayList<Explosion> explosions = new ArrayList<Explosion>(100000);

    static ArrayList<Detonator> detonators = new ArrayList<Detonator>(100000);

    static ArrayList<Wall> wall = new ArrayList<Wall>();

    static ArrayList<TNT> tnts = new ArrayList<TNT>(10000000);

    static ArrayList<Saw> saws = new ArrayList<Saw>(10000000);

    static ArrayList<Rock> rocks = new ArrayList<Rock>(10000000);

    static ArrayList<Switch> switchs = new ArrayList<Switch>(10000000);

    static ArrayList<Lock> locks = new ArrayList<Lock>(10000000);

    static HashMap<String, Image> sprites;

    BufferedImageLoader loader;

    static int life = 2;
    public static int LEVEL = 1;
    static int rescuedCount;

    public void init() {
        setFocusable(true);
        setBackground(Color.white);
        sp = new SoundPlayer(1, "/Resources/Music.mid");
        sprites = new HashMap<String, Image>();
        loader = new BufferedImageLoader();
        win = loader.loadImage("/Resources/Congratulation.gif");
        gameOver = loader.loadImage("/Resources/gameOver.png");
        rescued = loader.loadImage("/Resources/Rescued.gif");
        background = loader.loadImage("/Resources/Background.bmp");
        level = loader.loadImage("/Resources/mymap1.png");
        level2 = loader.loadImage("/Resources/mymap2.png");
        wall1 = loader.loadImage("/Resources/wallblock.png");
        tnt = loader.loadImage("/Resources/TNT.png");
        tntButton = loader.loadImage("/Resources/detonator1.png");
        exit = loader.loadImage("/Resources/Exit1.gif");
        sprites.put("dead", loader.getSprite("/Resources/Koala_dead.png"));
        sprites.put("yellowLock", loader.getSprite("/Resources/Lock_yellow.gif"));
        sprites.put("yellowSwitch", loader.getSprite("/Resources/Switch_yellow.gif"));
        sprites.put("redLock", loader.getSprite("/Resources/Lock_red.gif"));
        sprites.put("redSwitch", loader.getSprite("/Resources/Switch_red.gif"));
        sprites.put("lock", loader.getSprite("/Resources/Lock_blue.gif"));
        sprites.put("switch1", loader.getSprite("/Resources/Switch_blue.png"));
        sprites.put("switch2", loader.getSprite("/Resources/Switch_yellow.png"));
        sprites.put("switch3", loader.getSprite("/Resources/Switch_red.png"));
        sprites.put("switchOn", loader.getSprite("/Resources/Switch_blue.gif"));
        sprites.put("rock", loader.getSprite("/Resources/rock.png"));
        sprites.put("saw", loader.getSprite("/Resources/sawV.gif"));
        sprites.put("sawR", loader.getSprite("/Resources/sawR.gif"));
        sprites.put("sawH", loader.getSprite("/Resources/saw.gif"));
        sprites.put("koalaRight", loader.getSprite("/Resources/koalaRight.gif"));
        sprites.put("koalaLeft", loader.getSprite("/Resources/koalaLeft.gif"));
        sprites.put("koalaDown", loader.getSprite("/Resources/koalaDown.gif"));
        sprites.put("koalaUp", loader.getSprite("/Resources/koalaUp.gif"));
        sprites.put("koalaRightStand", loader.getSprite("/Resources/koala_right3.png"));
        sprites.put("koalaLeftStand", loader.getSprite("/Resources/koala_left8.png"));
        sprites.put("koalaDownStand", loader.getSprite("/Resources/koala_down7.png"));
        sprites.put("koalaUpStand", loader.getSprite("/Resources/koala_up3.png"));
        player1 = new Player(25, life, false, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        player2 = new Player(30, life, false, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        player3 = new Player(45, life, false, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        gameEvent1 = new GameEvents();
        gameEvent2 = new GameEvents();
        gameEvent3 = new GameEvents();
        gameEvent1.addObserver(player1.getPlane());
        gameEvent2.addObserver(player2.getPlane());
        gameEvent3.addObserver(player3.getPlane());
        KeyControl key1 = new KeyControl(gameEvent1);
        KeyControl key2 = new KeyControl(gameEvent2);
        KeyControl key3 = new KeyControl(gameEvent3);
        addKeyListener(key1);
        addKeyListener(key2);
        addKeyListener(key3);
        CD = new CollisionDetector(gameEvent1, gameEvent2, gameEvent3);
        playerReset();

        loadLevel(level);
    }

    public void timelineControl() {
    }

    public void drawBackGroundWithTileImage() {
        for (int xx = 0; xx < 520 * 3; xx += 520) {
            for (int yy = 0; yy < 520 * 3; yy += 520) {
                g2.drawImage(background, xx, yy, null);
            }
        }
    }
    
    

    public void switchLevel() {
        clearMap();
        player1 = new Player(25, life, false, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        player2 = new Player(30, life, false, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        player3 = new Player(45, life, false, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        gameEvent1.addObserver(player1.getPlane());
        gameEvent2.addObserver(player2.getPlane());
        gameEvent3.addObserver(player3.getPlane());
        KeyControl key1 = new KeyControl(gameEvent1);
        KeyControl key2 = new KeyControl(gameEvent2);
        KeyControl key3 = new KeyControl(gameEvent3);
        addKeyListener(key1);
        addKeyListener(key2);
        addKeyListener(key3);
        playerReset();
        switch (GameWorld.LEVEL) {

            case 1:
                playerReset2();
                loadLevel(level2);
                break;

        }
        GameWorld.LEVEL++;
    }

    private void clearMap() {
        exits.clear();
        detonators.clear();
        wall.clear();
        tnts.clear();
        saws.clear();

        rocks.clear();

        switchs.clear();

        locks.clear();

    }

    public void playerReset() {

        player1.getPlane().setXY(50, 200);
        player2.getPlane().setXY(50, 300);
        player3.getPlane().setXY(50, 400);
        player1.getPlane().Yspeed = 1;
        player2.getPlane().Yspeed = 1;
        player3.getPlane().Yspeed = 1;
    }

    public void playerReset2() {

        player1.getPlane().setXY(50, 200);
        player2.getPlane().setXY(50, 300);
        player3.getPlane().setXY(240, 320);
        player1.getPlane().Yspeed = 1;
        player2.getPlane().Yspeed = 1;
        player3.getPlane().Yspeed = 1;
    }

    public void drawDemo() {
        String content = "PLAYER 1 SCORE: " + player1.getScore();
        String content1 = "PLAYER 2 SCORE: " + player2.getScore();
        Font stringFont = new Font("SansSerif", Font.PLAIN, 30);
        g2.setFont(stringFont);
        g2.setColor(Color.white);

        if (!player1.isAlive() /*&& player2.isAlive() && player3.isAlive()*/) {
            stringFont = new Font("SansSerif", Font.PLAIN, 25);
            g2.setFont(stringFont);
            drawBackGroundWithTileImage();

            g2.drawString("You Lose", w / 2, h / 2 + 100);
            g2.drawImage(gameOver, null, w / 2, h / 2);
        } else if (GameWorld.rescuedCount == 6) {
            stringFont = new Font("SansSerif", Font.PLAIN, 25);
            g2.setFont(stringFont);

            drawBackGroundWithTileImage();
            g2.drawString("YouWin", 400, h / 2);
            g2.drawImage(win, null, w / 2, h / 2);

        } else if (player1.isIsRescued() == true && player2.isIsRescued() == true && player3.isIsRescued() == true) {

            switchLevel();

        } else {

            g2.drawImage(rescued, null, 40, 30);

            CD.playerVSswitch(player1, player2, player3);
            CD.playerVSWall(player1, player2, player3);
            CD.playerVSexit(player1, player2, player3);
            CD.playerVStnt(player1, player2, player3);
            CD.playerVSplayer(player1, player2, player3);
            CD.playerVSdetonator(player1, player2, player3);
            CD.rocktVSwall(player1, player2);
            CD.playerVSrock(player1, player2, player3);
            CD.rocktVStnt(player1, player2);
            CD.playerVSlock(player1, player2, player3);
            CD.playerVSsaw(player1, player2, player3);
            drawBackGroundWithTileImage();
            buildWalls();
            g2.drawImage(rescued, null, 10, 35);
            for (int i = 0; i < saws.size(); i++) {
                saws.get(i).update();
            }
            for (int i = 0; i < tnts.size(); i++) {
                if (tnts.get(i).isShow() == true) {
                    tnts.get(i).draw(g2, this);
                } else {
                    tnts.remove(i);
                }
            }

            for (int i = 0; i < switchs.size(); i++) {
                switchs.get(i).draw(g2, this);
            }
            for (int i = 0; i < locks.size(); i++) {
                if (locks.get(i).isShow() == true) {
                    locks.get(i).draw(g2, this);
                }

            }
            for (int i = 0; i < exits.size(); i++) {
                exits.get(i).draw(g2, this);
            }
            for (int i = 0; i < detonators.size(); i++) {
                detonators.get(i).draw(g2, this);
            }
            for (int i = 0; i < rocks.size(); i++) {
                if (rocks.get(i).isShow() == true) {
                    rocks.get(i).draw(g2, this);
                } else {
                    rocks.remove(i);
                }
            }
            for (int i = 0; i < saws.size(); i++) {
                saws.get(i).draw(g2, this);
            }
            player1.getPlane().draw(g2, this);
            player1.getPlane().updateMove();
            player2.getPlane().draw(g2, this);
            player2.getPlane().updateMove();
            player3.getPlane().draw(g2, this);
            player3.getPlane().updateMove();
            Font stringFonts = new Font("SansSerif", Font.PLAIN, 30);
            frameCount++;

        }
    }

    public void paint(Graphics g) {
        if (bimg == null) {
            Dimension windowSize = getSize();
            bimg = (BufferedImage) createImage(windowSize.width, windowSize.height);
            g2 = bimg.createGraphics();
        }
        drawDemo();
        g.drawImage(bimg, 0, 0, this);

    }

    private void buildWalls() {
        for (int i = 0; i <= wall.size() - 1; i++) {
            if (wall.get(i).show) {
                wall.get(i).draw(this, g2);
            } else {
                wall.remove(i);
            }
        }
    }

    private void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                if (red == 255 && green == 0 && blue == 0) {
                    wall.add(new Wall(wall1, xx * 40, yy * 40, 0, false));
                }
                if (red == 0 && green == 0 && blue == 255) {
                    exits.add(new Exit(exit, xx * 40, yy * 40, 0));
                }
                if (red == 0 && green == 255 && blue == 0) {
                    tnts.add(new TNT(tnt, xx * 40, yy * 40, 0, true));
                }
                if (red == 255 && green == 255 && blue == 255) {
                    detonators.add(new Detonator(tntButton, xx * 40, yy * 40, 0));
                }
                if (red == 0 && green == 255 && blue == 255) {
                    saws.add(new Saw(sprites.get("saw"), xx * 40, yy * 40, 2, ID.Saw));
                }
                if (red == 255 && green == 0 && blue == 255) {
                    rocks.add(new Rock(sprites.get("rock"), xx * 40, yy * 40, 0, true));

                }
                if (red == 255 && green == 255 && blue == 224) {
                    switchs.add(new Switch(sprites.get("redSwitch"), xx * 40, yy * 40, 0, false, ID.BlueSwitch));
                }
                if (red == 255 && green == 255 && blue == 0) {
                    locks.add(new Lock(sprites.get("redLock"), xx * 40, yy * 40, 0, true));
                }

                if (red == 255 && green == 255 && blue == 200) {
                    locks.add(new Lock(sprites.get("redLock"), xx * 40, yy * 40, 0, true));
                }

                if (red == 255 && green == 255 && blue == 100) {
                    switchs.add(new Switch(sprites.get("redSwitch"), xx * 40, yy * 40, 0, true, ID.YellowSwitch));

                }
                if (red == 255 && green == 255 && blue == 30) {
                    saws.add(new Saw(sprites.get("sawR"), xx * 40, yy * 40, 4, ID.Saw));
                }

                if (red == 90 && green == 90 && blue == 0) {
                    switchs.add(new Switch(sprites.get("redSwitch"), xx * 40, yy * 40, 0, true, ID.RedSwitch));
                }

                if (red == 90 && green == 90 && blue == 90) {
                    locks.add(new Lock(sprites.get("redLock"), xx * 40, yy * 40, 0, true));
                }

                if (red == 50 && green == 50 && blue == 50) {
                    saws.add(new Saw(sprites.get("sawH"), xx * 40, yy * 40, 1, ID.HSaw));
                }

                if (red == 10 && green == 10 && blue == 10) {
                    saws.add(new Saw(sprites.get("sawR"), xx * 40, yy * 40, 1, ID.VSaw));
                }

                if (red == 100 && green == 100 && blue == 200) {
                    saws.add(new Saw(sprites.get("sawH"), xx * 40, yy * 40, 1, ID.HSaw1));
                }
            }
        }
    }

    public void start() {
        System.out.println();
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    private void tick() {
        System.out.print(GameWorld.player1.getPlane().x + "x\n");
        System.out.print(GameWorld.player1.getPlane().y + "y\n");
    }

    public void run() {
        Thread me = Thread.currentThread();
        tick();
        while (thread == me) {
            repaint();
            try {
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public static void main(String argv[]) {
        System.out.println(new File(".").getAbsolutePath() + "The current Working directory");
        final GameWorld demo = new GameWorld();
        demo.init();

        JFrame f = new JFrame("Wingman");
        f.addWindowListener(new WindowAdapter() {
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(demo.w, demo.h));
        f.setVisible(true);
        f.setResizable(false);
        demo.windowSize = f.getContentPane().getSize();
        f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        demo.start();
    }
}
