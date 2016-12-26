import controllers.*;
import controllers.manangers.BodyManager;
import controllers.manangers.BombControllerManager;
import controllers.manangers.ControllerManager;
import controllers.manangers.EnemyControllerManager;
import utils.Utils;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import static utils.Utils.loadImage;

// Data abstraction

/**
 * Created by apple on 11/30/16.
 */
public class GameWindow extends Frame implements Runnable {
    Image background;

    BufferedImage backBuffer;
    GameSetting gameSetting;

    Vector<BaseController> controllers;
    private final Set<Integer> pressed = new HashSet<Integer>();

    public GameWindow() {

        configSettings();

        controllers = new Vector<>();
        controllers.add(ControllerManager.explosion);
//        controllers.add(new EnemyControllerManager());
        controllers.add(PlaneController.instance);
        controllers.add(BodyManager.instance);
        controllers.add(ControllerManager.enemyBullet);
        controllers.add(ControllerManager.bombBullet);
        controllers.add(new BombControllerManager());
        controllers.add(EnemyControllerManager.enemyControllerManager);

        setVisible(true);
        setSize(gameSetting.getWidth(), gameSetting.getHeight());

        backBuffer = new BufferedImage(gameSetting.getWidth(), gameSetting.getHeight(), BufferedImage.TYPE_INT_ARGB);

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
//
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
//                System.out.println("keyPressed");
                pressed.add(e.getKeyCode());
                PlaneController.instance.keyPressed(pressed);

            }

            @Override
            public void keyReleased(KeyEvent e) {
//                System.out.println("keyReleased");
                pressed.remove(e.getKeyCode());
            }
        });
    }

    private void configSettings() {
        PlaneController.instance.keySetting = new KeySetting(
                KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_SPACE
        );
        gameSetting = GameSetting.instance;
    }


    //Utilities


    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphics = backBuffer.getGraphics();
        // Prepare backbuffer
        backBufferGraphics.drawImage(background, 0, 0, gameSetting.getWidth(), gameSetting.getHeight(), null);
        if (PlaneController.instance.getLive() > 0) {
            Font font = new Font("Bauhaus 93",Font.BOLD,20);
            backBufferGraphics.setFont(font);
            backBufferGraphics.drawString("Hp : " + PlaneController.instance.getModel().getHp(), 100, 100);
            backBufferGraphics.drawString("Live : " + PlaneController.instance.getLive(), 100, 120);
            for (BaseController baseController : this.controllers) {
                baseController.draw(backBufferGraphics);
            }
        }else{
            backBufferGraphics.drawImage(Utils.loadImage("resources/gameOver.png"), 0, 0, gameSetting.getWidth(), gameSetting.getHeight(), null);

        }
        // Update window
        g.drawImage(backBuffer, 0, 0, gameSetting.getWidth(), gameSetting.getHeight(), null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.repaint();
                Thread.sleep(17);

                for(BaseController baseController: controllers) {
                    baseController.run();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
