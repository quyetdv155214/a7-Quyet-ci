package controllers.scenes;

import controllers.GameSetting;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by quyet on 12/31/2016.
 */
public class MenuScene extends GameScene {
    @Override
    public void update(Graphics graphics) {
        graphics.drawImage(Utils.loadImage("resources/1945-logo.png"), 0,0, GameSetting.instance.getWidth()
        , GameSetting.instance.getHeight(),null);
    }

    @Override
    public void run() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.sceneListener.replaceScene(
                new PlayGameScene(),
                true

        );
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
