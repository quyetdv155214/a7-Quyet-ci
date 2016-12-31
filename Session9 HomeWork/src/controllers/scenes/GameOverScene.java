package controllers.scenes;

import controllers.GameSetting;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by quyet on 12/31/2016.
 */
public class GameOverScene extends GameScene
{
    @Override
    public void update(Graphics graphics) {
        graphics.drawImage(
                Utils.loadImage("resources/gameOver.png"),
                0, 0,
                GameSetting.instance.getWidth(),
                GameSetting.instance.getHeight(),
                null
        );
    }

    @Override
    public void run() {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
