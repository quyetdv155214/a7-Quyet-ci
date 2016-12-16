package controllers.manangers;

import controllers.Controller;

import java.awt.*;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by apple on 12/10/16.
 */
public class ControllerManager {
    protected Vector<Controller> controllers;

    public ControllerManager() {
        controllers = new Vector<>();
    }

    public void draw(Graphics g) {
        try {
            for(Controller controller : this.controllers) {
                controller.draw(g);
            }

        }catch (Exception e){

        }
    }

    public void run() {
        for(Controller controller: this.controllers) {
            controller.run();
        }
        Iterator<Controller> iterator = this.controllers.iterator();
        // remove

        while(iterator.hasNext()){
            Controller controller = iterator.next();
            if(!controller.getModel().isAlive() || controller.getModel().getX() <=0 || controller.getModel().getY() > 600){
                iterator.remove();
            }

        }
    }



}
