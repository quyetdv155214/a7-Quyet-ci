package controllers;


import model.Model;
import utils.Utils;
import views.View;

/**
 * Created by q on 12/5/2016.
 */
public class BulletController extends Controler {


    public BulletController(Model model, View view) {
        super(model, view);
    }

    public static BulletController createBullet(int x, int y, int width, int heigh, String path) {
        BulletController bulletController = new BulletController(
                new Model(x, y, width, heigh),
                new View(Utils.loadImage(path))
        );
        return bulletController;

    }

    public void run() {
        this.model.move(0, -5);
    }

    public Model model() {
        return model;
    }

}
