import java.util.Objects;

class Rectangle {

    private Point leftTop;
    private Point bottomRight;

    Rectangle(){
        this.leftTop = new Point(0, 0);
        this.bottomRight = new Point(0, 0);
    }

    Rectangle(Point leftTop, Point bottomRight){
        this.leftTop = leftTop;
        this.bottomRight = bottomRight;
    }

    public Point getLeftTop() {
        return leftTop;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public boolean intersects(Rectangle rectangle){
        if (rectangle.leftTop.x > bottomRight.x){
            return false;
        }

        if (rectangle.leftTop.y > bottomRight.y){
            return false;
        }

        if (rectangle.bottomRight.x < leftTop.x){
            return false;
        }

        if (rectangle.bottomRight.y < leftTop.y){
            return false;
        }

        return true;
    }

    public boolean includes(Rectangle rectangle){
        if (rectangle.getLeftTop().x < leftTop.x){
            return false;
        }

        if (rectangle.getLeftTop().y < leftTop.y){
            return false;
        }

        if (rectangle.getBottomRight().x > bottomRight.x){
            return false;
        }

        if (rectangle.getBottomRight().y > bottomRight.y){
            return false;
        }

        return true;
    }

    public void setRectangle(Rectangle rectangle){
        this.leftTop.x = rectangle.getLeftTop().x;
        this.leftTop.y = rectangle.getLeftTop().y;
        this.bottomRight.x = rectangle.getBottomRight().x;
        this.bottomRight.y = rectangle.getBottomRight().y;
    }

    public void shift(Point shift){
        this.leftTop.x = this.leftTop.x + shift.x;
        this.leftTop.y = this.leftTop.y + shift.y;
        this.bottomRight.x = this.bottomRight.x + shift.x;
        this.bottomRight.y = this.bottomRight.y + shift.y;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }

        if (object == null || getClass() != object.getClass()){
            return false;
        }

        Rectangle rectangle = (Rectangle) object;

        return Objects.equals(leftTop, rectangle.leftTop) && Objects.equals(bottomRight, rectangle.bottomRight);
    }
}