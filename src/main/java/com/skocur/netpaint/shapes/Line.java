package main.java.com.skocur.netpaint.shapes;

import java.awt.*;

public class Line implements Shape {

    private int x, y, endX, endY, color;

    public Line(int x, int y) {
        this.x = x;
        this.y = y;
        color = 31230;
    }

    @Override
    public void draw(Graphics g, double scaleX, double scaleY) {
        g.setColor(new Color(color));
        g.drawLine((int)(x * scaleX), (int)(y * scaleY), (int)(endX * scaleX), (int)(endY * scaleY));
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
        return "LINE " + x
                + " " + y
                + " " + endX
                + " " + endY
                + " " + color;
    }
}
