package object.manager;

import exception.WrongObjectIdException;
import object.Tank;
import object.Type;

import java.util.HashMap;
import java.util.Map;

public class TankObjectManager implements ObjectManager<Tank> {

    private final Map<Integer, Tank> tankMap;

    TankObjectManager() {
        this.tankMap = new HashMap<>();
    }

    @Override
    public Tank getObjectById(int id) throws WrongObjectIdException {
        Tank tank = tankMap.getOrDefault(id, null);

        if (tank == null) {
            throw new WrongObjectIdException();
        }

        return tank;
    }

    @Override
    public void addObject(Tank object) {
        tankMap.put(object.getId(), object);
    }

    @Override
    public void removeById(int id) {
        tankMap.remove(id);
    }

    @Override
    public int getTypeId() {
        return Type.TANK;
    }
}
