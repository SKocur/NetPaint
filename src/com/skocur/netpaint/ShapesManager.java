package com.skocur.netpaint;

import com.skocur.netpaint.shapes.Oval;
import com.skocur.netpaint.shapes.Rectangle;
import com.skocur.netpaint.shapes.Shape;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that holds static methods responsible for operating on collection
 * of Shape implementations.
 */
public class ShapesManager {

    public static List<Shape> shapes;

    public static int shapeOption;

    static {
        shapes = new LinkedList<>();
        shapeOption = 0;
    }

    /**
     * Adds Shape implementation to List based on shapeOption variable
     * that is calculated by calculateShapeOption() method.
     *
     * After adding Shape it invokes Server method that is responsible for
     * sending data to Client app.
     *
     * @param mouseEvent
     */
    public static void addShapeBasedOn(MouseEvent mouseEvent) {
        if (shapeOption == 0) {
            shapes.add(new Oval(mouseEvent.getX(), mouseEvent.getY()));
        } else if (shapeOption == 1) {
            shapes.add(new Rectangle(mouseEvent.getX(), mouseEvent.getY()));
        }

        Main.server.sendData();
    }

    /**
     * Method used by Client which is used to add Object that implements Shape
     * interface (@see Shape).
     *
     * @param object Every object which implements Shape
     */
    public static void addShapeBasedOn(Object object) {
        shapes.add((Shape) object);
    }

    /**
     * Gets and convert value of clicked button to Integer that holds
     * option number, which describes which shape should be drawn.
     *
     * Options:
     * 0 - @see Oval
     * 1 - @see Rectangle
     *
     * It gets ASCII value and substract 49 from it.
     *
     * @param e KeyEvent
     */
    public static void calculateShapeOption(KeyEvent e) {
        int keyCode = e.getKeyCode();

        shapeOption = keyCode == 49 || keyCode == 50 ?
                keyCode - 49 : 0;
    }
}
