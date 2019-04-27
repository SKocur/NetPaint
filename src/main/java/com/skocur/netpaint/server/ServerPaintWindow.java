package main.java.com.skocur.netpaint.server;

import main.java.com.skocur.netpaint.FileDisplayer;
import main.java.com.skocur.netpaint.ShapesManager;
import main.java.com.skocur.netpaint.ShapesSpawner;
import main.java.com.skocur.netpaint.shapes.Shape;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

/**
 *
 */
public class ServerPaintWindow extends Frame {

    private final boolean IS_SPAWNER_ACTIVATED = false;

    public static final int DIMENSION_XY = 500;
    private double scaleW = 1;
    private double scaleH = 1;

    /**
     * Sets basic features of NetPaint window such as size or title.
     */
    public ServerPaintWindow() {
        setMinimumSize(new Dimension(DIMENSION_XY, DIMENSION_XY));
        setMaximumSize(new Dimension(700, 700));
        setPreferredSize(new Dimension(600, 600));
        setTitle("NetPaint");
        setVisible(true);

        setListeners();

        if (IS_SPAWNER_ACTIVATED) {
            new ShapesSpawner(() ->
                    repaint()
            ).start();

            new Thread(new FileDisplayer()).start();
        }
    }

    /**
     * Overridden method delivered from Frame class responsible for displaying
     * shapes from List that holds all Shapes drawn by user.
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        for (Shape shape : new LinkedList<>(ShapesManager.shapes)) {
            shape.draw(g, scaleW, scaleH);
        }
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

        if (!IS_SPAWNER_ACTIVATED) {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);

                    ShapesManager.addShapeBasedOn(mouseEvent);

                    repaint();
                }
            });
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                ShapesManager.calculateShapeOption(e);
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                Rectangle r = getBounds();
                int h = r.height;
                int w = r.width;

                scaleW = w > 0 ?  (double) w / DIMENSION_XY : 1;
                scaleH = w > 0 ? (double) h / DIMENSION_XY : 1;

                repaint();
            }
        });
    }
}
