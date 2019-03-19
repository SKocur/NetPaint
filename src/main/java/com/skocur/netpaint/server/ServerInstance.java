package main.java.com.skocur.netpaint.server;

import main.java.com.skocur.netpaint.ShapesManager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class, which is responsible for managing connected client as a separated
 * instance run in background thread.
 */
public class ServerInstance implements Runnable {

    private Socket socket;
    private ObjectOutputStream objectOutputStream;

    /**
     * Initializes client socket.
     *
     * @param socket Socket instance
     */
    public ServerInstance(Socket socket) {
        this.socket = socket;
    }

    /**
     * Just setting output stream of received socket instance.
     */
    @Override
    public void run() {
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Via this method all objects that implement Shape interface are sent.
     * Every invokation only one object is sent, the last one.
     */
    void sendData() {
        try {
            int indexOfLastObject = ShapesManager.shapes.size() - 1;
            objectOutputStream.writeObject(ShapesManager.shapes.get(indexOfLastObject));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
