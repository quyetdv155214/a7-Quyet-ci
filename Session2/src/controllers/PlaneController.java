package controllers;

import model.PlaneModel;
import views.PlaneView;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by q on 12/5/2016.
 */
public class PlaneController {
    private PlaneModel planeModel;
    private PlaneView planeView;
    private KeySetting keySetting;

    public PlaneModel getPlaneModel() {
        return planeModel;
    }

    public void setPlaneModel(PlaneModel planeModel) {
        this.planeModel = planeModel;
    }

    public PlaneView getPlaneView() {
        return planeView;
    }

    public void setPlaneView(PlaneView planeView) {
        this.planeView = planeView;
    }

    public KeySetting getKeySetting() {
        return keySetting;
    }

    public void setKeySetting(KeySetting keySetting) {
        this.keySetting = keySetting;
    }

    public PlaneController(PlaneModel planeModel, PlaneView planeView) {
        this.planeModel = planeModel;
        this.planeView = planeView;
    }
    public void keyPress(KeyEvent e){
        if (keySetting!=null){
            if (e.getKeyCode()==keySetting.getKeyUp()){
                planeModel.move(0,-5);
            }else if (e.getKeyCode()==keySetting.getKeyDown()){
                planeModel.move(0,5);
            }else if(e.getKeyCode()==keySetting.getKeyLeft()){
                planeModel.move(-5,0);
            }else if (e.getKeyCode()==keySetting.getKeyRight()){
                planeModel.move(5,0);
            }
        }
    }
    public void draw(Graphics g){
        planeView.draw(g,planeModel);
    }
    public void run(){

    }
}
