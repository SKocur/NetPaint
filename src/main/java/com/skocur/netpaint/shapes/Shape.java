package main.java.com.skocur.netpaint.shapes;

import java.awt.*;

/**
 * Item drawn by User via physical mouse. For now it only can be drawn by two
 * implementations:
 * -Rectangle (@see Rectangle)
 * -Oval (@see Oval)
 *
 * The Shape is also used during communication between Client and Server.
 */
public interface Shape {

    /**
     * Draws shape using Graphics object.
     *
     * @param g
     */
    void draw(Graphics g, double scaleX, double scaleY);

    void setEndX(int x);
    void setEndY(int y);

    void setColor(int color);

    int getX();
    int getY();
}
