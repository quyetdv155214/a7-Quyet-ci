package controllers;

import models.Model;
import utils.Utils;
import views.View;

import java.awt.*;
import java.util.Vector;

/**
 * Created by apple on 12/7/16.
 */
public class BulletController extends Controller {

    public static final int width = 20;
    public static final int height = 30;
    public BulletController(Model model, View view) {
        super(model, view);
    }

    public void run() {
        this.model.move(0, -5);
    }


    public static  BulletController createBullet(int x, int y){
        return new BulletController(new Model(x - width/2 , y - height/2, width, height), new View(Utils.loadImage("resources/bullet.png")));
    }
    public static Vector<BulletController> getBulletOutOfMap(Vector<BulletController> list){
        Vector<BulletController> removeList = new Vector<BulletController>();
        for (BulletController ec: list
             ) {
            if (ec.getModel().getX() <= 1){
                removeList.add(ec);
            }

        }
        return removeList;

    }
}
