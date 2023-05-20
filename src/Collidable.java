//322622010 Itay Chananel

/**
 * The type Pow.
 *
 * @author Itay Chananel < itay.chananel@live.biu.ac.il >
 * @version 1.0
 * @since 2023 -05-05
 */

import biuoop.DrawSurface;

/**
 * The interface Collidable.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);

    /**
     * Draw on the wanted object.
     *
     * @param surface the surface
     */
    void drawOn(DrawSurface surface);
}
