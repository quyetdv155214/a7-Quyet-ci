import controllers.BulletController;
import controllers.KeySetting;
import controllers.PlaneController;
import model.BulletModel;
import model.PlaneModel;
import views.BulletView;
import views.PlaneView;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by q on 12/5/2016.
 */
public class GameWindow extends Frame implements Runnable {
    Image background;
    PlaneController planeController;
    KeySetting keySetting;
    PlaneModel planeModel;
    PlaneView planeView;
    BufferedImage backBuffer;
    BulletController bulletController;
    ArrayList<BulletController> bulletControllers;
    String pathPlaneImage = "resources/plane2.png";
    String pathBulletImage ="resources/bullet.png";
    String pathBackGround = "resources/background.png";

    public GameWindow() {
        keySetting = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        planeModel = new PlaneModel(300, 300);
        planeView = new PlaneView(loadImage(pathPlaneImage));
        planeController = new PlaneController(planeModel, planeView);
        planeController.setKeySetting(keySetting);
        bulletControllers =new ArrayList<>();

        background = loadImage(pathBackGround);

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
                    int bulletX = planeController.getPlaneModel().getX() + 30;
                    int bulletY = planeController.getPlaneModel().getY() - 15;
                    BulletView bulletView = new BulletView(loadImage(pathBulletImage));
                    BulletModel bulletModel=new BulletModel(bulletX,bulletY);
                    bulletControllers.add(new BulletController(bulletModel,bulletView));
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
        for (BulletController bulletController: bulletControllers) {
            bulletController.draw(backBufferGraphic);
        }
        g.drawImage(backBuffer, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void run() {
        while (true) {
            try {

this.repaint();
                Thread.sleep(10);
                for (int i = 0; i < bulletControllers.size(); i++) {
                    bulletControllers.get(i).getBulletModel().move(0,-5);
                    if (bulletControllers.get(i).getBulletModel().getY() < 0) {
                        bulletControllers.remove(i);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Image loadImage(String path) {
        try {
            Image image = ImageIO.read(new File(path));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
