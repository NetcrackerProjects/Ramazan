class GameObject {

    private Rectangle body;
    private Point speed;

    GameObject(Point pos, Point size){
        this.body = new Rectangle(pos, size);
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
        return body.getPos();
    }

    public Point getSize(){
        return body.getSize();
    }

    public Rectangle getBody() {
        return body;
    }
}
