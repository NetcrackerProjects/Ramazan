package engine.command;

import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.mockito.Mockito.*;

public class EngineCommandProcessorTest {

    @Test
    public void shouldCallGetActionWhenProcessCommands() {
        EngineCommandProcessor engineCommandProcessor = new EngineCommandProcessor();
        Collection<EngineCommand> engineCommands = new HashSet<>();
        EngineCommand engineCommand = mock(EngineCommand.class);
        engineCommands.add(engineCommand);

        engineCommandProcessor.processCommands(engineCommands);

        verify(engineCommand, times(1)).getAction();
    }
}
