package controllers.manangers;

import controllers.PlaneController;

import java.awt.*;

/**
 * Created by q on 12/16/2016.
 */
public class PlaneControlerManager extends ControllerManager {

    public PlaneControlerManager() {

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public void run() {
        super.run();
    }
    public void add(PlaneController planeController){
        this.controllers.add(planeController);
    }


}
