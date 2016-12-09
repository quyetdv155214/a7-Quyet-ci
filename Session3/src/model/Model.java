package model;

/**
 * Created by q on 12/7/2016.
 */
public class Model {
    private int x;
    private int y;
    private int width;
    private int height;

    public Model(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Model(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    //get set
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
