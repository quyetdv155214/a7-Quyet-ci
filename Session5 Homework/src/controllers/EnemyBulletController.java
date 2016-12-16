package controllers;

import controllers.manangers.BodyManager;
import models.Model;
import utils.Utils;
import views.View;

import java.util.Vector;

/**
 * Created by apple on 12/10/16.
 */
public class EnemyBulletController extends Controller implements Body {

    private static final int SPEED_X = 0;
    private static final int SPEED_Y = 5;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    public EnemyBulletController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);

    }

    @Override
    public void run() {
        model.move(SPEED_X, SPEED_Y);
        if(!this.getModel().isAlive()){
            BodyManager.instance.remove(this);
        }

    }

    public static EnemyBulletController create(int x, int y) {
        return new EnemyBulletController(
                new Model(x, y, WIDTH, HEIGHT),
                new View(Utils.loadImage("resources/bullet-round.png"))
        );
    }


    @Override
    public void onContact(Body orther) {
        if(orther instanceof PlaneController)
        {
            this.getModel().decHp(1);
        }
    }
}
