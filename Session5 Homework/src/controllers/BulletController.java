package controllers;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import controllers.manangers.BodyManager;
import models.Model;
import utils.Utils;
import views.View;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by apple on 12/7/16.
 */
public class BulletController extends Controller implements Body{

    public static final int width = 10;
    public static final int height = 30;
    public BulletController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }

    public void run() {
        //
        this.model.move(0, -5);
        if(!this.getModel().isAlive()){
            BodyManager.instance.remove(this);
        }
    }


    public static  BulletController createBullet(int x, int y){

        BulletController bulletController=
                new BulletController(
                        new Model(x - width/2 , y - height/2, width, height),
                        new View(Utils.loadImage("resources/bullet.png")));
        bulletController.getModel().setHp(1);
        return  bulletController;
    }


    @Override
    public void onContact(Body orther) {
        if (orther instanceof EnemyController){
          //
            this.getModel().decHp(1);

        }
    }
}
