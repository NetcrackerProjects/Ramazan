import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class GameLoopTest {

    @Test
    public void should_notRun_when_terminated() throws InterruptedException {
        GameLoop gameLoop = new GameLoop();
        gameLoop.start();

        gameLoop.terminate();

        assertFalse(gameLoop.isRunning());
    }
}
