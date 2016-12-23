package controllers.enemy;


import controllers.Body;
import controllers.BulletController;
import controllers.Controller;
import controllers.manangers.BodyManager;
import models.Model;
import utils.Utils;
import views.View;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by apple on 12/10/16.
 */
public class EnemyController extends Controller implements Body {
//    private  int SPEED_X = 0;
//    private  int SPEED_Y = 1;

    private static final int WIDTH = 35;
    private static final int HEIGHT = 30;
    private int timeCounter;
    MoveBehavior moveBehavior;


    private Vector<EnemyBulletController> enemyBulletControllers;

    public EnemyController(Model model, View view, MoveBehavior moveBehavior) {
        super(model, view);
        this.moveBehavior = moveBehavior;
        BodyManager.instance.register(this);


        enemyBulletControllers = new Vector<>();
        timeCounter = 0;
    }

    @Override
    public void run() {

//        this.model.move(SPEED_X, SPEED_Y);
        if (moveBehavior != null) {
            moveBehavior.doMove(this);
        }
        timeCounter++;
        if (timeCounter > 60) {
            shoot();
            timeCounter = 0;
        }

        for (EnemyBulletController enemyBulletController : this.enemyBulletControllers) {
            enemyBulletController.run();
//            System.out.println("run");
        }
        removeBullet();
        if (!this.getModel().isAlive()) {
            BodyManager.instance.remove(this);
        }
    }
//


//    public void setMoveSin(){


    public void removeBullet() {
        Iterator<EnemyBulletController> iterator = enemyBulletControllers.iterator();
        while (iterator.hasNext()) {
            EnemyBulletController enemyBulletController = iterator.next();
            if (!enemyBulletController.getModel().isAlive() || enemyBulletController.getModel().getY() > 800) {
                iterator.remove();
            }
        }
    }


//    public void setSPEED_X(int SPEED_X) {
//        this.SPEED_X = SPEED_X;
//    }
//
//    public void setSPEED_Y(int SPEED_Y) {
//        this.SPEED_Y = SPEED_Y;
//    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        for (EnemyBulletController enemyBulletController : this.enemyBulletControllers) {
            enemyBulletController.draw(g);
        }
    }
    int i = 0;
    private void shoot() {
        // Create a new bullet
        EnemyBulletController enemyBulletController = null;


        enemyBulletController= EnemyBulletController.create(
                this.model.getMidX() - EnemyBulletController.WIDTH / 2, //getMidX
                this.model.getBottom(),
                this,
                ShootType.FOLLOW

        );
        enemyBulletController.getModel().setHp(1);


        // Add bullet to vector
        this.enemyBulletControllers.add(enemyBulletController);
    }

    static int e;

    public static EnemyController create(int x, int y, EnemyType enemyType) {


        switch (enemyType) {
            case BROWN:
                return new EnemyController(
                        new Model(x, y, WIDTH, HEIGHT),
                        new View(Utils.loadImage("resources/enemy_plane_yellow_3.png")),
                        new MoveZicZacBehavior()
                );
            case GREEN:
                return new EnemyController(
                        new Model(x, y, WIDTH, HEIGHT),
                        new View(Utils.loadImage("resources/plane1.png")),
                        new MoveStraightBehavior()
                );
            default:
                return null;
        }

//        return  null

    }

    @Override
    public void onContact(Body orther) {
        if (orther instanceof BulletController) {

            this.getModel().decHp(1);


        }
    }
}
