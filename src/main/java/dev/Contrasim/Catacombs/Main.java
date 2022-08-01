package dev.Contrasim.Catacombs;

public class Main {

    private final Server server;

    public Main() {
        this.server = new Server(8080);
        server.start();
    }

    public static void main(String[] args) {
        new Main();
    }

}
