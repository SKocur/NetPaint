package com.skocur.netpaint.client;

import com.skocur.netpaint.ShapesManager;

import java.awt.*;
import java.awt.event.*;

public class ClientPaintWindow extends Frame {

    public ClientPaintWindow() {
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

    }
}
