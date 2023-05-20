//322622010 Itay Chananel

/**
 * The type Pow.
 *
 * @author Itay Chananel < itay.chananel@live.biu.ac.il >
 * @version 1.0
 * @since 2023 -05-05
 */

/**
 * The type Velocity.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor for new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Get dx double.
     *
     * @return the double
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Get dy double.
     *
     * @return the double
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets dx.
     *
     * @param dx the dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets dy.
     *
     * @param dy the dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the point that we add dx and dy to her
     * @return point with new position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

}