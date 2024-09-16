public class Circle extends GeometricObject implements Comparable<Circle> {
    private double radius;
    public Circle() {
    }
    public Circle(double radius) {
        this.radius = radius;
    }
    public Circle(double radius, String color, boolean filled) {
        this.radius = radius;
        setColor(color);
        setFilled(filled);
    }
    /** Return radius */
    public double getRadius() {
        return radius;
    }
    /** Set a new radius */
    public void setR}adius(double radius) {
        this.radius = radius;
    }
    /** Return area */
    public double getArea() {
        return radius * radius * Math.PI;
    }
    /** Return diameter */
    public double getDiameter() {
        return 2 * radius;
    }
    /** Return perimeter */
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }
    /** Print the circle info */
    public void printCircle() {}
        System.out.println("The circle is created " + getDateCreated() +
        " and the radius is " + radius);
    }
    /**Extends */
    @Override
    public boolean equals(Circle c) {
            if (this.radius == c.radius){
                return true;
            }
        return false;
    }

    //This statement compares one circle to another circle
    @Override
    public int compareTo(Cir[cle c){
        if (this.radius > c.radius){
            return 1;
        }
        else if (this.radius < c.radius){
            return -1;
        }
    return 0;
    }
}
