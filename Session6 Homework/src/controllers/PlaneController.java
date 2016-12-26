package controllers;

import controllers.enemy.EnemyBulletController;
import controllers.enemy.EnemyController;
import controllers.manangers.BodyManager;
import models.Model;
import utils.Utils;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by apple on 12/3/16.
 */
public class PlaneController extends Controller implements Body {

    private static final int SPEED = 5;

    public KeySetting keySetting;
    public static final PlaneController instance = createPlane(300, 300);

    private PlaneController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);

    }

    public void keyPressed(KeyEvent e) {
        if (keySetting != null && this.getModel().isAlive()) {
            int keyCode = e.getKeyCode();
            if (keyCode == keySetting.keyUp) {
                if (this.getModel().getY() > 30)
                    model.move(0, -SPEED);
            } else if (keyCode == keySetting.keyDown) {
                if (this.getModel().getY() < 400 - this.getModel().getHeight())
                    model.move(0, SPEED);
            } else if (keyCode == keySetting.keyLeft) {
                if (this.getModel().getX() < 600 - this.getModel().getWidth())
                    model.move(-SPEED, 0);
            } else if (keyCode == keySetting.keyRight) {
                if (this.getModel().getX() > 0)
                    model.move(SPEED, 0);
            }
        }
    }

    // Design pattern
    // Factory
    private static PlaneController createPlane(int x, int y) {
        PlaneController planeController = new PlaneController(
                new Model(x, y, 70, 50),
                new View(Utils.loadImage("resources/plane3.png"))
        );
        planeController.getModel().setHp(100);
        return planeController;
    }

    @Override
    public void onContact(Body orther) {
        if (orther instanceof EnemyBulletController) {
            this.getModel().decHp(1);

        }
        if (orther instanceof EnemyController) {
            this.getModel().decHp(2);
        }
    }
}
