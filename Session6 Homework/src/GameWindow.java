import controllers.BulletController;

import controllers.KeySetting;
import controllers.PlaneController;
import controllers.gif.BombManger;
import controllers.manangers.*;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.util.Vector;

import static utils.Utils.loadImage;

// Data abstraction

/**
 * Created by apple on 11/30/16.
 */
public class GameWindow extends Frame implements Runnable {
    Image background;

    PlaneController planeController;

    EnemyControllerManager enemyControllerManager;
    BulletControlerManager bulletControlerManager;
    BombManger bombManger;

    Vector<BulletController> bulletVector;
    BufferedImage backBuffer;

    public GameWindow() {
        bombManger = new BombManger();

        enemyControllerManager = new EnemyControllerManager();

//        bulletVector = new Vector<>();
        bulletControlerManager = new BulletControlerManager();
//         planeControlerManager = new PlaneControlerManager();

        planeController = PlaneController.instance;

        planeController.keySetting = new KeySetting(
                KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT);
//        planeControlerManager.add(planeController);

        setVisible(true);
        setSize(600, 400);

        backBuffer = new BufferedImage(600, 400, BufferedImage.TYPE_INT_ARGB);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
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
        background = loadImage("resources/background.png");

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
//
                planeController.keyPressed(e);

                if (e.getKeyCode() == KeyEvent.VK_SPACE && planeController.getModel().isAlive()) {
//
                    bulletControlerManager.createBullet(planeController.getModel().getMidX(), planeController.getModel().getY());

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

//        addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//            }
//            boolean press;
//            @Override
//            public void mousePressed(MouseEvent e) {
//                timer.scheduleAtFixedRate(task, 0, 1000); // Time is in milliseconds
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                task.cancel();
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//
//            }
//        });
    }


    //Utilities


    @Override
    public void update(Graphics g) {
        // Prepare backbuffer
        Graphics backBufferGraphics = backBuffer.getGraphics();
        backBufferGraphics.drawImage(background, 0, 0, 600, 400, null);
        planeController.draw(backBufferGraphics);

//        for (BulletController bullet : bulletVector)
//            bullet.draw(backBufferGraphics);
        backBufferGraphics.drawString(planeController.getModel().getHp()+"",100,100 );
        backBufferGraphics.drawString("num of enemy : " + BodyManager.instance.getNumEnemy(),100,120);

        enemyControllerManager.draw(backBufferGraphics);
        bombManger.draw(backBufferGraphics);

        bulletControlerManager.draw(backBufferGraphics);

        // Update window
        g.drawImage(backBuffer, 0, 0, 600, 400, null);
    }

    boolean die = true;

    @Override
    public void run() {
        while (true) {
            try {
                this.repaint();
                Thread.sleep(17);

//                for (BulletController bullet : bulletVector)
//                    bullet.run();


//                enemyControllerManager.run();
//                bulletControlerManager.run();

                BodyManager.instance.checkContact();
                if (planeController.getModel().isAlive()) {
                    enemyControllerManager.run();
                    bulletControlerManager.run();
                    bombManger.run();
                } else if (die) {
                    System.out.println("you die");
                    die = false;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
