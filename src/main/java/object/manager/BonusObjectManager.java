package object.manager;

import exception.WrongObjectIdException;
import object.BonusHolder;
import object.Type;

import java.util.HashMap;
import java.util.Map;

public class BonusObjectManager implements ObjectManager<BonusHolder> {

    private final Map<Integer, BonusHolder> bonusMap;

    BonusObjectManager() {
        this.bonusMap = new HashMap<>();
    }

    @Override
    public BonusHolder getObjectById(int id) throws WrongObjectIdException {
        BonusHolder bonusHolder = bonusMap.get(id);

        if (bonusHolder == null) {
            throw new WrongObjectIdException();
        }

        return bonusHolder;
    }

    @Override
    public void addObject(BonusHolder object) {
        bonusMap.put(object.getId(), object);
    }

    @Override
    public void removeById(int id) {
        bonusMap.remove(id);
    }

    @Override
    public int getTypeId() {
        return Type.BONUS;
    }
}
