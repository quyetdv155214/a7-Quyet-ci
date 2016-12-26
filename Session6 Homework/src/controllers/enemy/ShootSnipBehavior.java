package controllers.enemy;

import controllers.PlaneController;

import java.util.DoubleSummaryStatistics;

/**
 * Created by quyet on 12/21/2016.
 */
public class ShootSnipBehavior implements ShootBehavior {
    boolean check = true;
    double deg;
    int w =0 ;
    int h= 0;
    private static final int px = PlaneController.instance.getModel().getMidX();
    private static final int py = PlaneController.instance.getModel().getMidY();

    //    int ty = PlaneController.instance.getModel().getMidY();
    @Override
    public void doShoot(EnemyBulletController enemyBulletController, int x, int y) {

        if (check) {
            h = Math.abs(py - y);
//            if (x > px)
            w =(px -x);


            check = false;
        }
//        if (ex < 500 && ex > 0 && ey < 700) {
//        deg = Math.atan(w / h);
//        check = false;

        double xSpeed = w / 50;
        double ySpeed = h / 50;

        enemyBulletController.getModel().move((int) xSpeed, (int) ySpeed);


    }

}
