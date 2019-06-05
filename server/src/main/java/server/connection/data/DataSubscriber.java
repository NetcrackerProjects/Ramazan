package server.connection.data;

import game.player.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class DataSubscriber {

    private final User user;
    private final Socket socket;
    private final PrintWriter printWriter;

    DataSubscriber(User user, Socket socket) throws IOException {
        this.user = user;
        this.socket = socket;
        this.printWriter = new PrintWriter(socket.getOutputStream());
    }

    User getUser(){
        return user;
    }

    void sendMessage(String message) {
        printWriter.println(message);
    }

    void terminate() throws IOException {
        socket.close();
    }
}
