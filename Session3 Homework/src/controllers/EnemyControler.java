package controllers;

import model.Model;
import utils.Utils;
import views.View;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by q on 12/9/2016.
 */
public class EnemyControler extends Controler {
//    private EnemyBulletControler enemyBulletControler;


    private ArrayList<EnemyBulletControler> enemyBulletControlers = new ArrayList<>();

    public void addBullet() {
        enemyBulletControlers.add(EnemyBulletControler.createBullet(model.getX() + 40, model.getY() + 100));
    }

    public ArrayList<EnemyBulletControler> getListBullet() {
        return enemyBulletControlers;
    }

    public EnemyControler(Model model, View view) {
        super(model, view);
    }

    @Override
    public void run() {
        Random ran = new Random();
        int x = ran.nextInt(3);
        x -= ran.nextInt(4);
        this.model.move(x, 1);
    }

    public static EnemyControler createPlane(int x, int y, int width, int heigh, String path) {
        Random ran = new Random();
        x= ran.nextInt(500) +100;
        EnemyControler enemyControler = new EnemyControler(
                new Model(x, y, width, heigh),
                new View(Utils.loadImage(path))
        );
        return enemyControler;

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    public Model getModel() {
        return model;
    }
}
