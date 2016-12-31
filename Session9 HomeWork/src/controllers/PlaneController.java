package controllers;

import controllers.enemies.EnemyBulletController;
import controllers.manangers.BodyManager;
import controllers.manangers.ControllerManager;
import controllers.scenes.GameScene;
import controllers.scenes.PlaneDetroyListener;
import models.Model;
import utils.Utils;
import views.Animation;
import views.SingleView;
import views.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;

/**
 * Created by apple on 12/3/16.
 */
public class PlaneController extends Controller implements Body, PlaneDetroyListener{

    private static final int SPEED = 6;
    private int live;
    public KeySetting keySetting;

    private ControllerManager bulletManager;

    public static final PlaneController instance = createPlane(
            GameSetting.instance.getWidth() / 2 - 30 / 2, GameSetting.instance.getHeight() - 100);

    private PlaneController(Model model, View view) {
        super(model, view);
        bulletManager = new ControllerManager();
        live = 3;
        BodyManager.instance.register(this);
    }



    int timeRebond = 300;

    @Override
    public void run() {

        super.run();
        bulletManager.run();
        if (model.getHp() <= 0) {
            if (timeRebond == 300) {
                live--;
                destroy();
                model.setY(GameSetting.instance.getHeight() + 220);
                model.setX(GameSetting.instance.getWidth() / 2 - model.getWidth() / 2);
            }

            timeRebond--;
            if (live > 0) {

                model.move(0, -1);
                if (timeRebond == 0) {
                    model.setHp(10);
                    model.setAlive(true);
                    BodyManager.instance.register(this);
                    timeRebond = 300;
                }
            }

        }

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        bulletManager.draw(g);
    }

    private void shoot() {
        Utils.playSound("resources/shoot.wav", false);
        BulletController bulletController = BulletController.create(this.model.getMidX() - BulletController.WIDTH / 2,
                this.model.getY() - BulletController.HEIGHT);
        bulletManager.add(bulletController);
    }

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    // Design pattern
    // Factory
    private static PlaneController createPlane(int x, int y) {
        PlaneController planeController = new PlaneController(
                new Model(x, y, 70, 50, 10),
                new SingleView(Utils.loadImage("resources/plane3.png"))
        );
        return planeController;
    }

    @Override
    public void onContact(Body other) {
//        System.out.println("check");
        if (other instanceof EnemyBulletController) {
            this.decHp(1);
//            System.out.println("trung dan");
        }

    }

    public void destroy() {
        ExplosionController explosionController = new ExplosionController(
                new Model(this.getModel().getX(), this.getModel().getY(), 100, 100),
                new Animation(Utils.loadSheet("resources/explosion.png", 32, 32, 1, 6))
        );
        ControllerManager.explosion.add(explosionController);
        Utils.playSound("resources/planeExploding.wav", false);
    }

    public void keyPressed(Set<Integer> pressed) {
        if (keySetting != null && model.isAlive()) {
            for (Integer i: pressed
                 ) {

                int keyCode = i;
                if (keyCode == keySetting.keyUp) {
                    if (this.getModel().getY() > 30)
                        model.move(0, -SPEED);
                } else if (keyCode == keySetting.keyDown) {
                    if (this.getModel().getY() < GameSetting.instance.getHeight() - this.getModel().getHeight())
                        model.move(0, SPEED);
                } else if (keyCode == keySetting.keyLeft) {
                    if (this.getModel().getX() > 0)
                        model.move(-SPEED, 0);
                } else if (keyCode == keySetting.keyRight) {
                    if (this.getModel().getX() < GameSetting.instance.getWidth() - this.getModel().getWidth())
                        model.move(SPEED, 0);
                } else if (keyCode == keySetting.keyShoot) {
                    if (model.isAlive())
                        shoot();
                }
            }
        }
    }

    @Override
    public void planeDestroy() {

    }
}
