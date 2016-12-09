package controllers;

import model.Model;
import utils.Utils;
import views.View;

import java.awt.*;

/**
 * Created by q on 12/9/2016.
 */
public class EnemyBulletControler extends Controler {

    public EnemyBulletControler(Model model, View view) {
        super(model, view);
    }

    public static  EnemyBulletControler createBullet(int x, int y){
        return  new EnemyBulletControler(
                new Model(x, y, 20,20),
                new View(Utils.loadImage("resources/bullet-round.png")) );
    }
    @Override
    public void run() {
        this.model.move(0, 7);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
