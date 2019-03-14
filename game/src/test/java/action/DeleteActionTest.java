package action;

import object.GameObject;
import object.manager.ObjectManager;
import org.junit.Test;
import org.mockito.Mockito;

public class DeleteActionTest {

    @Test
    @SuppressWarnings("unchecked")
    public <T extends GameObject> void shouldCallRemoveGameObjectWhenDoAction() {
        T gameObject = (T) Mockito.mock(GameObject.class);
        ObjectManager<T> manager = (ObjectManager<T>) Mockito.mock(ObjectManager.class);
        DeleteAction<T> deleteAction = new DeleteAction<>(gameObject, manager);

        deleteAction.doAction();

        Mockito.verify(manager, Mockito.times(1)).removeObject(gameObject);
    }
}
