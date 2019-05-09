package koala;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class CollisionDetector {

    Random generator = new Random(1234567);

    GameEvents gameEvent1, gameEvent2, gameEvent3;

    SoundPlayer sp;

    Graphics2D g2;

    public CollisionDetector(GameEvents ge1, GameEvents ge2, GameEvents ge3) {
        this.gameEvent1 = ge1;
        this.gameEvent2 = ge2;
        this.gameEvent3 = ge3;

    }

    public void playerVSsaw(Player pp, Player pp2, Player pp3) {
        PlayerTank playerTank1 = pp.getPlane();
        PlayerTank playerTank2 = pp2.getPlane();
        PlayerTank playerTank3 = pp3.getPlane();
        Saw tnt;
        Rectangle p1box = new Rectangle(playerTank1.getX(), playerTank1.getY(), playerTank1.getWidth() / 3, playerTank1.getHeight());
        Rectangle p2box = new Rectangle(playerTank2.getX(), playerTank2.getY(), playerTank2.getWidth() / 3, playerTank2.getHeight());
        Rectangle p3box = new Rectangle(playerTank3.getX(), playerTank3.getY(), playerTank3.getWidth() / 3, playerTank3.getHeight());
        for (int i = 0; i < GameWorld.saws.size(); i++) {
            tnt = GameWorld.saws.get(i);
            Rectangle puBox = new Rectangle(tnt.getX(), tnt.getY(), tnt.getWidth(), tnt.getHeight());
            if (p1box.intersects(puBox) && GameWorld.saws.get(i).getId() == ID.HSaw) {
                // playerTank1.reduceHealth(3);
                //playerTank2.reduceHealth(3);
                //playerTank3.reduceHealth(3);
                System.out.println(GameWorld.saws.get(i).getX() + "x");
                System.out.println(GameWorld.saws.get(i).getY() + "y");
                sp = new SoundPlayer(2, "/Resources/Saw.wav");

            }
            if (p2box.intersects(puBox)) {
                playerTank1.reduceHealth(3);
                playerTank2.reduceHealth(3);
                playerTank3.reduceHealth(3);
                sp = new SoundPlayer(2, "/Resources/Saw.wav");

            }
            if (p3box.intersects(puBox)) {
                playerTank1.reduceHealth(3);
                playerTank2.reduceHealth(3);
                playerTank3.reduceHealth(3);
                sp = new SoundPlayer(2, "/Resources/Saw.wav");

            }
        }
    }

    public void playerVSlock(Player pp, Player pp2, Player pp3) {
        Switch rock;
        Lock locks;
        PlayerTank p1 = pp.getPlane();
        PlayerTank p2 = pp2.getPlane();
        PlayerTank p3 = pp3.getPlane();
        Rectangle p1_box = new Rectangle(p1.getX(), p1.getY(), p1.getWidth(), p1.getHeight());
        Rectangle p2_box = new Rectangle(p2.getX(), p2.getY(), p2.getWidth(), p2.getHeight());
        Rectangle p3_box = new Rectangle(p3.getX(), p3.getY(), p3.getWidth(), p3.getHeight());
        for (int i = 0; i < GameWorld.locks.size(); i++) {
            locks = GameWorld.locks.get(i);
            Rectangle eBox = new Rectangle(locks.getX(), locks.getY(), locks.getWidth(), locks.getHeight());
            if (p1_box.intersects(eBox)) {
                if (GameWorld.locks.get(i).isShow() == false) {
                    p1.x -= 0;
                    p1.x += 0;
                    p1.y += 0;
                    p1.y -= 0;

                }
                /*
                if (p1_box.x > wall.x && p1.isMoveLeft()== true) {
                    //p1.x += 1;
                    p1.setMoveLeft(false);
                    p1.setImage(GameWorld.sprites.get("koalaLeftStand"));
                } 
                if (p1_box.x < wall.x && p1.isMoveRight()== true) {
                   // p1.x -= 1;
                   p1.setMoveRight(false);
                   p1.setImage(GameWorld.sprites.get("koalaRightStand"));
                }
                if (p1_box.y > wall.y && p1.isMoveUp()== true) {
                    //p1.y += 1;
                    p1.setMoveUp(false);
                    p1.setImage(GameWorld.sprites.get("koalaUpStand"));
                } 
                if (p1_box.y < wall.y && p1.isMoveDown()== true) {
                   // p1.y -= 1;
                   p1.setMoveDown(false);
                   p1.setImage(GameWorld.sprites.get("koalaDownStand"));
                    
                }
            }
                
                 */

                if (p1_box.x > locks.x && GameWorld.locks.get(i).isShow() == true) {
                     p1.x += 3;
                    p1.setMoveLeft(false);
                    p1.setImage(GameWorld.sprites.get("koalaLeftStand"));

                } else if (p1_box.x < locks.x && GameWorld.locks.get(i).isShow() == true) {
                    p1.x -= 3;
                    p1.setMoveRight(false);
                    p1.setImage(GameWorld.sprites.get("koalaRightStand"));

                }
                if (p1_box.y > locks.y && GameWorld.locks.get(i).isShow() == true) {
                    //p1.y += 3;
                    p1.setMoveUp(false);
                    p1.setImage(GameWorld.sprites.get("koalaUpStand"));
                } else if (p1_box.y < locks.y && GameWorld.locks.get(i).isShow() == true) {
                    // p1.y -= 3;
                    p1.setMoveDown(false);
                    p1.setImage(GameWorld.sprites.get("koalaDownStand"));
                }
            }

            if (p2_box.intersects(eBox)) {
                if (GameWorld.locks.get(i).isShow() == false) {
                    p2.x -= 0;
                    p2.x += 0;
                    p2.y += 0;
                    p2.y -= 0;
                }
                if (p2_box.x > locks.x && GameWorld.locks.get(i).isShow() == true) {
                     p2.x += 3;
                    p2.setMoveLeft(false);
                    p2.setImage(GameWorld.sprites.get("koalaLeftStand"));
                } else if (p2_box.x < locks.x && GameWorld.locks.get(i).isShow() == true) {
                     p2.x -= 3;
                    p2.setMoveRight(false);
                    p2.setImage(GameWorld.sprites.get("koalaRightStand"));
                }
                if (p2_box.y > locks.y && GameWorld.locks.get(i).isShow() == true) {
                    // p2.y += 3;
                    p2.setMoveUp(false);
                    p2.setImage(GameWorld.sprites.get("koalaUpStand"));
                } else if (p2_box.y < locks.y && GameWorld.locks.get(i).isShow() == true) {
                    //p2.y -= 3;
                    p2.setMoveDown(false);
                    p2.setImage(GameWorld.sprites.get("koalaDownStand"));
                }
            }
            if (p3_box.intersects(eBox)) {
                if (GameWorld.locks.get(i).isShow() == false) {
                    p2.x -= 0;
                    p2.x += 0;
                    p2.y += 0;
                    p2.y -= 0;
                }
                if (p3_box.x > locks.x && GameWorld.locks.get(i).isShow() == true) {
                    p3.x += 3;
                    p3.setMoveLeft(false);
                    p3.setImage(GameWorld.sprites.get("koalaLeftStand"));
                } else if (p3_box.x < locks.x && GameWorld.locks.get(i).isShow() == true) {
                    p3.x -= 3;
                    p3.setMoveRight(false);
                    p3.setImage(GameWorld.sprites.get("koalaRightStand"));
                }
                if (p3_box.y > locks.y && GameWorld.locks.get(i).isShow() == true) {
                    // p3.y += 3;
                    p3.setMoveUp(false);
                    p3.setImage(GameWorld.sprites.get("koalaUpStand"));
                } else if (p3_box.y < locks.y && GameWorld.locks.get(i).isShow() == true) {
                    // p3.y -= 3;
                    p3.setMoveDown(false);
                    p3.setImage(GameWorld.sprites.get("koalaDownStand"));
                }
            }
        }
    }

    public void playerVSswitch(Player pp, Player pp2, Player pp3) {
        Switch rock;
        Lock locks;
        PlayerTank p1 = pp.getPlane();
        PlayerTank p2 = pp2.getPlane();
        PlayerTank p3 = pp3.getPlane();
        Rectangle p1_box = new Rectangle(p1.getX(), p1.getY(), p1.getWidth() / 2, p1.getHeight() / 2);
        Rectangle p2_box = new Rectangle(p2.getX(), p2.getY(), p2.getWidth() / 2, p2.getHeight() / 2);
        Rectangle p3_box = new Rectangle(p3.getX(), p3.getY(), p3.getWidth() / 2, p3.getHeight() / 2);
        for (int i = 0; i < GameWorld.switchs.size(); i++) {
            rock = GameWorld.switchs.get(i);
            Rectangle eBox = new Rectangle(rock.getX(), rock.getY(), rock.getWidth() / 2, rock.getHeight() / 2);

            if (p1_box.intersects(eBox) && GameWorld.switchs.get(i).isShow() == true) {

                System.out.print(eBox.x + "x\n");
                System.out.print(eBox.y + "x\n");
                GameWorld.locks.get(i).setShow(false);
                rock.setImage(GameWorld.sprites.get("redSwitch"));
                GameWorld.switchs.get(i).setShow(true);
                // sp = new SoundPlayer(2, "/Resources/Lock.wav");
            } //            else if (p1_box.intersects(eBox) && GameWorld.switchs.get(i).isShow() == true) {
            //
            //                GameWorld.locks.get(i).setShow(true);
            //                rock.setImage(GameWorld.sprites.get("switch1"));
            //
            //            } 
            else if (p2_box.intersects(eBox)) {
                GameWorld.locks.get(i).setShow(false);
                rock.setImage(GameWorld.sprites.get("redSwitch"));
                // sp = new SoundPlayer(2, "/Resources/Lock.wav");
            } //            else if (p2_box.intersects(eBox) && GameWorld.switchs.get(i).isShow() == true) {
            //
            //                GameWorld.locks.get(i).setShow(true);
            //                rock.setImage(GameWorld.sprites.get("switch2"));
            //
            //            } 
            else if (p3_box.intersects(eBox)) {
                GameWorld.locks.get(i).setShow(false);
                rock.setImage(GameWorld.sprites.get("redSwitch"));
                //sp = new SoundPlayer(2, "/Resources/Lock.wav");
            } //            else if (p3_box.intersects(eBox) && GameWorld.switchs.get(i).isShow() == true) {
            //
            //                GameWorld.locks.get(i).setShow(true);
            //                rock.setImage(GameWorld.sprites.get("switch3"));
            //
            //            } 
            else {
                rock.setImage(GameWorld.sprites.get("switch3"));
                //rock.setImage(GameWorld.sprites.get("switch2"));
                // rock.setImage(GameWorld.sprites.get("switch3"));
                GameWorld.locks.get(i).setShow(true);
                GameWorld.switchs.get(i).setShow(true);
            }
        }

    }

    public void rocktVStnt(Player player1, Player player2) {
        TNT wall;
        Rock rock;
        for (int i = 0; i < GameWorld.rocks.size(); i++) {
            for (int j = 0; j < GameWorld.tnts.size(); j++) {
                rock = GameWorld.rocks.get(i);
                Rectangle bulletBox = new Rectangle(rock.getX(), rock.getY(), rock.getWidth() / 2, rock.getHeight() / 2);
                wall = GameWorld.tnts.get(j);
                Rectangle wallBox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
                if (bulletBox.intersects(wallBox)) {
                    rock.setShow(false);
                    wall.setShow(false);
                }
            }
        }
    }

    public void rocktVSwall(Player player1, Player player2) {
        Wall wall;
        Rock rock;
        for (int i = 0; i < GameWorld.rocks.size(); i++) {
            for (int j = 0; j < GameWorld.wall.size(); j++) {
                rock = GameWorld.rocks.get(i);
                Rectangle bulletBox = new Rectangle(rock.getX(), rock.getY(), rock.getWidth(), rock.getHeight());
                wall = GameWorld.wall.get(j);
                Rectangle wallBox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
                if (bulletBox.intersects(wallBox)) {
                    gameEvent1.setValue("rock");
                    if (bulletBox.x > wallBox.x) {
                        GameWorld.rocks.get(i).x += 1;
                    }
                    if (bulletBox.x < wallBox.x) {
                        GameWorld.rocks.get(i).x -= 1;
                    }
                    if (bulletBox.y > wallBox.y) {
                        GameWorld.rocks.get(i).y += 1;
                    }
                    if (bulletBox.y < wallBox.y) {
                        GameWorld.rocks.get(i).y -= 1;
                    }
                }
            }
        }
    }

    public void playerVSrock(Player pp, Player pp2, Player pp3) {
        Rock rock;
        PlayerTank p1 = pp.getPlane();
        PlayerTank p2 = pp2.getPlane();
        PlayerTank p3 = pp3.getPlane();
        Rectangle p1_box = new Rectangle(p1.getX(), p1.getY(), p1.getWidth(), p1.getHeight());
        Rectangle p2_box = new Rectangle(p2.getX(), p2.getY(), p2.getWidth(), p2.getHeight());
        Rectangle p3_box = new Rectangle(p3.getX(), p3.getY(), p3.getWidth(), p3.getHeight());
        for (int i = 0; i < GameWorld.rocks.size(); i++) {
            rock = GameWorld.rocks.get(i);
            Rectangle eBox = new Rectangle(rock.getX(), rock.getY(), rock.getWidth() / 2, rock.getHeight() / 2);
            if (p1_box.intersects(eBox)) {
                gameEvent1.setValue("player");
                if (eBox.y > p1_box.y) {
                    GameWorld.rocks.get(i).y += 4;
                    sp = new SoundPlayer(2, "/Resources/Rock.wav");
                }
                if (rock.y < p1_box.y) {
                    GameWorld.rocks.get(i).y -= 4;
                    sp = new SoundPlayer(2, "/Resources/Rock.wav");
                }
                if (eBox.x > p1_box.x) {
                    GameWorld.rocks.get(i).x += 4;
                    sp = new SoundPlayer(2, "/Resources/Rock.wav");
                }
                if (eBox.x < p1_box.x) {
                    GameWorld.rocks.get(i).x -= 4;
                    sp = new SoundPlayer(2, "/Resources/Rock.wav");
                }
            }

            if (p2_box.intersects(eBox)) {
                gameEvent1.setValue("player");
                if (eBox.y > p2_box.y) {
                    GameWorld.rocks.get(i).y += 4;
                    sp = new SoundPlayer(2, "/Resources/Rock.wav");
                }
                if (rock.y < p2_box.y) {
                    GameWorld.rocks.get(i).y -= 4;
                    sp = new SoundPlayer(2, "/Resources/Rock.wav");
                }
                if (eBox.x > p2_box.x) {
                    GameWorld.rocks.get(i).x += 4;
                    sp = new SoundPlayer(2, "/Resources/Rock.wav");
                }
                if (eBox.x < p2_box.x) {
                    GameWorld.rocks.get(i).x -= 4;
                    sp = new SoundPlayer(2, "/Resources/Rock.wav");
                }
            }

            if (p3_box.intersects(eBox)) {
                gameEvent1.setValue("player");
                if (eBox.y > p3_box.y) {
                    GameWorld.rocks.get(i).y += 4;
                    sp = new SoundPlayer(2, "/Resources/Rock.wav");
                }
                if (rock.y < p3_box.y) {
                    GameWorld.rocks.get(i).y -= 4;
                    sp = new SoundPlayer(2, "/Resources/Rock.wav");
                }
                if (eBox.x > p3_box.x) {
                    GameWorld.rocks.get(i).x += 4;
                    sp = new SoundPlayer(2, "/Resources/Rock.wav");
                }
                if (eBox.x < p3_box.x) {
                    GameWorld.rocks.get(i).x -= 4;
                    sp = new SoundPlayer(2, "/Resources/Rock.wav");
                }
            }

        }
    }

    public void playerVSplayer(Player pp, Player pp2, Player pp3) {
        Rectangle pbox = new Rectangle(pp.getPlane().getX(), pp.getPlane().getY(), pp.getPlane().getWidth(), pp.getPlane().getHeight());
        Rectangle pbox2 = new Rectangle(pp2.getPlane().getX(), pp2.getPlane().getY(), pp2.getPlane().getWidth(), pp2.getPlane().getHeight());
        PlayerTank p1 = pp.getPlane();
        PlayerTank p2 = pp2.getPlane();
        PlayerTank p3 = pp3.getPlane();
        Rectangle p1_box = new Rectangle(p1.getX(), p1.getY(), p1.getWidth(), p1.getHeight());
        Rectangle p2_box = new Rectangle(p2.getX(), p2.getY(), p2.getWidth(), p2.getHeight());
        Rectangle p3_box = new Rectangle(p3.getX(), p3.getY(), p3.getWidth(), p3.getHeight());
        if (p1_box.intersects(p2_box) && !p1.getBoom()) {
            if (p1_box.x > p2_box.x) {
                //p1.x += 5;
                p1.setMoveLeft(false);
            } else if (p1_box.x < p2_box.x) {
                // p1.x -= 5;
                p1.setMoveRight(false);

            }
            if (p1_box.y > p2_box.y) {
                // p1.y += 5;
                p1.setMoveUp(false);
            } else if (p1_box.y < p2_box.y) {
                // p1.y -= 5;
                p1.setMoveDown(false);
            }
        }

        if (p1_box.intersects(p3_box)) {
            if (p1_box.x > p3_box.x) {
                //p1.x += 1;
                p1.setMoveLeft(false);
            } else if (p1_box.x < p3_box.x) {
                // p1.x -= 1;
                p1.setMoveRight(false);
            }
            if (p1_box.y > p3_box.y) {
                //p1.y += 1;
                p1.setMoveUp(false);
            } else if (p1_box.y < p3_box.y) {
                //p1.y -= 1;
                p1.setMoveDown(false);
            }
        }
        if (p2_box.intersects(p1_box)) {
            if (p2_box.x > p1_box.x) {
                // p2.x += 5;
                p2.setMoveLeft(false);
            } else if (p2_box.x < p1_box.x) {
                //p2.x -= 5;
                p2.setMoveRight(false);
            }
            if (p2_box.y > p1_box.y) {
                // p2.y += 5;
                p2.setMoveUp(false);
            } else if (p2_box.y < p1_box.y) {
                //p2.y -= 5;
                p2.setMoveDown(false);
            }
        }
        if (p2_box.intersects(p3_box)) {
            if (p2_box.x > p3_box.x) {
                // p2.x += 5;
                p2.setMoveLeft(false);
            } else if (p2_box.x < p3_box.x) {
                // p2.x -= 5;
                p2.setMoveRight(false);
            }
            if (p2_box.y > p3_box.y) {
                //p2.y += 5;
                p2.setMoveUp(false);
            } else if (p2_box.y < p3_box.y) {
                //p2.y -= 5;
                p2.setMoveDown(false);
            }
        }
        ///////////////                     Player 3

        if (p3_box.intersects(p1_box)) {
            if (p3_box.x > p1_box.x) {
                // p2.x += 5;
                p3.setMoveLeft(false);
            } else if (p3_box.x < p1_box.x) {
                //p2.x -= 5;
                p3.setMoveRight(false);
            }
            if (p3_box.y > p1_box.y) {
                // p2.y += 5;
                p3.setMoveUp(false);
            } else if (p3_box.y < p1_box.y) {
                //p2.y -= 5;
                p3.setMoveDown(false);
            }
        }
        if (p3_box.intersects(p2_box)) {
            if (p3_box.x > p2_box.x) {
                // p2.x += 5;
                p3.setMoveLeft(false);
            } else if (p3_box.x < p2_box.x) {
                // p2.x -= 5;
                p3.setMoveRight(false);
            }
            if (p3_box.y > p2_box.y) {
                //p2.y += 5;
                p3.setMoveUp(false);
            } else if (p3_box.y < p2_box.y) {
                //p2.y -= 5;
                p3.setMoveDown(false);
            }
        }

    }

    public void playerVSWall(Player pp, Player pp2, Player pp3) {
        Wall wall;
        PlayerTank p1 = pp.getPlane();
        PlayerTank p2 = pp2.getPlane();
        PlayerTank p3 = pp3.getPlane();
        Rectangle p1_box = new Rectangle(p1.getX(), p1.getY(), p1.getWidth(), p1.getHeight());
        Rectangle p2_box = new Rectangle(p2.getX(), p2.getY(), p2.getWidth(), p2.getHeight());
        Rectangle p3_box = new Rectangle(p3.getX(), p3.getY(), p3.getWidth(), p3.getHeight());
        for (int i = 0; i < GameWorld.wall.size(); i++) {
            wall = GameWorld.wall.get(i);
            Rectangle eBox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
            if (p1_box.intersects(eBox) && !p1.getBoom() && wall.show) {

                if (p1_box.x > wall.x && p1.isMoveLeft()== true) {
                    p1.x += 2;
                    p1.y += 2;
                    p1.y -= 2;
                    p1.setMoveLeft(false);
                    p1.setImage(GameWorld.sprites.get("koalaLeftStand"));
                }
                if (p1_box.x < wall.x && p1.isMoveRight()== true) {
                    p1.x -= 2;
                    p1.y += 2;
                    p1.y -= 2;
                    p1.setMoveRight(false);
                    p1.setImage(GameWorld.sprites.get("koalaRightStand"));
                }
                if (p1_box.y > wall.y && p1.isMoveUp()== true) {
                    p1.y += 2;
                    p1.x += 2;
                    p1.x -= 2;
                    p1.setMoveUp(false);
                    p1.setImage(GameWorld.sprites.get("koalaUpStand"));
                }
                if (p1_box.y < wall.y && p1.isMoveDown() == true) {
                    p1.y -= 2;
                    p1.x += 2;
                    p1.x -= 2;
                    p1.setMoveDown(false);
                    p1.setImage(GameWorld.sprites.get("koalaDownStand"));

                }
            }
            if (p2_box.intersects(eBox) && !p2.getBoom() && wall.show) {

                if (p2_box.x > wall.x && p2.isMoveLeft() == true) {
                    p2.x += 2;
                    p2.y += 2;
                    p2.y -= 2;
                    p2.setMoveLeft(false);
                    p2.setImage(GameWorld.sprites.get("koalaLeftStand"));
                }
                if (p2_box.x < wall.x && p2.isMoveRight() == true) {
                    p2.x -= 2;
                    p2.y += 2;
                    p2.y -= 2;
                    p2.setMoveRight(false);
                    p2.setImage(GameWorld.sprites.get("koalaRightStand"));
                }
                if (p2_box.y > wall.y && p2.isMoveUp() == true) {
                    p2.y += 2;
                    p2.x += 2;
                    p2.x -= 2;
                    p2.setMoveUp(false);
                    p2.setImage(GameWorld.sprites.get("koalaUpStand"));
                }
                if (p2_box.y < wall.y && p2.isMoveDown() == true) {
                    p2.y -= 2;
                    p2.x += 2;
                    p2.x -= 2;
                    p2.setMoveDown(false);
                    p2.setImage(GameWorld.sprites.get("koalaDownStand"));

                }
            }
            if (p3_box.intersects(eBox) && !p2.getBoom() && wall.show) {

                if (p3_box.x > wall.x && p3.isMoveLeft() == true) {
                   p3.x += 2;
                    p3.y += 2;
                    p3.y -= 2;
                    p3.setMoveLeft(false);
                    p3.setImage(GameWorld.sprites.get("koalaLeftStand"));
                }
                if (p3_box.x < wall.x && p3.isMoveRight() == true) {
                    p3.x -= 2;
                    p3.y += 2;
                    p3.y -= 2;
                    p3.setMoveRight(false);
                    p3.setImage(GameWorld.sprites.get("koalaRightStand"));
                }
                if (p3_box.y > wall.y && p3.isMoveUp() == true) {
                    p3.y += 2;
                    p3.x += 2;
                    p3.x -= 2;
                    p3.setMoveUp(false);
                    p3.setImage(GameWorld.sprites.get("koalaUpStand"));
                }
                if (p3_box.y < wall.y && p3.isMoveDown() == true) {
                    p3.y -= 2;
                    p3.x += 2;
                    p3.x -= 2;
                    p3.setMoveDown(false);
                    p3.setImage(GameWorld.sprites.get("koalaDownStand"));

                }
            }
        }
    }

    public void playerVSdetonator(Player player1, Player player2, Player player3) {
        PlayerTank playerTank1 = player1.getPlane();
        PlayerTank playerTank2 = player2.getPlane();
        PlayerTank playerTank3 = player3.getPlane();
        Detonator button;
        Rectangle p1box = new Rectangle(playerTank1.getX(), playerTank1.getY(), playerTank1.getWidth() / 2, playerTank1.getHeight() / 2);
        Rectangle p2box = new Rectangle(playerTank2.getX(), playerTank2.getY(), playerTank2.getWidth() / 2, playerTank2.getHeight() / 2);
        Rectangle p3box = new Rectangle(playerTank3.getX(), playerTank3.getY(), playerTank3.getWidth() / 2, playerTank3.getHeight() / 2);

        for (int i = 0; i < GameWorld.detonators.size(); i++) {
            button = GameWorld.detonators.get(i);
            Rectangle puBox = new Rectangle(button.getX(), button.getY(), button.getWidth() / 2, button.getHeight() / 2);
            if (p1box.intersects(puBox)) {
                GameWorld.tnts.clear();
            }
            if (p2box.intersects(puBox)) {
                GameWorld.tnts.clear();
            }
            if (p3box.intersects(puBox)) {
                GameWorld.tnts.clear();
            }
        }
    }

    public void playerVSexit(Player player1, Player player2, Player player3) {
        PlayerTank playerTank1 = player1.getPlane();
        PlayerTank playerTank2 = player2.getPlane();
        PlayerTank playerTank3 = player3.getPlane();
        Exit button;
        Rectangle p1box = new Rectangle(playerTank1.getX(), playerTank1.getY(), playerTank1.getWidth(), playerTank1.getHeight());
        Rectangle p2box = new Rectangle(playerTank2.getX(), playerTank2.getY(), playerTank2.getWidth(), playerTank2.getHeight());
        Rectangle p3box = new Rectangle(playerTank3.getX(), playerTank3.getY(), playerTank3.getWidth(), playerTank3.getHeight());
        for (int i = 0; i < GameWorld.exits.size(); i++) {
            button = GameWorld.exits.get(i);
            Rectangle puBox = new Rectangle(button.getX(), button.getY(), button.getWidth(), button.getHeight());
            if (p1box.intersects(puBox)) {
                player1.getPlane().setXY(160, 35);
                player1.getPlane().Yspeed = 0;
                player1.setIsRescued(true);
                GameWorld.rescuedCount++;
                sp = new SoundPlayer(2, "/Resources/Saved.wav");
            }
            if (p2box.intersects(puBox)) {
                player2.getPlane().setXY(200, 35);
                player2.getPlane().Yspeed = 0;
                player2.setIsRescued(true);
                GameWorld.rescuedCount++;
                sp = new SoundPlayer(2, "/Resources/Saved.wav");
            }
            if (p3box.intersects(puBox)) {
                player3.getPlane().setXY(240, 35);
                player3.getPlane().Yspeed = 0;
                player3.setIsRescued(true);
                GameWorld.rescuedCount++;
                System.out.println(GameWorld.rescuedCount);
                sp = new SoundPlayer(2, "/Resources/Saved.wav");
            }
        }
    }

    public void playerVStnt(Player player1, Player player2, Player player3) {
        PlayerTank playerTank1 = player1.getPlane();
        PlayerTank playerTank2 = player2.getPlane();
        PlayerTank playerTank3 = player3.getPlane();
        TNT tnt;
        Rectangle p1box = new Rectangle(playerTank1.getX(), playerTank1.getY(), playerTank1.getWidth(), playerTank1.getHeight());
        Rectangle p2box = new Rectangle(playerTank2.getX(), playerTank2.getY(), playerTank2.getWidth(), playerTank2.getHeight());
        Rectangle p3box = new Rectangle(playerTank3.getX(), playerTank3.getY(), playerTank3.getWidth(), playerTank3.getHeight());
        for (int i = 0; i < GameWorld.tnts.size(); i++) {
            tnt = GameWorld.tnts.get(i);
            Rectangle puBox = new Rectangle(tnt.getX(), tnt.getY(), tnt.getWidth(), tnt.getHeight());

            if (p1box.intersects(puBox)) {

                playerTank1.reduceHealth(3);
                playerTank2.reduceHealth(3);
                playerTank3.reduceHealth(3);

                sp = new SoundPlayer(2, "/Resources/Explosion.wav");
                // GameWorld.tnts.remove(i);
                gameEvent1.setValue("tnt");
            }
            if (p2box.intersects(puBox)) {
                playerTank2.setImage(GameWorld.sprites.get("dead"));
                playerTank1.reduceHealth(3);
                playerTank2.reduceHealth(3);
                playerTank3.reduceHealth(3);

                sp = new SoundPlayer(2, "/Resources/Explosion.wav");
                gameEvent1.setValue("tnt");
            }
            if (p3box.intersects(puBox)) {

                playerTank1.reduceHealth(3);
                playerTank2.reduceHealth(3);
                playerTank3.reduceHealth(3);
                sp = new SoundPlayer(2, "/Resources/Explosion.wav");
                // GameWorld.tnts.remove(i);
                gameEvent1.setValue("tnt");
            }
        }
    }

}
