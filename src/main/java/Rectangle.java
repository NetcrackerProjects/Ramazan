import java.util.Objects;

class Rectangle {

    private Point topLeft;
    private Point bottomRight;

    Rectangle(){
        this.topLeft = new Point(0, 0);
        this.bottomRight = new Point(0, 0);
    }

    Rectangle(Point topLeft, Point bottomRight){
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    Rectangle(Rectangle rectangle){
        this.topLeft = new Point(rectangle.getTopLeft());
        this.bottomRight = new Point(rectangle.getBottomRight());
    }

    Point getTopLeft() {
        return topLeft;
    }

    private Point getBottomRight() {
        return bottomRight;
    }

    boolean intersects(Rectangle rectangle){
        if (rectangle.topLeft.x > bottomRight.x){
            return false;
        }

        if (rectangle.topLeft.y > bottomRight.y){
            return false;
        }

        if (rectangle.bottomRight.x < topLeft.x){
            return false;
        }

        if (rectangle.bottomRight.y < topLeft.y){
            return false;
        }

        return true;
    }

    boolean includes(Rectangle rectangle){
        if (rectangle.getTopLeft().x < topLeft.x){
            return false;
        }

        if (rectangle.getTopLeft().y < topLeft.y){
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

    void copyRectangle(Rectangle rectangle){
        topLeft.copyPoint(rectangle.topLeft);
        bottomRight.copyPoint(rectangle.bottomRight);
    }

    void shift(Point shift){
        topLeft.shift(shift);
        bottomRight.shift(shift);
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

        return Objects.equals(topLeft, rectangle.topLeft) && Objects.equals(bottomRight, rectangle.bottomRight);
    }
}