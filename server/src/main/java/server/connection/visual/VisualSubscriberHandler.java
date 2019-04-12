package server.connection.visual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class VisualSubscriberHandler extends Thread{

    private final Socket socket;

    private final VisualSubscriberManager visualSubscriberManager;

    VisualSubscriberHandler(Socket socket, VisualSubscriberManager visualSubscriberManager) {
        this.socket = socket;
        this.visualSubscriberManager = visualSubscriberManager;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = in.readLine();

            int userId = Integer.parseInt(line);

            visualSubscriberManager.addVisualSubscriber(userId, socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
