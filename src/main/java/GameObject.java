class GameObject {

    private Rectangle body;
    private Point speed;

    GameObject(Point leftTop, Point rightBottom){
        this.body = new Rectangle(leftTop, rightBottom);
        this.speed = new Point(0, 0);
    }

    Point getSpeed() {
        return speed;
    }

    void setSpeed(Point speed){
        this.speed = speed;
    }

    boolean intersects(Rectangle rectangle){
        return body.intersects(rectangle);
    }

    void move(){
        body.shift(speed);
    }

    Point getPos(){
        return body.getLeftTop();
    }

    Rectangle getBody() {
        return body;
    }
}
