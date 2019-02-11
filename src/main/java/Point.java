class Point {

    public double x;
    public double y;

    Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    Point(Point point){
        this.x = point.x;
        this.y = point.y;
    }

    void shift(Point shift){
        x += shift.x;
        y += shift.y;
    }

    void copyPoint(Point point){
        this.x = point.x;
        this.y = point.y;
    }

    @Override
    public boolean equals(Object object){
        if (object == null){
            return false;
        }

        if (object.getClass() != Point.class){
            return false;
        }

        Point p = (Point)object;
        return (p.x == x && p.y == y);
    }
}
