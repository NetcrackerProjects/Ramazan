import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;

public class ServerTest {

    @Test
    public void givenServer_whenTerminate_thenCorrect() throws IOException, InterruptedException {

        ServerStarter serverStarter = new ServerStarter();
        Server server = serverStarter.start(6666);

        server.terminate();

        assertFalse(server.isAlive());

    }
}
