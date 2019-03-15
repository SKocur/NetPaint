package com.skocur.netpaint.shapes;

import java.awt.*;

public class Oval implements Shape {

    int x, y;

    public Oval(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval(x, y, 100, 100);
    }
}
