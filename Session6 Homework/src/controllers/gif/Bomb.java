package controllers.gif;

import controllers.Body;
import controllers.Controller;
import controllers.PlaneController;
import controllers.enemy.EnemyController;
import controllers.manangers.BodyManager;
import models.Model;
import utils.Utils;
import views.View;

import java.awt.*;

/**
 * Created by quyet on 12/24/2016.
 */
public class Bomb extends Controller implements Body {
    private static final int scope = 500;
    private static final int WIDTH = 35;
    private static final int HEIGHT = 30;
    public Bomb(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);



    }

    @Override
    public void run() {
        super.run();
        this.getModel().move(0,1);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(this.getView().image, this.getModel().getX(), this.getModel().getY(), WIDTH, HEIGHT, null);
    }

    public static Bomb createBomb(int x , int y){
        return  new Bomb(
                new Model(x, y, scope, scope),
                new View(Utils.loadImage("resources/bomb.png"))
        );
    }
    boolean exploding= false;
    @Override
    public void onContact(Body orther) {
        if (orther instanceof PlaneController){
            this.getModel().decHp(1);
            exploding = true;
        }
        if (exploding == true && orther instanceof EnemyController){
//            orther.getModel().decHp(100);
        }
    }
}
