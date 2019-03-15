package com.skocur.netpaint.client;

import com.skocur.netpaint.ShapesManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client implements Runnable {

    public static int port = 1701;

    private Socket socket;
    private SocketDataListener socketDataListener;

    public Client(SocketDataListener socketDataListener) {
        try {
            socket = new Socket("localhost", port);

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

    public interface SocketDataListener {
        void onReceive();
    }
}
