package main.java.com.skocur.netpaint;

import main.java.com.skocur.netpaint.server.ServerPaintWindow;
import main.java.com.skocur.netpaint.shapes.Line;
import main.java.com.skocur.netpaint.shapes.Oval;
import main.java.com.skocur.netpaint.shapes.Rectangle;
import main.java.com.skocur.netpaint.shapes.Shape;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ShapesSpawner extends Thread {

    private final int SLEEP_TIME = 1000;

    private WindowRepainter windowRepainter;

    public ShapesSpawner(WindowRepainter windowRepainter) {
        this.windowRepainter = windowRepainter;

        File file = new File("shapes.data");
        file.delete();
    }

    @Override
    public void run() {
        while (true) {
            int randomOption = (int)(Math.random() * 3);

            System.out.println("Random option: " + randomOption);

            int randomColor = (int)(Math.random() * 25000);

            int randomX = (int)(Math.random() * (ServerPaintWindow.DIMENSION_XY / 2) + 50);
            int randomY = (int)(Math.random() * (ServerPaintWindow.DIMENSION_XY / 2) + 50);
            int randomEndX = (int)(Math.random() * (ServerPaintWindow.DIMENSION_XY / 10) + 50);
            int randomEndY = (int)(Math.random() * (ServerPaintWindow.DIMENSION_XY / 10) + 50);

            Shape shape;

            if (randomOption == 0) {
                shape = new Oval(randomX, randomY);
            } else if (randomOption == 1) {
                shape = new Rectangle(randomX, randomY);
            } else {
                shape = new Line(randomX, randomY);
            }

            try {
                shape.setEndX(randomEndX);
                shape.setEndY(randomEndY);
                shape.setColor(randomColor);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            ShapesManager.shapes.add(shape);
            windowRepainter.repaintWindow();

            saveToFile(shape);

            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveToFile(Shape shape) {
        try {
            FileWriter fstream = new FileWriter("shapes.data", true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(shape.toString());
            out.write("\n");

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FunctionalInterface
    public interface WindowRepainter {
        void repaintWindow();
    }
}
