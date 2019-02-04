import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class GameLoopTest {

    @Test
    public void givenGameLoop_whenTerminate_thenCorrect() throws InterruptedException {
        GameLoop gameLoop = new GameLoop();
        gameLoop.start();

        gameLoop.terminate();

        assertFalse(gameLoop.isRunning());
    }
}
