package server.connection.data;

import server.exception.NoSuchUserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class DataSubscriberHandler extends Thread{

    private final Socket socket;

    private final DataSubscriberManager dataSubscriberManager;

    DataSubscriberHandler(Socket socket, DataSubscriberManager dataSubscriberManager) {
        this.socket = socket;
        this.dataSubscriberManager = dataSubscriberManager;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = in.readLine();

            int userId = Integer.parseInt(line);

            dataSubscriberManager.addDataSubscriber(userId, socket);

        } catch (IOException | NoSuchUserException e) {
            e.printStackTrace();
        }
    }
}
