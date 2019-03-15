package com.skocur.netpaint;

import com.skocur.netpaint.client.Client;
import com.skocur.netpaint.client.ClientPaintWindow;
import com.skocur.netpaint.server.Server;
import com.skocur.netpaint.server.ServerPaintWindow;

public class Main {

    public static Server server = new Server();
    static PaintType paintType = PaintType.SERVER;

    private static ClientPaintWindow clientPaintWindow;

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

    private static Client.SocketDataListener socketDataListener = new Client.SocketDataListener() {
        @Override
        public void onReceive() {
            if (clientPaintWindow != null && paintType == PaintType.CLIENT) {
                clientPaintWindow.repaint();
            }
        }
    };
}
