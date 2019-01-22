import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private MessageHandler messageHandler;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.messageHandler = new TextMessageHandler();
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                Message message = messageHandler.getMessage(inputLine);
                if (message.isEnd()) {
                    out.println("bye");
                    break;
                }
                out.println(message.getTextMessage());
            }

            in.close();
            out.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
