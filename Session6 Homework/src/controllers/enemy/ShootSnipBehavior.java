package controllers.enemy;

import controllers.PlaneController;

import java.util.DoubleSummaryStatistics;

/**
 * Created by quyet on 12/21/2016.
 */
public class ShootSnipBehavior implements ShootBehavior {
    boolean check = true;
    double deg;

    private static final int px = PlaneController.instance.getModel().getMidX();
    private static final int py = PlaneController.instance.getModel().getMidY();

    //    int ty = PlaneController.instance.getModel().getMidY();
    @Override
    public void doShoot(EnemyBulletController enemyBulletController, int x, int y) {

        int h = Math.abs(py - y);
        int w = Math.abs(x - px);

//        if (ex < 500 && ex > 0 && ey < 700) {
        deg = Math.atan(w / h);
        check = false;

        double xSpeed = 2 * Math.sin(deg);
        double ySpeed = 2 * Math.cos(deg);
        enemyBulletController.getModel().move((int) xSpeed, (int) ySpeed);


    }

}
