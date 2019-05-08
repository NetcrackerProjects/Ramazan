package server.connection.registration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.exception.FailedCreateUserException;
import server.user.User;
import server.user.UserFactory;
import server.user.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientRegistrationHandlerTest {

    private static final int PORT = 6666;

    private static final String MONITOR_SIZE = "10:10";

    private static final int USER_ID = 1;

    private ClientRegistrationHandler clientRegistrationHandler;

    private Socket socket;
    private ServerSocket serverSocket;

    private UserFactory userFactory;
    private UserManager userManager;

    private PrintWriter printer;

    @Before
    public void setup() throws IOException, FailedCreateUserException {
        this.serverSocket = new ServerSocket(PORT);
        this.socket = new Socket("127.0.0.1", PORT);
        Socket clientSocket = serverSocket.accept();

        this.printer = new PrintWriter(socket.getOutputStream(), true);
        this.userFactory = mock(UserFactory.class);
        User user = mock(User.class);
        when(user.getId()).thenReturn(USER_ID);
        when(userFactory.createUser(any())).thenReturn(user);
        this.userManager = mock(UserManager.class);
        this.clientRegistrationHandler = new ClientRegistrationHandler(clientSocket, userFactory, userManager);
        clientRegistrationHandler.start();
    }

    @Test
    public void shouldCallCreateUserWhenRegisterNewUser() throws InterruptedException, FailedCreateUserException {
        printer.println(MONITOR_SIZE);

        clientRegistrationHandler.join();
        verify(userFactory, times(1)).createUser(any());
    }

    @Test
    public void shouldCallAddUserToUserManagerWhenRegisterNewUser() throws InterruptedException {
        printer.println(MONITOR_SIZE);

        clientRegistrationHandler.join();
        verify(userManager, times(1)).addUser(any());
    }

    @Test
    public void shouldSendNewIdWhenRegisterNewUser() throws IOException {
        printer.println(MONITOR_SIZE);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = in.readLine();
        assertEquals("1", message);
    }

    @After
    public void cleanUp() throws IOException {
        socket.close();
        serverSocket.close();
    }
}
