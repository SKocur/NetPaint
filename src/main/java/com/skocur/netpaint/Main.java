package main.java.com.skocur.netpaint;

import main.java.com.skocur.netpaint.client.Client;
import main.java.com.skocur.netpaint.client.ClientPaintWindow;
import main.java.com.skocur.netpaint.server.Server;
import main.java.com.skocur.netpaint.server.ServerPaintWindow;

/**
 * Class that is entry point for NetPaint. NetPaint behaves
 * differently depending on PaintType option (@see PaintType).
 */
public class Main {

    public static Server server = new Server();
    static PaintType paintType = PaintType.SERVER;

    private static ClientPaintWindow clientPaintWindow;

    /**
     * Method that runs different parts of NetPaint depending on PaintType
     * option (@see PaintType).
     * It displays window and sets up class responsible for background
     * communication between Client and Server.
     *
     * @param args
     */
    public static void main(String[] args) {
        switch (paintType) {
            case CLIENT:
                clientPaintWindow = new ClientPaintWindow();

                new Thread(new Client(socketDataListener)).start();
                break;

            case SERVER:
                new Thread(server).start();
                new ServerPaintWindow();
                break;
        }
    }

    /**
     * Anonymous class responsible for invoking repaint() method from
     * class that extends Frame.
     */
    private static Client.SocketDataListener socketDataListener = () -> {
            if (clientPaintWindow != null && paintType == PaintType.CLIENT) {
                clientPaintWindow.repaint();
            }
    };
}
