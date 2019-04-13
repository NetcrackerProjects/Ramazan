package server.connection.registration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.user.User;
import server.user.UserFactory;
import server.user.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientRegistrationListenerTest {

    private static final int PORT = 6666;

    private static final String MONITOR_SIZE = "10:10";

    private static final int USER_ID = 1;

    private ClientRegistrationListener clientRegistrationListener;

    @Before
    public void setup() throws IOException {
        UserFactory userFactory = mock(UserFactory.class);
        User user = mock(User.class);
        when(user.getId()).thenReturn(USER_ID);
        when(userFactory.createUser(any())).thenReturn(user);
        UserManager userManager = mock(UserManager.class);
        this.clientRegistrationListener = new ClientRegistrationListener(userFactory, userManager);
        clientRegistrationListener.start();
    }

    @Test
    public void shouldCreateHandlerThatWouldProcessRegistrationRequestWhenRun() throws IOException {
        Socket socket = new Socket("127.0.0.1", PORT);
        PrintWriter printer = new PrintWriter(socket.getOutputStream(), true);
        printer.println(MONITOR_SIZE);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = in.readLine();
        socket.close();
        assertEquals("1", message);
    }

    @After
    public void cleanUp() throws IOException {
        clientRegistrationListener.terminate();
    }
}