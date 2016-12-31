package controllers.manangers;

import controllers.GameSetting;
import controllers.enemies.EnemyController;
import controllers.enemies.EnemyType;
import controllers.gif.BombControler;
import controllers.gif.BombType;

import java.util.Random;

/**
 * Created by quyet on 12/25/2016.
 */
public class BombControllerManager extends ControllerManager {
    int counter;

    @Override
    public void run() {
        super.run();
        counter++;
        if (counter == 300) {
            spawn();
            counter = 0;
        }
    }


    private void spawn() {
        //1: Create enemy
        BombControler bombControler = null;

        Random ran = new
                Random();
        int x = ran.nextInt(GameSetting.instance.getWidth() - 200) + 100;
        int bombType = ran.nextInt(2);
        switch (bombType){
            case 0:
                bombControler = BombControler.create(x, 0, BombType.SMALL);
                break;
            case 1:
                bombControler = BombControler.create(x, 0, BombType.BIG);
                break;
        }
        //2: Add new enemy to vector
        this.controllers.add(bombControler);
    }
}
