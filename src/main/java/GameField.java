import java.util.ArrayList;
import java.util.Collection;

public class GameField {

    private Rectangle field;

    private Collection<GameObject> gameObjects;

    private Rectangle movedBody;

    public GameField(Point size){
        this.field = new Rectangle(new Point(0, 0), size);
        gameObjects = new ArrayList<>();
        movedBody = new Rectangle(new Point(0, 0), new Point(0, 0));
    }

    private boolean isFree(Rectangle rectangle){
        if (outOfField(rectangle)) return false;
        if (intersectsObjects(rectangle)) return false;
        return true;
    }

    private boolean intersectsObjectsExcept(Rectangle rectangle, GameObject exception){
        for(GameObject gameObject: gameObjects){
            if (exception == gameObject) continue;
            if (gameObject.intersects(rectangle)) return true;
        }
        return false;
    }

    private boolean intersectsObjects(Rectangle rectangle){
        for(GameObject gameObject: gameObjects){
            if (gameObject.intersects(rectangle)) return true;
        }
        return false;
    }

    private boolean outOfField(Rectangle rectangle){
        return !field.includes(rectangle);
    }

    private boolean canMove(GameObject gameObject, double delta){
        Point speed = gameObject.getSpeed();

        movedBody.getPos().x = gameObject.getPos().x + speed.x * delta;
        movedBody.getPos().y = gameObject.getPos().y + speed.y * delta;
        movedBody.getSize().x = gameObject.getSize().x;
        movedBody.getSize().y = gameObject.getSize().y;

        if (outOfField(movedBody)) return false;
        if (intersectsObjectsExcept(movedBody, gameObject)) return false;
        return true;
    }

    public void update(double delta){
        move(delta);
    }

    private void move(double delta){
        for(GameObject gameObject: gameObjects){
            if (canMove(gameObject, delta)) gameObject.move(delta);
        }
    }


    public void addObject(GameObject gameObject){
        if (isFree(gameObject.getBody())) gameObjects.add(gameObject);
    }

    public void removeObject(GameObject gameObject){
        gameObjects.remove(gameObject);
    }

    public Collection<GameObject> getGameObjects() {
        return gameObjects;
    }
}
