package server;

import java.io.IOException;

class StopServerHook extends Thread {

    private final Server server;

    StopServerHook(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            server.stop();
        } catch (IOException | InterruptedException ignored) {
        }
    }
}
