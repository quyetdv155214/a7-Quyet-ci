package views;

import model.BulletModel;

import java.awt.*;

/**
 * Created by q on 12/5/2016.
 */
public class BulletView {
    private Image image;

    public BulletView(Image image) {
        this.image = image;
    }

    public void draw(Graphics g, BulletModel bulletModel) {
        g.drawImage(image, bulletModel.getX(), bulletModel.getY(), 13, 33, null);
    }
}
