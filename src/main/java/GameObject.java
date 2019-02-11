class GameObject {

    private Rectangle body;
    private Point speed;
    private boolean permeable;

    GameObject(Point leftTop, Point rightBottom){
        this.body = new Rectangle(leftTop, rightBottom);
        this.speed = new Point(0, 0);
        this.permeable = false;
    }

    Point getSpeed() {
        return speed;
    }

    void setSpeed(Point speed){
        this.speed = speed;
    }

    Point getPos(){
        return body.getTopLeft();
    }

    Rectangle getBody() {
        return body;
    }

    boolean doesIntersect(Rectangle rectangle){
        return body.intersects(rectangle);
    }

    boolean isPermeable(){
        return permeable;
    }

    void setPermeable() {
        this.permeable = true;
    }

    void move(){
        body.shift(speed);
    }

    void intersectGameObject(GameObject gameObject){
    }
}
