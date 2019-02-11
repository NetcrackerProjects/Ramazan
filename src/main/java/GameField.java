import java.util.Collection;
import java.util.HashSet;

class GameField {

    private Rectangle field;

    private Collection<GameObject> gameObjects;

    private Rectangle movedBody;

    GameField(Point leftTop, Point rightBottom){
        this.field = new Rectangle(leftTop, rightBottom);
        this.gameObjects = new HashSet<>();
        this.movedBody = new Rectangle();
    }

    void addObject(GameObject gameObject) throws OutOfBoundaryException, ObjectIntersectionException {
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

    private boolean canMove(GameObject gameObject){
        Point speed = gameObject.getSpeed();
        
        movedBody.setRectangle(gameObject.getBody());
        movedBody.shift(speed);

        if (isOutOfGameField(movedBody)){
            return false;
        }

        if (doesIntersectsGameObjectsExcept(movedBody, gameObject)){
            return false;
        }

        return true;
    }

    private void move(){
        for(GameObject gameObject: gameObjects){
            if (canMove(gameObject)){
                gameObject.move();
            }
        }
    }
}
