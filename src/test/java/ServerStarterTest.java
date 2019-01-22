import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;

public class ServerStarterTest {

    @Test
    public void givenStarter_whenStart_thenNotNull() throws IOException, InterruptedException {
        ServerStarter starter = new ServerStarter();

        Server server = starter.start(6666);

        assertNotEquals(null, server);

        server.terminate();
    }
}
