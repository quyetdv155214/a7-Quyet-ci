package controllers;

import controllers.enemies.EnemyController;
import controllers.manangers.BodyManager;
import models.Model;
import utils.Utils;
import views.SingleView;
import views.View;

/**
 * Created by apple on 12/7/16.
 */
public class BulletController extends Controller implements Body {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 30;
    GameVector moveVector ;
    public BulletController(Model model, View view, GameVector moveVector) {
        super(model, view);
        this.moveVector = moveVector;
        BodyManager.instance.register(this);
    }

    public BulletController(Model model, View view) {
        super(model, view);
        moveVector = new GameVector(0,-5);
        BodyManager.instance.register(this);
    }

    public void run() {
        this.model.move(moveVector);
    }

    @Override
    public void onContact(Body other) {
        if(other instanceof EnemyController) {
            System.out.println("Oh yeah");
            this.model.setAlive(false);
            ((EnemyController) other).destroy();
        }
    }

    public static BulletController create(int x, int y) {
        Model bullet = new Model(x, y, WIDTH, HEIGHT);
        SingleView singleView = new SingleView(Utils.loadImage("resources/bullet.png"));
        return new BulletController(bullet, singleView);
    }
    public static BulletController create(int x, int y, GameVector gameVector) {
        Model bullet = new Model(x, y, WIDTH, HEIGHT);
        SingleView singleView = new SingleView(Utils.loadImage("resources/mine.png"));
        return new BulletController(bullet, singleView , gameVector);
    }
    }
