package server.connection.control;

import game.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ClientControlHandlerTest {

    private Game game;
    private Socket socket;
    private ServerSocket serverSocket;
    private ClientControlHandler clientControlHandler;
    private PrintWriter printer;

    @Before
    public void setup() throws IOException {
        this.serverSocket = new ServerSocket(6666);
        this.socket = new Socket("127.0.0.1", 6666);
        this.printer = new PrintWriter(socket.getOutputStream(), true);
        Socket clientSocket = serverSocket.accept();

        this.game = mock(Game.class);
        this.clientControlHandler = new ClientControlHandler(clientSocket, game);
    }

    @Test
    public void shouldCallGameProcessCommandMethodWhenReceiveCommand() throws InterruptedException {
        clientControlHandler.start();
        printer.println("7:1");

        printer.println("1");

        printer.println("6");
        clientControlHandler.join();
        verify(game, times(1)).processCommand(any());
    }

    @After
    public void cleanUp() throws IOException {
        socket.close();
        serverSocket.close();
    }
}
