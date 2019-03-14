package interaction;

import junit.framework.TestCase;
import object.GameObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;

public class InteractionTest {

    private GameObject first;
    private GameObject second;
    private GameObject third;

    @Before
    public void setup() {
        this.first = Mockito.mock(GameObject.class);
        this.second = Mockito.mock(GameObject.class);
        this.third = Mockito.mock(GameObject.class);
        Mockito.when(first.getTypeId()).thenReturn(0);
        Mockito.when(second.getTypeId()).thenReturn(1);
        Mockito.when(third.getTypeId()).thenReturn(2);
    }

    @Test
    public void shouldReturnTrueWhenInteractionEqual() {
        Interaction firstInteraction = new Interaction(first, second);
        Interaction secondInteraction = new Interaction(first, second);

        boolean equals = firstInteraction.equals(secondInteraction);

        TestCase.assertTrue(equals);
    }

    @Test
    public void shouldReturnTrueWhenInteractionWithSwappedObjects() {
        Interaction firstInteraction = new Interaction(first, second);
        Interaction secondInteraction = new Interaction(second, first);

        boolean equals = firstInteraction.equals(secondInteraction);

        TestCase.assertTrue(equals);
    }

    @Test
    public void shouldReturnFalseWhenInteractionNotEqual() {
        Interaction firstInteraction = new Interaction(first, second);
        Interaction secondInteraction = new Interaction(first, third);

        boolean equals = firstInteraction.equals(secondInteraction);

        assertFalse(equals);
    }

    @Test
    public void shouldEqualHashCodeWhenInteractionWithSwappedObjects() {
        Interaction firstInteraction = new Interaction(first, second);
        Interaction secondInteraction = new Interaction(second, first);
        int expectedHashCode = firstInteraction.hashCode();

        int swappedHashCode = secondInteraction.hashCode();

        TestCase.assertEquals(expectedHashCode, swappedHashCode);
    }
}
