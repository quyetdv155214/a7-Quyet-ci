package controllers;
import model.Model;
import views.View;
import java.awt.*;

/**
 * Created by q on 12/7/2016.
 */
public class Controler {
    protected Model model;
    protected View view;


    public Controler(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void run(){

    }
    public void draw(Graphics g){
        view.draw(g,model);
    }


}
