//322622010 Itay Chananel

/**
 * The type Pow.
 *
 * @author Itay Chananel < itay.chananel@live.biu.ac.il >
 * @version 1.0
 * @since 2023 -05-05
 */

import biuoop.DrawSurface;

import java.util.LinkedList;

/**
 * The type Game environment.
 */
public class GameEnvironment {

    private java.util.List<Collidable> coList;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.coList = new LinkedList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the collidable object
     */
    public void addCollidable(Collidable c) {
        coList.add(c);
    }

    /**
     * Draw on all the collidables.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        for (int i = 0; i < coList.size(); i++) {
            coList.get(i).drawOn(surface);
        }
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory of the ball
     * @return the closest collision in the balls trajectory
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        Point closestPo = null;
        Point closestPoToStart = null;
        double min = -10;
        Collidable closestColl = null;
        //checking if there is no hit exist
        if (coList == null || coList.isEmpty()) {
            return null;
        }
        //checking for each collidable if going to be a hit
        for (int i = 0; i < coList.size(); i++) {
            //list for all the intersection points
            java.util.List<Point> interPoints = coList.get(i).getCollisionRectangle().intersectionPoints(trajectory);
            //check if there is at least one intersection point
            if (!interPoints.isEmpty()) {
                //define the closest point to the start
                closestPoToStart = trajectory.closestIntersectionToStartOfLine(coList.get(i).getCollisionRectangle());
                double distanceNewColl = closestPoToStart.distance(trajectory.start());
                //reset the minimum distance by the first intersection point that was found
                if (min == -10) {
                    min = distanceNewColl;
                    closestPo = closestPoToStart;
                    closestColl = coList.get(i);
                    //checking if there is point that closer than the closest point for now
                } else if (distanceNewColl < min) {
                    min = distanceNewColl;
                    closestPo = closestPoToStart;
                    closestColl = coList.get(i);
                }
            }
        }
        if (closestPo != null) {
            CollisionInfo newCollisionInfo = new CollisionInfo(closestPo, closestColl);
            return newCollisionInfo;
        }
        return null;
    }
}
