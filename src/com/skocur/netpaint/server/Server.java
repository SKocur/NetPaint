package com.skocur.netpaint.server;

import com.skocur.netpaint.ShapesManager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    public static int port = 1701;

    private ServerSocket serverSocket;
    private ObjectOutputStream objectOutputStream;
    private boolean isClientConnected = false;

    public Server() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            var socket = serverSocket.accept();

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            isClientConnected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData() {
        try {
            if (isClientConnected) {
                objectOutputStream.writeObject(ShapesManager.shapes.get(ShapesManager.shapes.size() - 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
