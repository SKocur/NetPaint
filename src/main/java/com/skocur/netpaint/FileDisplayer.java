package main.java.com.skocur.netpaint;

import main.java.com.skocur.netpaint.server.ServerPaintWindow;
import main.java.com.skocur.netpaint.shapes.Line;
import main.java.com.skocur.netpaint.shapes.Oval;
import main.java.com.skocur.netpaint.shapes.Rectangle;
import main.java.com.skocur.netpaint.shapes.Shape;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileDisplayer extends Frame implements Runnable {

    private List<Shape> shapes = new LinkedList<>();

    private double scaleW = 1;
    private double scaleH = 1;

    public FileDisplayer() {
        setMinimumSize(new Dimension(500, 500));
        setMaximumSize(new Dimension(700, 700));
        setPreferredSize(new Dimension(600, 600));
        setTitle("NetPaint");
        setVisible(true);

        setListeners();
    }

    private void setListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosed(windowEvent);

                System.exit(0);
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                java.awt.Rectangle r = getBounds();
                int h = r.height;
                int w = r.width;

                scaleW = w > 0 ?  (double) w / ServerPaintWindow.DIMENSION_XY : 1;
                scaleH = w > 0 ? (double) h / ServerPaintWindow.DIMENSION_XY : 1;

                repaint();
            }
        });
    }

    @Override
    public void run() {
        File file = new File("shapes.data");

        if(file.exists() && file.canRead()) {
            long fileLength = file.length();

            try {
                readFile(file, 0);

                while (true) {
                    if (fileLength < file.length()) {
                        readFile(file, fileLength);
                        fileLength = file.length();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readFile(File file,long fileLength) throws IOException {
        String line;

        BufferedReader in = new BufferedReader(new java.io.FileReader(file));
        in.skip(fileLength);

        while ((line = in.readLine()) != null) {
            String[] data = line.split(" ");

            Shape shape;
            if (data[0].equals("OVAL")) {
                shape = new Oval(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            } else if (data[0].equals("RECTANGLE")) {
                shape = new Rectangle(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            } else {
                shape = new Line(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            }

            shape.setEndX(Integer.parseInt(data[3]));
            shape.setEndY(Integer.parseInt(data[4]));
            shape.setColor(Integer.parseInt(data[5]));

            shapes.add(shape);

            repaint();
        }

        in.close();
    }

    @Override
    public void paint(Graphics g) {
        for (Shape shape : new LinkedList<>(shapes)) {
            shape.draw(g, scaleW, scaleH);
        }
    }
}
