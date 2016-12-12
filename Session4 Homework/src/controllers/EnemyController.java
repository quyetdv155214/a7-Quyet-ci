package controllers;


import models.Model;
import utils.Utils;
import views.View;

import java.awt.*;
import java.util.Vector;

/**
 * Created by apple on 12/10/16.
 */
public class EnemyController extends Controller {
    private  int SPEED_X = 0;
    private  int SPEED_Y = 1;

    private static final int WIDTH = 35;
    private static final int HEIGHT = 30;
    private int timeCounter;


    private Vector<EnemyBulletController> enemyBulletControllers;

    public EnemyController(Model model, View view) {
        super(model, view);

        enemyBulletControllers = new Vector<>();
        timeCounter = 0;
    }

    @Override
    public void run() {

        this.model.move(SPEED_X, SPEED_Y);
        timeCounter++;
        if (timeCounter > 30) {
            shoot();
            timeCounter = 0;
        }

        for (EnemyBulletController enemyBulletController : this.enemyBulletControllers) {
            enemyBulletController.run();
        }
        this.enemyBulletControllers.removeAll(
                EnemyBulletController.getBulletOutOfMap(enemyBulletControllers));
    }
//

    public void setMoveStraight(){
        setSPEED_X(0);
        setSPEED_Y(1);
    }
    public void setMoveSlide(){
        setSPEED_X(-1);
        setSPEED_Y(1);
    }
    public void setMoveSin(){

    }


    public void setSPEED_X(int SPEED_X) {
        this.SPEED_X = SPEED_X;
    }

    public void setSPEED_Y(int SPEED_Y) {
        this.SPEED_Y = SPEED_Y;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        for (EnemyBulletController enemyBulletController : this.enemyBulletControllers) {
            enemyBulletController.draw(g);
        }
    }

    private void shoot() {
        // Create a new bullet
        EnemyBulletController enemyBulletController = EnemyBulletController.create(
                this.model.getMidX() - EnemyBulletController.WIDTH / 2, //getMidX
                this.model.getBottom()
        );

        // Add bullet to vector
        this.enemyBulletControllers.add(enemyBulletController);
    }

    public static EnemyController create(int x, int y) {
        return new EnemyController(
                new Model(x, y, WIDTH, HEIGHT),
                new View(Utils.loadImage("resources/plane1.png"))
        );
    }
}
