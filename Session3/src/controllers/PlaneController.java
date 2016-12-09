package controllers;



import model.Model;
import utils.Utils;
import views.View;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by q on 12/5/2016.
 */
public class PlaneController extends Controler {

    public PlaneController(Model model, View view) {
        super(model, view);
    }

    private static final int SPEED = 5;
    private KeySetting keySetting;

    public void keyPress(KeyEvent e){
        if (keySetting!=null){
            if (e.getKeyCode()==keySetting.getKeyUp()){
                model.move(0, - SPEED);
            }else if (e.getKeyCode()==keySetting.getKeyDown()){
                model.move(0,SPEED);
            }else if(e.getKeyCode()==keySetting.getKeyLeft()){
                model.move(-SPEED,0);
            }else if (e.getKeyCode()==keySetting.getKeyRight()){
                model.move(SPEED,0);
            }
        }
    }
    public static PlaneController createPlane(int x, int y, int width, int heigh, String path) {
        PlaneController planeController = new PlaneController(
                new Model(x, y, width, heigh),
                new View(Utils.loadImage(path))
        );
        return planeController;

    }

    public KeySetting getKeySetting() {
        return keySetting;
    }

    public void setKeySetting(KeySetting keySetting) {
        this.keySetting = keySetting;
    }
    public Model getModel(){
        return model;
    }
}
