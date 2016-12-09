import controllers.*;
import model.Model;
import utils.Utils;
import views.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by q on 12/5/2016.
 */
public class GameWindow extends Frame implements Runnable {
    private static final int TIME_CREATE_ENEMY = 3000;
    private static final String ENEMY_PATH = "resources/enemy_plane_white_1.png";
    Image background;
    PlaneController planeController;
    KeySetting keySetting;

    BufferedImage backBuffer;

    private ArrayList<BulletController> bulletControllers;
    private ArrayList<EnemyControler> enemyControlers;
    private ArrayList<EnemyBulletControler> enemyBulletControllers;
    private EnemyControler enemyControler;
    private String pathPlaneImage = "resources/plane2.png";
    private String pathBulletImage = "resources/bullet.png";
    private String pathBackGround = "resources/background.png";
    int planeX = 300;
    int planeY = 500;
    int planeWidth = 60;
    int planeHeith = 70;

    public GameWindow() {
        keySetting = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        planeController = PlaneController.createPlane(planeX, planeY, planeWidth, planeHeith, pathPlaneImage);
        planeController.setKeySetting(keySetting);

        bulletControllers = new ArrayList<>();
//        bulletController = BulletController.createBullet()
//        enemyControler = EnemyControler.createPlane(300, 0, 100, 100, ENEMY_PATH);
        background = Utils.loadImage(pathBackGround);
        enemyControlers = new ArrayList<>();
        setVisible(true);
        setSize(800, 600);
        backBuffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("window Opened!");
            }

            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed");

                planeController.keyPress(e);
                if ((e.getKeyCode() == KeyEvent.VK_SPACE)) {
                    BulletController bulletController = new BulletController(
                            new Model(planeController.getModel().getX() +
                                    planeController.getModel().getWidth() / 2 - 6,
                                    planeController.getModel().getY() - 30,
                                    12, 30),
                            new View(Utils.loadImage(pathBulletImage))
                    );
                    bulletControllers.add(bulletController);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.repaint();
    }


    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphic = backBuffer.getGraphics();
        backBufferGraphic.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
        planeController.draw(backBufferGraphic);
//        enemyControler.draw(backBufferGraphic);
//        for (EnemyBulletControler eb : enemyControler.getListBullet()){
//            eb.draw(backBufferGraphic);
//        }
        for (BulletController bulletController : bulletControllers) {
            bulletController.draw(backBufferGraphic);
        }


        if (enemyControlers != null) {
            for (EnemyControler enemyControler : enemyControlers) {
                enemyControler.draw(backBufferGraphic);
                //draw enemy bullet
                for (EnemyBulletControler eb : enemyControler.getListBullet()) {
                    eb.draw(backBufferGraphic);
                }
            }
            ;

        }
        g.drawImage(backBuffer, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    int time = 0;

    @Override
    public void run() {
        while (true) {
            try {

                this.repaint();
                for (BulletController b : bulletControllers) {
                    b.run();
                }
                Thread.sleep(10);
                time += 10;
//                enemyControler.run();
//                if(time % 1000 ==0) enemyControler.addBullet();;
//                for (EnemyBulletControler eb : enemyControler.getListBullet()){
//                    eb.run();
//                }
//                 create

                if (time % TIME_CREATE_ENEMY == 0) {
                    enemyControlers.add(EnemyControler.createPlane(300, 0, 80, 90, ENEMY_PATH));

                }
                if (time % 2000 == 0)
                    for (EnemyControler e : enemyControlers) {
                        e.addBullet();
                    }

                if (enemyControlers.size() > 0) {
                    for (EnemyControler e : enemyControlers) {
                        e.run();
                        for (EnemyBulletControler eb : e.getListBullet()) {
                            eb.run();
                        }
                    }
                }
//                  remove
                ArrayList<EnemyControler> temp = new ArrayList<>();
                for (EnemyControler e : enemyControlers) {
                    if (e.getModel().getY() > 900) {
                        temp.add(e);
                    }
                }
                enemyControlers.removeAll(temp);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
