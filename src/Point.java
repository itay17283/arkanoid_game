//322622010 Itay Chananel

/**
 * The type Pow.
 *
 * @author Itay Chananel < itay.chananel@live.biu.ac.il >
 * @version 1.0
 * @since 2023 -05-05
 */

/**
 * The type Point.
 */
public class Point {
    /**
     * The Threshlod.
     */
    static final double THRESHLOD = 0.00001;
    private double x;
    private double y;

    /**
     * constructor for new Point.
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * function calculates the distance between 2 points.
     *
     * @param other the point that we want to check the distance between it and our point
     * @return the distance between the two points
     */
    public double distance(Point other) {
        double squareX = (other.getX() - this.x) * (other.getX() - this.x);
        double squareY = (other.getY() - this.y) * (other.getY() - this.y);
        return Math.sqrt(squareX + squareY);
    }

    /**
     * return True if the point that was sent is the same point.
     *
     * @param other the point that we check if equals to the current point
     * @return true if it is the same point and false if not
     */
    public boolean equals(Point other) {
        return (Math.abs(other.getX() - this.x) < THRESHLOD && Math.abs(other.getY() - this.y) < THRESHLOD);
    }

    /**
     * Get x.
     *
     * @return x x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get y.
     *
     * @return y y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }
}
