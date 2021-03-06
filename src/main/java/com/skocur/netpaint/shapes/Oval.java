package main.java.com.skocur.netpaint.shapes;

import java.awt.*;
import java.io.Serializable;

public class Oval implements Shape, Serializable {

    private int x, y, endX, endY, color;

    public Oval(int x, int y) {
        this.x = x;
        this.y = y;
        color = 31230;
    }

    @Override
    public void draw(Graphics g, double scaleX, double scaleY) {
        g.setColor(new Color(color));
        g.drawOval((int) (x * scaleX), (int) (y * scaleY), (int) (endX * scaleX), (int) (endY * scaleY));
    }

    @Override
    public void setEndX(int x) {
        this.endX = x;
    }

    @Override
    public void setEndY(int y) {
        this.endY = y;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "OVAL " + x
                + " " + y
                + " " + endX
                + " " + endY
                + " " + color;
    }
}
