package server;

public class StopServerHook extends Thread {

    private Server server;

    StopServerHook(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        server.stop();
    }
}
