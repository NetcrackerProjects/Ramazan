package game.action;

import engine.object.GameObject;
import engine.object.manager.ObjectManager;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class DeleteActionTest {

    @Test
    @SuppressWarnings("unchecked")
    public void shouldRemoveGameObjectWhenDeleteActionIsInvoked() {
        GameObject gameObject = mock(GameObject.class);
        ObjectManager<GameObject> manager = mock(ObjectManager.class);
        DeleteAction<GameObject> deleteAction = new DeleteAction<>(gameObject, manager);

        deleteAction.doAction();

        verify(manager, times(1)).removeObject(gameObject);
    }
}
