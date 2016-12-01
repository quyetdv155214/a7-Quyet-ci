import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by q on 30/11/2016.
 *
 * them ti thoi
 */
public class GameWindow extends Frame {
    Image background;
    Image plane3;
    Image enemy_plane;
    private int planeX = 250;
    private int planeY = 300;
    private int enemy_planeX = 300;
    private int enemy_planeY = 100;

    public GameWindow() {
        setVisible(true);
        setSize(800, 600);
        setResizable(false);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("WindowOpened");
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
                System.out.println("windowIconified");

            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("windowDeiconified");

            }

            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("windowActivated");

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        try {
            background = ImageIO.read(new File("resources/background.png"));
            plane3 = ImageIO.read(new File("resources/plane3.png"));
            enemy_plane = ImageIO.read(new File("resources/enemy_plane_white_1.png"));
        } catch (IOException e) {
            System.out.println("Load Image Fail");
            e.printStackTrace();
        }

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if ( planeY < 20) {
                            break;
                        }
                        planeY -= 5;
                        repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        if (planeY > 600 - 100) {
                            break;
                        }
                        planeY += 5;
                        repaint();
                        break;
                    case KeyEvent.VK_LEFT:
                        if(planeX == 0){
                            break;
                        }
                        planeX -= 5;
                        repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(planeX == 800-100)break;
                        planeX += 5;
                        repaint();
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, 800, 600, null);
        g.drawImage(plane3, planeX, planeY, 100, 100, null);
        g.drawImage(enemy_plane,enemy_planeX,enemy_planeY, null);
    }
}