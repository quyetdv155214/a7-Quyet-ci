package controllers.manangers;

import controllers.Controller;
import controllers.GameSetting;
import controllers.GameVector;
import controllers.PlaneController;
import controllers.enemies.EnemyController;
import controllers.enemies.EnemyType;

import java.util.Random;

/**
 * Created by apple on 12/10/16.
 */
public class EnemyControllerManager extends ControllerManager {

    public static final EnemyControllerManager enemyControllerManager = new EnemyControllerManager();

    int counter;

    @Override
    public void run() {
        super.run();
        counter++;
        if (counter == 100) {
            spawn();
            counter = 0;
        }
    }

    private int enemyCount;

    private void spawn() {
        //1: Create enemy
        enemyCount++;
        EnemyController enemyController = null;
        //
        Random ran
                = new Random();
        int x = ran.nextInt(GameSetting.instance.getWidth() - 200) + 100;
        if (enemyCount % 2 == 0) {
            enemyController = EnemyController.create(x, 0, EnemyType.WHITE);
        } else {
            enemyController = EnemyController.create(x, 0, EnemyType.BROWN);
        }
        //2: Add new enemy to vector
        this.controllers.add(enemyController);

    }

    public void destroyInScope(int scope){

        for (Controller c : this.controllers) {

            GameVector dVector = c.getModel().subtract(PlaneController.instance.getModel());
            double lengh = dVector.getLength();
            if (lengh <= scope)
            {
                c.decHp(100);
            }
        }

    }

}
