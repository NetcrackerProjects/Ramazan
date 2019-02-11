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
        Rectangle body = gameObject.getBody();

        if (outOfField(body)){
            throw new OutOfBoundaryException();
        }

        if (intersectsObjects(body, gameObjects)){
            throw new ObjectIntersectionException();
        }

        gameObjects.add(gameObject);
    }

    void update(){
        move();
    }

    private boolean intersectsObjectsExcept(Rectangle rectangle, GameObject exception){
        Collection<GameObject> gameObjectsWithoutException = new HashSet<>(gameObjects);
        gameObjectsWithoutException.remove(exception);

        return intersectsObjects(rectangle, gameObjectsWithoutException);
    }

    private boolean intersectsObjects(Rectangle rectangle, Collection<GameObject> objectsToIntersect){
        for(GameObject gameObject: objectsToIntersect){
            if (gameObject.intersects(rectangle)){
                return true;
            }
        }
        return false;
    }

    private boolean outOfField(Rectangle rectangle){
        return !field.includes(rectangle);
    }

    private boolean canMove(GameObject gameObject){
        Point speed = gameObject.getSpeed();

        movedBody.setRectangle(gameObject.getBody());
        movedBody.shift(speed);

        if (outOfField(movedBody)){
            return false;
        }

        if (intersectsObjectsExcept(movedBody, gameObject)){
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
