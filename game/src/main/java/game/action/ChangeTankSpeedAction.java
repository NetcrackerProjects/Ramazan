package game.action;

import engine.action.Action;
import engine.geometry.Vector;
import game.object.tank.Tank;

public class ChangeTankSpeedAction implements Action {

    private final Tank tank;
    private final Vector newSpeed;

    public ChangeTankSpeedAction(Tank tank, Vector newSpeed) {
        this.tank = tank;
        this.newSpeed = newSpeed;
    }

    @Override
    public void doAction() {
        tank.setSpeed(newSpeed);
        tank.rotateWeapon();
    }
}
