package controllers.manangers;

import controllers.BulletController;
import controllers.manangers.ControllerManager;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by q on 12/16/2016.
 */
public class BulletControlerManager extends ControllerManager {

    public BulletControlerManager() {
    }

    @Override
    public void run() {
        super.run();

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    public void createBullet(int x, int y){
        BulletController bulletController = BulletController.createBullet(
                x,y);
        this.controllers.add(bulletController);
    }
}
