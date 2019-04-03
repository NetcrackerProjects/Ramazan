package server;

class Main {

    public static void main(String[] argv) {

        Server server = new Server(Server.PORT);

        server.start();
    }
}
