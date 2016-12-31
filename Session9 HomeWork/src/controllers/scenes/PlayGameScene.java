package controllers.scenes;

import controllers.BaseController;
import controllers.GameSetting;
import controllers.PlaneController;
import controllers.manangers.BodyManager;
import controllers.manangers.BombControllerManager;
import controllers.manangers.ControllerManager;
import controllers.manangers.EnemyControllerManager;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import static utils.Utils.loadImage;

/**
 * Created by quyet on 12/31/2016.
 */
public class PlayGameScene extends GameScene  {
    Image background;
    Vector<BaseController> controllers;
    private final Set<Integer> pressed = new HashSet<Integer>();

    public PlayGameScene() {
        controllers = new Vector<>();
        controllers.add(ControllerManager.explosion);
//        controllers.add(new EnemyControllerManager());
        controllers.add(PlaneController.instance);
        controllers.add(BodyManager.instance);
        controllers.add(ControllerManager.enemyBullet);
        controllers.add(ControllerManager.bombBullet);
        controllers.add(new BombControllerManager());
        controllers.add(EnemyControllerManager.enemyControllerManager);
        background = loadImage("resources/background.png");

    }


    @Override
    public void update(Graphics graphics) {

        // Prepare backbuffer
        graphics.drawImage(background, 0, 0, GameSetting.instance.getWidth(), GameSetting.instance.getHeight(), null);
        if (PlaneController.instance.getLive() > 0) {
            Font font = new Font("Bauhaus 93",Font.BOLD,20);
            graphics.setFont(font);
            graphics.drawString("Hp : " + PlaneController.instance.getModel().getHp(), 100, 100);
            graphics.drawString("Live : " + PlaneController.instance.getLive(), 100, 120);
            for (BaseController baseController : this.controllers) {
                baseController.draw(graphics);
            }
        }else{
            graphics.drawImage(Utils.loadImage("resources/gameOver.png"), 0, 0, GameSetting.instance.getWidth(),
                    GameSetting.instance.getHeight(), null);

        }
        // Update window
    }

    @Override
    public void run() {

        for(BaseController baseController: controllers) {
            baseController.run();
        }
        if (PlaneController.instance.getLive() == 0){
            this.sceneListener.replaceScene(new GameOverScene(), false);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());
        PlaneController.instance.keyPressed(pressed);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());

    }


}
