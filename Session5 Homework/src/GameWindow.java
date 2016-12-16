import controllers.BulletController;

import controllers.KeySetting;
import controllers.PlaneController;
import controllers.manangers.BodyManager;
import controllers.manangers.EnemyControllerManager;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

    Vector<BulletController> bulletVector;
    BufferedImage backBuffer;

    public GameWindow() {

        enemyControllerManager = new EnemyControllerManager();

        bulletVector = new Vector<>();

        planeController = PlaneController.createPlane(300, 300);

        planeController.keySetting = new KeySetting(
                KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT);

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

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    BulletController bulletController = BulletController.createBullet(
                            planeController.getModel().getMidX(), planeController.getModel().getY());
                    bulletVector.add(bulletController);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }


    //Utilities


    @Override
    public void update(Graphics g) {
        // Prepare backbuffer
        Graphics backBufferGraphics = backBuffer.getGraphics();
        backBufferGraphics.drawImage(background, 0, 0, 600, 400, null);
        planeController.draw(backBufferGraphics);

        for (BulletController bullet : bulletVector)
            bullet.draw(backBufferGraphics);


        enemyControllerManager.draw(backBufferGraphics);

        // Update window
        g.drawImage(backBuffer, 0, 0, 600, 400, null);
    }


    @Override
    public void run() {
        while (true) {
            try {
                this.repaint();
                Thread.sleep(17);

                for (BulletController bullet : bulletVector)
                    bullet.run();


                BodyManager.instance.checkContact();
                enemyControllerManager.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
