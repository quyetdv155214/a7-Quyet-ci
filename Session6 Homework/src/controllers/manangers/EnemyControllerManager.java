package controllers.manangers;

import controllers.enemy.EnemyController;
import controllers.enemy.EnemyType;

import java.util.Random;

/**
 * Created by apple on 12/10/16.
 */
public class EnemyControllerManager extends ControllerManager {
    int time_spawn = 100;
    int temp = 0;

    @Override
    public void run() {
        super.run();
        temp++;
        if (temp == time_spawn) {
            spawn();
            temp = 0;
        }
    }

    int i = 0;

    private void spawn() {
        //1: Create
        Random ran = new Random();
        // random x;
//        int type = ran.nextInt(2);


        int x = ran.nextInt(500) + 100;
        EnemyController enemyController = null;


        if (i % 2 == 0)

            enemyController = EnemyController.create(x, 0, EnemyType.BROWN);
        else
            enemyController = EnemyController.create(x, 0, EnemyType.GREEN);
        i++;
// set hp
        enemyController.getModel().setHp(3);

        //2: Add new enemy to vector
        this.controllers.add(enemyController);

    }

}
