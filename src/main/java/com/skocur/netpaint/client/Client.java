package main.java.com.skocur.netpaint.client;

import main.java.com.skocur.netpaint.ShapesManager;
import main.java.com.skocur.netpaint.server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Class that is responsible for getting data from Server version of NetPaint.
 * It implements run method from Runnable interface as it gives our app ability not only to
 * manage communication layer on background thread, but also to receive and process
 * data from Server.
 */
public class Client implements Runnable {

    private Socket socket;
    private SocketDataListener socketDataListener;

    /**
     * Constructor that sets up Socket on 1701 port that will try to
     * connect to ServerSocket. @see Server
     *
     * @param socketDataListener Functional interface @see SocketDataListener
     */
    public Client(SocketDataListener socketDataListener) {
        try {
            socket = new Socket("localhost", Server.port);

            this.socketDataListener = socketDataListener;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            if (socket != null) {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                while (true) {
                    ShapesManager.addShapeBasedOn(objectInputStream.readObject());

                    socketDataListener.onReceive();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Functional interface which is responsible for informing our ClientPaintWindow that it
     * should be repainted after receiving data from server (host).
     */
    @FunctionalInterface
    public interface SocketDataListener {
        void onReceive();
    }
}
