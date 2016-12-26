package controllers.gif;

import controllers.BulletController;
import controllers.ExplosionController;
import controllers.GameVector;
import controllers.PlaneController;
import controllers.enemies.EnemyController;
import controllers.manangers.ControllerManager;
import controllers.manangers.EnemyControllerManager;
import models.Model;
import utils.Utils;
import views.Animation;

import java.util.Vector;

/**
 * Created by quyet on 12/26/2016.
 */
public class ExplodingBomb implements BombBehavior {
    int scope ;

    public ExplodingBomb(int scope) {
        this.scope = scope;
    }

    @Override
    public void effect() {
        EnemyControllerManager.enemyControllerManager.destroyInScope(scope);
        Utils.playSound("resources/Explosion+3.wav", false);
        ExplosionController explosionController = new ExplosionController(
                new Model(
                        PlaneController.instance.getModel().getMidX() -scope/2, PlaneController.instance.getModel().getMidY()- scope/2,
                        scope, scope),
                new Animation(Utils.loadSheet("resources/explosion.png", 32, 32, 1, 6))
        );
        ControllerManager.explosion.add(explosionController);
    }
}
