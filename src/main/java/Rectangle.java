public class Rectangle {

    private Point pos;
    private Point size;

    public Rectangle(Point pos, Point size){
        this.pos = pos;
        this.size = size;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public Point getSize() {
        return size;
    }

    public void setSize(Point size) {
        this.size = size;
    }

    public boolean intersects(Rectangle rectangle){
        if (rectangle.pos.getX() > pos.getX() + size.getX()) return false;
        if (rectangle.pos.getY() > pos.getY() + size.getY()) return false;
        if (rectangle.pos.getX() + rectangle.size.getX() < pos.getX()) return false;
        if (rectangle.pos.getY() + rectangle.size.getY() < pos.getY()) return false;
        return true;
    }

    public boolean includes(Rectangle rectangle){
        if (rectangle.getPos().getX() < pos.getX()) return false;
        if (rectangle.getPos().getY() < pos.getY()) return false;
        if (rectangle.getPos().getX() + rectangle.getSize().getX() > pos.getX() + size.getX()) return false;
        if (rectangle.getPos().getY() + rectangle.getSize().getY() > pos.getY() + size.getY()) return false;
        return true;
    }

    public void shift(Point shift){
        pos.setX(pos.getX() + shift.getX());
        pos.setY(pos.getY() + shift.getY());
    }
}
