package com.skocur.netpaint.server;

import com.skocur.netpaint.ShapesManager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

/**
 * Class that is responsible for sharing data as Java objects to
 * connected client. It implements run method from Runnable interface
 * as it gives our app ability to manage communication layer on background
 * thread.
 */
public class Server implements Runnable {

    // Random port, chosen by luck
    public static int port = 1701;

    private ServerSocket serverSocket;
    private ObjectOutputStream objectOutputStream;
    private boolean isClientConnected = false;

    /**
     * Constructor that sets up ServerSocket on 1701 port that will listen
     * for connections.
     */
    public Server() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implemented method delivered from Runnable interface responsible for
     * setting accepting client connection. Thanks to this, Client will be able to receive
     * shapes drawn by user (Host) on Server version of application.
     */
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

    /**
     * Method that sends data after receiving input (Shape) from User.
     * Via this method all objects that implement Shape interface are sent.
     */
    public void sendData() {
        try {
            if (isClientConnected) {
                int indexOfLastObject = ShapesManager.shapes.size() - 1;
                objectOutputStream.writeObject(ShapesManager.shapes.get(indexOfLastObject));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
