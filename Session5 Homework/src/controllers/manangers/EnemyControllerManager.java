package controllers.manangers;

import java.awt.*;

import controllers.Controller;
import controllers.EnemyController;

import java.util.Random;
import java.util.Vector;

/**
 * Created by apple on 12/10/16.
 */
public class EnemyControllerManager extends ControllerManager {
    int time_spawn = 50;
    int temp = 0;
    @Override
    public void run() {
        super.run();
        temp++;
        if(temp == time_spawn) {
            spawn();
            temp=0;
        }
    }


    private void spawn() {
        //1: Create
        Random ran = new Random();
        // random x;
        int type = ran.nextInt(2);



        int x = ran.nextInt(500) + 100;

        EnemyController enemyController = EnemyController.create(x, 0);
        enemyController.getModel().setHp(3);
        switch (type){
            case 0 : enemyController.setMoveStraight();
            break;
            case 1: enemyController.setMoveSlide();
            break;

        }

        //2: Add new enemy to vector
        this.controllers.add(enemyController);

    }

}
