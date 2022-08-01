package dev.Contrasim.Catacombs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable {

    private final Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private final int id;

    public Connection(Socket socket) {
        this.socket = socket;
        id = 0;

        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (socket.isConnected()) {
                Object data = inputStream.readObject();
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObject(Object packet) {
        try {
            outputStream.writeObject(packet);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
