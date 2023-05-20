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
 * The type Ball.
 */
public class Ball implements Sprite {
    /**
     * The Zero.
     */
    static final int ZERO = 0;
    /**
     * The Sleep time.
     */
    static final int SLEEP_TIME = 50;
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;

    private GameEnvironment gameEn;

    /**
     * constructor for new Ball (3 arguments).
     *
     * @param center the center
     * @param r      the radius
     * @param color  the color
     * @param gameEn the game en
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEn) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.gameEn = gameEn;
    }

    /**
     * constructor for new Ball (4 arguments).
     *
     * @param x      the x
     * @param y      the y
     * @param r      the r
     * @param color  the color
     * @param gameEn the game en
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEn) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.gameEn = gameEn;
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Get size int.
     *
     * @return the int
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Get color java . awt . color.
     *
     * @return the java . awt . color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Gets game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEn;
    }

    /**
     * Draw on method.
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), this.r);
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);

    }

    /**
     * helper function for move one step that checks if the ball is going to be out of the board and handles it.
     *
     * @param startWidth  the start width
     * @param startHeight the start height
     * @param endWidth    the end width
     * @param endHeight   the end height
     */
    @SuppressWarnings("checkstyle:RightCurly")
    public void moveOneStepHelper(int startWidth, int startHeight, int endWidth, int endHeight) {
        //change the balls horizontal direction when the ball hits the left border
        if (this.center.getX() - this.r + v.getDx() < startWidth) {
            this.center.setX(startWidth + this.r + this.v.getDx());
            setVelocity(-this.v.getDx(), this.v.getDy());

            //change the balls horizontal direction when the ball hits the right border
        } else if (this.center.getX() + this.r + this.v.getDx() > endWidth) {
            this.center.setX(endWidth - this.r + this.v.getDx());
            setVelocity(-this.v.getDx(), this.v.getDy());
        }
        //change the balls vertical direction when the ball hits the top border
        if (this.center.getY() - this.r + v.getDy() < startHeight) {
            this.center.setY(startHeight + this.r + this.v.getDy());
            setVelocity(this.v.getDx(), -this.v.getDy());
            //change the balls vertical direction when the ball hits the bottom border
        } else if (this.center.getY() + this.r + this.v.getDy() > endHeight) {
            this.center.setY(endHeight - this.r + this.v.getDy());
            setVelocity(this.v.getDx(), -this.v.getDy());
        }
        //execute the step
        moveOneStep();
    }

    /**
     * Move one step of the ball.
     */
    public void moveOneStep() {
        //updates the new location of the ball by setting new center point
        CollisionInfo coInfo = gameEn.getClosestCollision(trajectory());
        //if the ball is going to collide in object
        if (coInfo != null) {
            Point coPoint = coInfo.collisionPoint();
            Collidable coObject = coInfo.collisionObject();
            center.setX(coPoint.getX() - v.getDx());
            center.setY(coPoint.getY() - v.getDy());
            //update the new velocity depends on where the ball will hit
            v = coObject.hit(coPoint, v);
        }
        center = v.applyToPoint(center);
    }

    /**
     * function for the updated trajectory of the line.
     *
     * @return the line
     */
    public Line trajectory() {
        return new Line(center.getX(), center.getY(), center.getX() + v.getDx(), center.getY() + v.getDy());
    }

    @Override
    public void timePassed() {
        //the time passed of the ball is to move one step
        moveOneStep();
    }

    @Override
    public void addToGame(Game g) {
        //add the ball to the sprites array
        g.addSprite(this);
    }
}



/*
//the ball will hit the left line of the block
            if (coObject.getCollisionRectangle().getLeftLine().isPointOnLine(coPoint)) {
                center.setX(coPoint.getX() - r);
                center.setY(coPoint.getY());
            }
            //the ball will hit the right line of the block
            else if (coObject.getCollisionRectangle().getRightLine().isPointOnLine(coPoint)) {
                center.setX(coPoint.getX() + r);
                center.setY(coPoint.getY());
            }
            //the ball will hit the top line of the block
            if (coObject.getCollisionRectangle().getTopLine().isPointOnLine(coPoint)) {
                center.setX(coPoint.getX());
                center.setY(coPoint.getY() - r);
            }
            //the ball will hit the bottom line of the block
            else if (coObject.getCollisionRectangle().getBottomLine().isPointOnLine(coPoint)) {
                center.setX(coPoint.getX());
                center.setY(coPoint.getY() + r);
            }
            center.setX(coPoint.getX() - v.getDx());
            center.setY(coPoint.getY() - v.getDy());
 */