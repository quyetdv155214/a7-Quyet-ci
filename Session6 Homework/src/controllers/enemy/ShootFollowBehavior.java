package controllers.enemy;

import controllers.PlaneController;

import java.util.DoubleSummaryStatistics;

/**
 * Created by quyet on 12/21/2016.
 */
public class ShootFollowBehavior implements ShootBehavior {
    boolean check = true;

    @Override
    public void doShoot(EnemyBulletController enemyBulletController, int x  ,int y) {

        int tx = PlaneController.instance.getModel().getMidX();
        int ty = PlaneController.instance.getModel().getMidY();
        int h = Math.abs(ty -y);
        int w = Math.abs(x -tx);

//        if (ex < 500 && ex > 0 && ey < 700) {
        double deg = 0;
        if(check){
             deg= Math.atan(w/h);
            check = false;
        }
            double xSpeed = 2 * Math.sin(deg);
            double ySpeed = 2 * Math.cos(deg);
            enemyBulletController.getModel().move( (int)xSpeed, (int)ySpeed);



    }

}
