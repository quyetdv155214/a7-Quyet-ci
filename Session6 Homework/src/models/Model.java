package models;

import java.awt.*;

/**
 * Created by apple on 12/7/16.
 */
public class Model {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isAlive= true;
    private int hp;

    public Model(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getMidX() {
        return this.x + this.width / 2;
    }

    public int getMidY() {
        return this.y + this.height / 2;
    }

    public int getBottom() {
        return this.y + this.height;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public boolean isAlive() {

        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    // decrease hp
    public void decHp(int num){
        this.hp = this.hp - num;
        checkDead();
    }
    public void checkDead(){
        if (hp <= 0){
            setAlive(false);
        }
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTopY() {
        return this.y -height;
    }
    public Rectangle getRect(){
        return  new Rectangle(x,y,width,height);
    }
    public boolean intersects(Model orther){
        Rectangle rect1 = this.getRect();
        Rectangle rect2 = orther.getRect();

        return rect1.intersects(rect2);

    }
}
