package com.skocur.netpaint.shapes;

import java.awt.*;
import java.io.Serializable;

public class Rectangle implements Shape, Serializable {

    int x, y;

    public Rectangle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(x, y, 100, 100);
    }
}