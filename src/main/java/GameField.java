import java.util.Collection;
import java.util.HashSet;

class GameField {

    private Rectangle field;

    private Collection<GameObject> gameObjects;

    GameField(Point leftTop, Point rightBottom){
        this.field = new Rectangle(leftTop, rightBottom);
        this.gameObjects = new HashSet<>();
    }

    void addObject(GameObject gameObject) throws Exception{
        Rectangle objectBody = gameObject.getBody();

        if (isOutOfGameField(objectBody)){
            throw new OutOfBoundaryException();
        }

        if (doesIntersectsGameObjects(objectBody, gameObjects)){
            throw new ObjectIntersectionException();
        }

        gameObjects.add(gameObject);
    }

    void update(){
        move();
    }

    private void move(){
        for(GameObject gameObject: gameObjects){
            if (canMove(gameObject)){
                gameObject.move();
            }
        }
    }

    private boolean canMove(GameObject gameObject){
        Point speed = gameObject.getSpeed();

        Rectangle objectMovedBody = new Rectangle(gameObject.getBody());
        objectMovedBody.shift(speed);

        if (isOutOfGameField(objectMovedBody)){
            return false;
        }

        if (doesIntersectsGameObjectsExcept(objectMovedBody, gameObject)){
            return false;
        }

        return true;
    }

    private boolean doesIntersectsGameObjectsExcept(Rectangle rectangle, GameObject exception){
        Collection<GameObject> gameObjectsWithoutException = new HashSet<>(gameObjects);
        gameObjectsWithoutException.remove(exception);

        return doesIntersectsGameObjects(rectangle, gameObjectsWithoutException);
    }

    private boolean doesIntersectsGameObjects(Rectangle rectangle, Collection<GameObject> objectsToIntersect){
        for(GameObject gameObject: objectsToIntersect){
            if (gameObject.intersects(rectangle)){
                return true;
            }
        }
        return false;
    }

    private boolean isOutOfGameField(Rectangle objectBody){
        return !field.includes(objectBody);
    }
}
