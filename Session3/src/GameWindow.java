import controllers.BulletController;
import controllers.KeySetting;
import controllers.PlaneController;
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
    Image background;
    PlaneController planeController;
    KeySetting keySetting;

    BufferedImage backBuffer;
    BulletController bulletController;
    private ArrayList<BulletController> bulletControllers;
    private String pathPlaneImage = "resources/plane2.png";
    private String pathBulletImage = "resources/bullet.png";
    private String pathBackGround = "resources/background.png";
    int planeX = 300;
    int planeY = 300;
    int planeWidth = 90;
    int planeHeith = 100;

    public GameWindow() {
        keySetting = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        planeController = PlaneController.createPlane(planeX, planeY, planeWidth, planeHeith, pathPlaneImage);
        planeController.setKeySetting(keySetting);

        bulletControllers = new ArrayList<>();
//        bulletController = BulletController.createBullet()
        background = Utils.loadImage(pathBackGround);

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
                                    planeController.getModel().getY() -30,
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
        for (BulletController bulletController : bulletControllers) {
            bulletController.draw(backBufferGraphic);
        }
        g.drawImage(backBuffer, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void run() {
        while (true) {
            try {

                this.repaint();
                for (BulletController b : bulletControllers) {
                    b.run();
//                        if(b.model().getY() <=0){
//                            bulletControllers.remove(b);
//                        }

                }
                Thread.sleep(10);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
