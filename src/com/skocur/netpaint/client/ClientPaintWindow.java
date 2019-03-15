package com.skocur.netpaint.client;

import com.skocur.netpaint.ShapesManager;

import java.awt.*;
import java.awt.event.*;

public class ClientPaintWindow extends Frame {

    /**
     * Sets basic features of NetPaint window such as size or title.
     */
    public ClientPaintWindow() {
        setSize(700, 700);
        setTitle("NetPaint");
        setVisible(true);

        setListeners();
    }

    /**
     * Overridden method delivered from Frame class responsible for displaying
     * shapes from List that holds all Shapes drawn by user.
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        ShapesManager.shapes.forEach((shape) -> shape.draw(g));
    }

    /**
     * Method that set all necessary listeners responsible for processing
     * various tasks.
     */
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
