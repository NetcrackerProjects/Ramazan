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
        if (rectangle.pos.x > pos.x + size.x) return false;
        if (rectangle.pos.y > pos.y + size.y) return false;
        if (rectangle.pos.x + rectangle.size.x < pos.x) return false;
        if (rectangle.pos.y + rectangle.size.y < pos.y) return false;
        return true;
    }

    public boolean includes(Rectangle rectangle){
        if (rectangle.getPos().x < pos.x) return false;
        if (rectangle.getPos().y < pos.y) return false;
        if (rectangle.getPos().x + rectangle.getSize().x > pos.x + size.x) return false;
        if (rectangle.getPos().y + rectangle.getSize().y > pos.y + size.y) return false;
        return true;
    }

    public void shift(Point shift){
        pos.x = pos.x + shift.x;
        pos.y = pos.y + shift.y;
    }
}
