class GameObject {

    private Rectangle body;
    private Point speed;

    private Point shift;

    GameObject(Point pos, Point size){
        this.body = new Rectangle(pos, size);
        this.speed = new Point(0, 0);
        this.shift = new Point(0, 0);
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

    public void move(double delta){
        this.shift.x = delta * speed.x;
        this.shift.y = delta * speed.y;

        body.shift(shift);
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
