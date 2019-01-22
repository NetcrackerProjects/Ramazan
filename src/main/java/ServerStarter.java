public class ServerStarter {

    public Server start(int port){
        Server server = new Server(port);
        server.start();
        return server;
    }
}
