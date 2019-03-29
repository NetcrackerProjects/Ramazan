package server;

import java.io.IOException;
import java.util.Scanner;

class ServerCloserListener extends Thread {

    private static final String END = "end";

    private volatile boolean running;

    private final Server server;

    ServerCloserListener(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        Scanner reader = new Scanner(System.in);
        this.running = true;
        while (isRunning()) {
            String line = reader.nextLine();

            if (line.equals(END)) {
                endServer();
            }
        }
    }

    private boolean isRunning() {
        return running;
    }

    private void endServer() {
        try {
            server.terminate();
            this.running = false;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
