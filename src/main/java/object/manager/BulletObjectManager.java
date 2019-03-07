package object.manager;

import exception.WrongObjectIdException;
import object.Bullet;
import object.Type;

import java.util.HashMap;
import java.util.Map;

public class BulletObjectManager implements ObjectManager<Bullet> {

    private final Map<Integer, Bullet> bulletMap;

    BulletObjectManager() {
        this.bulletMap = new HashMap<>();
    }

    @Override
    public Bullet getObjectById(int id) throws WrongObjectIdException {
        Bullet bullet = bulletMap.get(id);

        if (bullet == null) {
            throw new WrongObjectIdException();
        }

        return bullet;
    }

    @Override
    public void addObject(Bullet object) {
        bulletMap.put(object.getId(), object);
    }

    @Override
    public void removeById(int id) {
        bulletMap.remove(id);
    }

    @Override
    public int getTypeId() {
        return Type.BULLET;
    }
}
