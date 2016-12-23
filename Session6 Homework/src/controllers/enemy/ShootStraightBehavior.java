package controllers.enemy;

/**
 * Created by quyet on 12/23/2016.
 */
public class ShootStraightBehavior implements ShootBehavior {

    @Override
    public void doShoot(EnemyBulletController enemyBulletController, int x , int y) {
        enemyBulletController.getModel().move(0, 3);

    }

}
