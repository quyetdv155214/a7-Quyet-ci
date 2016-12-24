package controllers.enemy;

import controllers.Body;
import controllers.Controller;
import controllers.PlaneController;
import controllers.manangers.BodyManager;
import models.Model;
import utils.Utils;
import views.View;

/**
 * Created by apple on 12/10/16.
 */
public class EnemyBulletController extends Controller implements Body {

//    private static final int SPEED_X = 0;
//    private static final int SPEED_Y = 5;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    ShootBehavior shootBehavior;
    EnemyController enemyController ;

    public EnemyBulletController(Model model, View view, ShootBehavior shootBehavior , EnemyController enemyController) {

        super(model, view);
        this.shootBehavior = shootBehavior;
        this.enemyController = enemyController;

        BodyManager.instance.register(this);

    }


    @Override
    public void run() {

//        model.move(SPEED_X, SPEED_Y);
        if (shootBehavior  != null)
        {
            shootBehavior.doShoot(this, 100,150);
        }


    }

    public static EnemyBulletController create(int x, int y, EnemyController enemyController, ShootType s) {
        switch (s){
            case STRAIGHT:
                return new EnemyBulletController(
                        new Model(x, y, WIDTH, HEIGHT),
                        new View(Utils.loadImage("resources/bullet-round.png")),
                        new ShootStraightBehavior(),
                        enemyController
                );
            case FOLLOW:
                return new EnemyBulletController(
                        new Model(x, y, WIDTH, HEIGHT),
                        new View(Utils.loadImage("resources/bullet-round.png")),
                        new ShootSnipBehavior(),
                        enemyController

                );
                default: return  null;
        }

    }


    @Override
    public void onContact(Body orther) {
        if(orther instanceof PlaneController)
        {
            this.getModel().decHp(1);
        }
        if(!this.getModel().isAlive()){
            BodyManager.instance.remove(this);
        }
    }
}
