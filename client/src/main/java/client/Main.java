package client;

import java.io.IOException;

class Main {

    public static void main(String[] argv) {
        try {
            new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
