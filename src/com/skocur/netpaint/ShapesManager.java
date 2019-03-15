package com.skocur.netpaint;

import com.skocur.netpaint.shapes.Oval;
import com.skocur.netpaint.shapes.Rectangle;
import com.skocur.netpaint.shapes.Shape;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class ShapesManager {
    public static List<Shape> shapes;

    public static int shapeOption;

    static {
        shapes = new LinkedList<>();
        shapeOption = 0;
    }

    public static void addShapeBasedOn(MouseEvent mouseEvent) {
        if (shapeOption == 0) {
            shapes.add(new Oval(mouseEvent.getX(), mouseEvent.getY()));
        } else if (shapeOption == 1) {
            shapes.add(new Rectangle(mouseEvent.getX(), mouseEvent.getY()));
        }

        Main.server.sendData();
    }

    public static void addShapeBasedOn(Object object) {
        shapes.add((Shape) object);
    }

    /*public static void addShapeBasedOn(MouseEvent mouseEvent, PaintType paintType) {
        if (shapeOption == 0) {
            shapes.add(new Oval(mouseEvent.getX(), mouseEvent.getY()));
        } else if (shapeOption == 1) {
            shapes.add(new Rectangle(mouseEvent.getX(), mouseEvent.getY()));
        }

        networkSplitter(paintType);
    }*/

    private static void networkSplitter(PaintType paintType) {
        switch (paintType) {
            case CLIENT:
                break;
        }
    }

    private static void sendShape() {

    }

    public static void calculateShapeOption(KeyEvent e) {
        int keyCode = e.getKeyCode();

        shapeOption = keyCode == 49 || keyCode == 50 ?
                keyCode - 49 : 0;
    }
}
