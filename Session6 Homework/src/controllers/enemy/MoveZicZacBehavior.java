package controllers.enemy;

/**
 * Created by quyet on 12/23/2016.
 */
public class MoveZicZacBehavior implements MoveBehavior {
    int count = 0;

    @Override
    public void doMove(EnemyController enemyController) {
        if (count < 100) {
            enemyController.getModel().move(1, 1);
        }
        else if(count > 100 && count < 200){
            enemyController.getModel().move(-1, 1);
        }
        else if (count > 200){
            count = 0;
        }
        count ++;


    }
}
