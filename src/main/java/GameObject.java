class GameObject {

    private Rectangle body;
    private Point speed;

    GameObject(Point leftTop, Point rightBottom){
        this.body = new Rectangle(leftTop, rightBottom);
        this.speed = new Point(0, 0);
    }

    public Point getSpeed() {
        return speed;
    }

    public void setSpeed(Point speed){
        this.speed = speed;
    }

    public boolean intersects(Rectangle rectangle){
        return body.intersects(rectangle);
    }

    public void move(){
        body.shift(speed);
    }

    public Point getPos(){
        return body.getLeftTop();
    }

    public Point getSize(){
        return body.getBottomRight();
    }

    public Rectangle getBody() {
        return body;
    }
}
