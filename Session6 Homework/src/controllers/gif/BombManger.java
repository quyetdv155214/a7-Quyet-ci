package controllers.gif;

import controllers.manangers.ControllerManager;

import java.util.Random;

/**
 * Created by quyet on 12/24/2016.
 */
public class BombManger extends ControllerManager {
    int time_spawn = 100;

    @Override
    public void run() {
        super.run();
        spawn();
    }

    private void  spawn(){
        Random ran  = new Random();
        int x = ran.nextInt(400) +  100;
        time_spawn --;
        if ( time_spawn == 0) {
            Bomb b = Bomb.createBomb(x, 0);
            b.getModel().setHp(1);
            controllers.add(b);//
            time_spawn = 500;
        }
    }



}
