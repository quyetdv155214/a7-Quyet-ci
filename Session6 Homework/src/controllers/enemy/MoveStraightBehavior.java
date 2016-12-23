package controllers.enemy;

/**
 * Created by quyet on 12/23/2016.
 */
public class MoveStraightBehavior implements MoveBehavior {
    @Override
    public void doMove(EnemyController enemyController) {
        enemyController.getModel().move( 0, 1);

    }
}
