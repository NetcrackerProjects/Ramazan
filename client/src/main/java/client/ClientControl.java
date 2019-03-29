package client;

import client.sprite.PlayerCommandsTypeCoder;
import engine.player.command.PlayerCommandType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.HashSet;

class ClientControl extends Thread {

    private final static int MS_PER_UPDATE = 30;

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private final PlayerInput playerInput;

    private volatile boolean running;

    ClientControl(PlayerInput playerInput) {
        this.playerInput = playerInput;
    }

    @Override
    public void run() {

        this.running = true;
        double lag = 0.0;
        double previous = getCurrentTime();
        while (isRunning()) {
            double current = getCurrentTime();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            while (lag >= MS_PER_UPDATE) {
                String message = formMessage();

                try {
                    sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                lag -= MS_PER_UPDATE;
            }
        }

        try {
            stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void startConnection(String ip, int port) throws IOException {
        this.clientSocket = new Socket(ip, port);
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        start();
    }

    private String formMessage() {
        Collection<PlayerCommandType.Type> playerCommands = new HashSet<>();
        playerInput.drainsTo(playerCommands);
        return PlayerCommandsTypeCoder.getMessage(playerCommands);
    }

    private void sendMessage(String text) throws IOException {
        out.println(text);
        in.readLine();
    }

    private void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    private boolean isRunning() {
        return running;
    }

    private double getCurrentTime() {
        return System.currentTimeMillis();
    }
}
