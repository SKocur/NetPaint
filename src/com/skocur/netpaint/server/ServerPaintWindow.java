package com.skocur.netpaint.server;

import com.skocur.netpaint.ShapesManager;
import com.skocur.netpaint.shapes.Oval;
import com.skocur.netpaint.shapes.Rectangle;
import com.skocur.netpaint.shapes.Shape;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

public class ServerPaintWindow extends Frame {

    public ServerPaintWindow() {
        setSize(700, 700);
        setTitle("NetPaint");
        setVisible(true);

        setListeners();
    }

    @Override
    public void paint(Graphics g) {
        ShapesManager.shapes.forEach((shape) -> shape.draw(g));
    }

    private void setListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosed(windowEvent);

                System.exit(0);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                ShapesManager.addShapeBasedOn(mouseEvent);

                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                ShapesManager.calculateShapeOption(e);
            }
        });
    }
}
