package controllers.manangers;

import controllers.BaseController;
import controllers.Controller;
import controllers.GameSetting;
import controllers.enemies.EnemyController;

import java.awt.*;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by apple on 12/10/16.
 */
public class ControllerManager implements BaseController {
    protected Vector<Controller> controllers;

    public ControllerManager() {//
        controllers = new Vector<>();
    }

    public static final ControllerManager enemyBullet = new ControllerManager();
        public static final ControllerManager enemyController = new ControllerManager();
    public static final ControllerManager bombBullet = new ControllerManager();
    public static final ControllerManager explosion = new ControllerManager();
    public static final ControllerManager gif = new ControllerManager();


    public void draw(Graphics g) {
        for (Controller controller : this.controllers) {
            controller.draw(g);
        }
    }

    public void run() {
        for (Controller controller : this.controllers) {
            controller.run();
        }

        Iterator<Controller> iterator = this.controllers.iterator();
        while (iterator.hasNext()) {
            Controller controller = iterator.next();
            if (!controller.getModel().isAlive() || !GameSetting.instance.isInScreen(controller)) {

                iterator.remove();
            }
        }
    }

    public Vector<EnemyController> getEnemy() {
        Vector<EnemyController> enemyControllers = new Vector<>();


        for (Controller e : controllers
                ) {
            if (e instanceof EnemyController) {
                enemyControllers.add((EnemyController) e);
                System.out.println("get===");
            }
        }
        return enemyControllers;
    }



    public void add(Controller controller) {
        this.controllers.add(controller);
    }

    public void remove(Controller controller) {
        this.controllers.remove(controller);
    }
}
