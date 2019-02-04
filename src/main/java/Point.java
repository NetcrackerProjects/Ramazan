class Point {

    public double x;
    public double y;

    Point(double x, double y){
        this.x = x;
        this.y = y;
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
