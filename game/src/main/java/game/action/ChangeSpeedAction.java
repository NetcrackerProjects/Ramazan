package game.action;

import engine.action.Action;
import engine.geometry.Vector;
import engine.object.GameObject;

public class ChangeSpeedAction implements Action {

    private final GameObject gameObject;
    private final Vector newSpeed;

    public ChangeSpeedAction(GameObject gameObject, Vector newSpeed) {
        this.gameObject = gameObject;
        this.newSpeed = newSpeed;
    }

    @Override
    public void doAction() {
        gameObject.setSpeed(newSpeed);
    }
}
