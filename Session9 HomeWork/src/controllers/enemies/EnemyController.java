package controllers.enemies;

import controllers.*;
import controllers.manangers.BodyManager;
import controllers.manangers.ControllerManager;
import controllers.notifications.EventSubcriber;
import controllers.notifications.EventType;
import controllers.notifications.NotificationCenter;
import models.Model;
import utils.Utils;
import views.Animation;
import views.SingleView;
import views.View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by apple on 12/10/16.
 */

public class EnemyController extends Controller implements Body, EventSubcriber {

    private static final int WIDTH = 35;
    private static final int HEIGHT = 30;
    private int timeCounter;

//    private Vector<EnemyBulletController> enemyBulletControllers;

    private MoveBehavior moveBehavior;
    private ShootBehavior shootBehavior;

//    public void add(EnemyBulletController enemyBulletController) {
//        this.enemyBulletControllers.add(enemyBulletController);
//    }

    public EnemyController(Model model, View view, MoveBehavior moveBehavior, ShootBehavior shootBehavior) {
        super(model, view);

        timeCounter = 0;
        this.moveBehavior = moveBehavior;
        BodyManager.instance.register(this);

        this.shootBehavior = shootBehavior;
        NotificationCenter.instance.register(this);
    }

    @Override
    public void run() {
        //Move

        if (moveBehavior != null) {
            moveBehavior.doMove(this);
        }

        timeCounter++;
        if (timeCounter > 30) {
            shoot();
            timeCounter = 0;
        }
    }

    private void shoot() {

        if (shootBehavior != null) {

            shootBehavior.doShot(this);
        }
    }

    public static EnemyController create(int x, int y, EnemyType type) {
        switch (type) {
            case BROWN:
                return new EnemyController(
                        new Model(x, y, WIDTH, HEIGHT),
                        new SingleView(Utils.loadImage("resources/plane1.png")),
                        new MoveStraightDownBehavior(),
                        new ShootStraightBehavior()
                );
            case GREEN:
                return new EnemyController(
                        new Model(x, y, WIDTH, HEIGHT),
                        new SingleView(Utils.loadImage("resources/enemy-green-3.png")),
                        new MoveZigZagBehavior(),
                        new ShootOnTargetBehavior()
                );
            case WHITE:
                Vector<BufferedImage> images = new Vector<>();
                images.add(Utils.loadImage("resources/enemy_plane_white_1.png"));
                images.add(Utils.loadImage("resources/enemy_plane_white_2.png"));
                images.add(Utils.loadImage("resources/enemy_plane_white_3.png"));


                return new EnemyController(
                        new Model(x, y, WIDTH, HEIGHT),
                        new Animation(images),
                        new MoveZigZagBehavior(),
                        new ShootOnTargetBehavior()
                );

        }
        return null;
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof BulletController) {
//            System.out.println("Huhu");
//            this.model.setAlive(false);
            this.decHp(1);
        }

    }



    public void destroyInScope(int scope) {

        GameVector dVector = this.getModel().subtract(PlaneController.instance.getModel());
        double lengh = dVector.getLength();
        System.out.println("lengh" + lengh);
        System.out.println("scope " + scope);
        if (lengh <= scope) {
            this.model.destroy();

        }


    }

    @Override
    public boolean onEvent(EventType eventType, Object o, int scope) {
        switch (eventType) {
            case BOMB_EXPLOSION:
                destroyInScope(scope);
                return false;
        }
        return true;
    }
}
