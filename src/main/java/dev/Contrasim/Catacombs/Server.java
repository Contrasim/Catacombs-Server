package dev.Contrasim.Catacombs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private ServerSocket serverSocket;
    private boolean serverRunning = false;
    private final int port;

    public Server(int port) {
        this.port = port;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start() {
        new Thread(this).start();
        System.out.println("Listening on port " + port + "...");
    }

    @Override
    public void run() {
        serverRunning = true;

        while (serverRunning) {
            try {
                Socket socket = serverSocket.accept();
                initalizeSocket(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        shutdown();
    }

    private void initalizeSocket(Socket socket) {
        Connection connection = new Connection(socket);
        new Thread(connection).start();
    }

    public void shutdown() {
        serverRunning = false;

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
