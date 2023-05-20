//322622010 Itay Chananel

/**
 * The type Pow.
 *
 * @author Itay Chananel < itay.chananel@live.biu.ac.il >
 * @version 1.0
 * @since 2023 -05-05
 */

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite {

    private Rectangle rect;

    /**
     * Instantiates a new Block.
     *
     * @param rect the rect
     */
    public Block(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void timePassed() {
        //time passed at the block is useless because nothing happen to the block after he created
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        //checks if the collision point is on the left line of the rectangle
        if (rect.getLeftLine().isPointOnLine(collisionPoint)) {
            currentVelocity.setDx(-Math.abs(currentVelocity.getDx()));
            //checks if the collision point is on the right line of the rectangle
        } else if (rect.getRightLine().isPointOnLine(collisionPoint)) {
            currentVelocity.setDx(Math.abs(currentVelocity.getDx()));
        }
        //checks if the collision point is on the top line of the rectangle
        if (rect.getTopLine().isPointOnLine(collisionPoint)) {
            currentVelocity.setDy(-Math.abs(currentVelocity.getDy()));
            //checks if the collision point is on the bottom line of the rectangle
        } else if (rect.getBottomLine().isPointOnLine(collisionPoint)) {
            currentVelocity.setDy(Math.abs(currentVelocity.getDy()));
        }
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        //the color of the block
        surface.setColor(rect.getColor());
        surface.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        //the bounds of the block
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    @Override
    public void addToGame(Game g) {
        //add the ball to both sprites and collidables lists
        g.addSprite(this);
        g.addCollidable(this);
    }
}


/*
        if (rect.getLeftLine().isPointOnLine(collisionPoint)) {
                currentVelocity.setDx(-Math.abs(currentVelocity.getDx()));
                }
                //checks if the collision point is on the right line of the rectangle
                else if (rect.getRightLine().isPointOnLine(collisionPoint)) {
                currentVelocity.setDx(Math.abs(currentVelocity.getDx()));
                }
                //checks if the collision point is on the top line of the rectangle
                if (rect.getTopLine().isPointOnLine(collisionPoint)) {
                currentVelocity.setDy(-Math.abs(currentVelocity.getDy()));
                }
                //checks if the collision point is on the bottom line of the rectangle
                else if (rect.getBottomLine().isPointOnLine(collisionPoint)) {
                currentVelocity.setDy(Math.abs(currentVelocity.getDy()));
                }

 */
