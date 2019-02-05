import java.util.Collection;
import java.util.HashSet;

class GameField {

    private Rectangle field;

    private Collection<GameObject> gameObjects;

    private Rectangle movedBody;

    GameField(Point size){
        this.field = new Rectangle(new Point(0, 0), size);
        this.gameObjects = new HashSet<>();
        this.movedBody = new Rectangle(new Point(0, 0), new Point(0, 0));
    }

    public void addObject(GameObject gameObject) throws OutOfBoundaryException, ObjectIntersectionException {
        Rectangle body = gameObject.getBody();

        if (outOfField(body)){
            throw new OutOfBoundaryException();
        }

        if (intersectsObjects(body)){
            throw new ObjectIntersectionException();
        }

        gameObjects.add(gameObject);
    }

    public void update(){
        move();
    }

    private boolean intersectsObjectsExcept(Rectangle rectangle, GameObject exception){
        for(GameObject gameObject: gameObjects){
            if (exception == gameObject){
                continue;
            }

            if (gameObject.intersects(rectangle)){
                return true;
            }

        }
        return false;
    }

    private boolean intersectsObjects(Rectangle rectangle){
        for(GameObject gameObject: gameObjects){
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

        this.movedBody.getPos().x = gameObject.getPos().x + speed.x;
        this.movedBody.getPos().y = gameObject.getPos().y + speed.y;
        this.movedBody.getSize().x = gameObject.getSize().x;
        this.movedBody.getSize().y = gameObject.getSize().y;

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
