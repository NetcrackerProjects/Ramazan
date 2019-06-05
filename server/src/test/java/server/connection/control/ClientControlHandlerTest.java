package server.connection.control;

import game.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.exception.NoSuchUserException;
import server.user.UserManager;

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
    private UserManager userManager;

    @Before
    public void setup() throws IOException {
        this.serverSocket = new ServerSocket(7777);
        this.socket = new Socket("127.0.0.1", 7777);
        this.printer = new PrintWriter(socket.getOutputStream(), true);
        Socket clientSocket = serverSocket.accept();

        this.game = mock(Game.class);
        this.userManager = mock(UserManager.class);
        this.clientControlHandler = new ClientControlHandler(clientSocket, game, userManager);
    }

    @Test
    public void shouldCallGameProcessCommandMethodWhenReceiveCommand() throws InterruptedException {
        clientControlHandler.start();
        printer.println("s:2:1");

        printer.println("p:1");

        printer.println("s:1");
        clientControlHandler.join();
        verify(game, times(1)).processCommand(any());
    }

    @Test
    public void shouldSaveUserWhenReceiveEndCommand() throws InterruptedException, NoSuchUserException {
        clientControlHandler.start();
        printer.println("s:2:1");

        printer.println("s:1");
        clientControlHandler.join();
        verify(userManager, times(1)).saveUser(1);
    }

    @After
    public void cleanUp() throws IOException {
        socket.close();
        serverSocket.close();
    }
}
