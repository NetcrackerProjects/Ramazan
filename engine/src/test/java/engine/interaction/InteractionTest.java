package engine.interaction;

import engine.object.GameObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InteractionTest {

    private GameObject first;
    private GameObject second;
    private GameObject third;

    @Before
    public void setup() {
        this.first = mock(GameObject.class);
        this.second = mock(GameObject.class);
        this.third = mock(GameObject.class);
        when(first.getTypeId()).thenReturn(0);
        when(second.getTypeId()).thenReturn(1);
        when(third.getTypeId()).thenReturn(2);
    }

    @Test
    public void shouldReturnTrueWhenInteractionEqual() {
        Interaction firstInteraction = new Interaction(first, second);
        Interaction secondInteraction = new Interaction(first, second);

        boolean equals = firstInteraction.equals(secondInteraction);

        assertTrue(equals);
    }

    @Test
    public void shouldReturnTrueWhenInteractionWithSwappedObjects() {
        Interaction firstInteraction = new Interaction(first, second);
        Interaction secondInteraction = new Interaction(second, first);

        boolean equals = firstInteraction.equals(secondInteraction);

        assertTrue(equals);
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

        assertEquals(expectedHashCode, swappedHashCode);
    }
}
