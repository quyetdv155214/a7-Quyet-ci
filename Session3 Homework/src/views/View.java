package views;

import model.Model;


import java.awt.*;

/**
 * Created by q on 12/7/2016.
 */
public class View {
    private Image image;
    public void draw(Graphics g, Model model){
        g.drawImage(image,model.getX(),model.getY(),model.getWidth(),model.getHeight() ,null);
    }

    public View(Image image) {
        this.image = image;
    }
}
