package engine.visualizer;

import engine.object.GameObject;

import java.util.Collection;

public interface Visualizer {

    void draw(Collection<GameObject> gameObjects);
}
