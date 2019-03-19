package main.java.com.skocur.netpaint.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that is responsible for sharing data as Java objects to
 * connected client. It implements run method from Runnable interface
 * as it gives our app ability to manage communication layer on background
 * thread.
 */
public class Server implements Runnable {

    // Random port, chosen by luck
    public static int port = 1701;

    private static List<ServerInstance> serverInstances;

    private ServerSocket serverSocket;

    /**
     * Constructor that sets up ServerSocket on 1701 port that will listen
     * for connections.
     *
     * Initialize our List<E> as a LinkedList, because we do not need random access
     * performance as ArrayList<E> provides, but we need constant time O(1) of adding
     * new elements to it.
     */
    public Server() {
        try {
            serverSocket = new ServerSocket(port);
            serverInstances = new LinkedList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implemented method delivered from Runnable interface responsible for accepting
     * client connection. Thanks to this, Client will be able to receive
     * shapes drawn by user (Host) on Server version of application.
     *
     * It runs new server instance as a separated thread (@see ServerInstance) only when new
     * client wants to be connected to host.
     */
    @Override
    public void run() {
        try {
            while (true) {
                var socket = serverSocket.accept();

                ServerInstance serverInstance = new ServerInstance(socket);
                new Thread(serverInstance).start();

                serverInstances.add(serverInstance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that sends data after receiving input (Shape) from User.
     *
     * Gets all server instances and sends data from them, because every client
     * have to be connected on separated thread.
     */
    public void sendData() {
        for (ServerInstance serverInstance : serverInstances) {
            serverInstance.sendData();
        }
    }
}
