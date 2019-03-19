package main.java.com.skocur.netpaint.shapes;

import java.awt.*;
import java.io.Serializable;

public class Oval implements Shape, Serializable {

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
