package object.manager;

import exception.WrongObjectIdException;
import object.GameObject;

interface ObjectManager<T extends GameObject> {

    T getObjectById(int id) throws WrongObjectIdException;

    void addObject(T object);

    void removeById(int id);

    int getTypeId();
}
