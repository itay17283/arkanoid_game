//322622010 Itay Chananel

/**
 * The type Pow.
 *
 * @author Itay Chananel < itay.chananel@live.biu.ac.il >
 * @version 1.0
 * @since 2023 -05-05
 */

/**
 * The type Collision info.
 */
public class CollisionInfo {

    private Point point;
    private Collidable col;

    /**
     * Instantiates a new Collision info.
     *
     * @param point the point
     * @param col   the collidable object
     */
    public CollisionInfo(Point point, Collidable col) {
        this.point = point;
        this.col = col;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.col;
    }
}
