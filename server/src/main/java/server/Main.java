package server;

class Main {

    public static void main(String[] argv) {

        Server server = new Server(Server.PORT);

        Runtime.getRuntime().addShutdownHook(new StopServerHook(server));

        server.start();
    }
}
