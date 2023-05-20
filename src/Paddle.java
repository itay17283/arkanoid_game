//322622010 Itay Chananel

/**
 * The type Pow.
 *
 * @author Itay Chananel < itay.chananel@live.biu.ac.il >
 * @version 1.0
 * @since 2023 -05-05
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {

    /**
     * The Area 1.
     */
    static final int AREA_1 = 1;
    /**
     * The Area 2.
     */
    static final int AREA_2 = 2;
    /**
     * The Area 3.
     */
    static final int AREA_3 = 3;
    /**
     * The Area 4.
     */
    static final int AREA_4 = 4;
    /**
     * The Area 5.
     */
    static final int AREA_5 = 5;

    /**
     * The Area 1 angle.
     */
    static final int AREA_1_ANGLE = 300;
    /**
     * The Area 2 angle.
     */
    static final int AREA_2_ANGLE = 330;
    /**
     * The Area 3 angle.
     */
    static final int AREA_3_ANGLE = 0;
    /**
     * The Area 4 angle.
     */
    static final int AREA_4_ANGLE = 30;
    /**
     * The Area 5 angle.
     */
    static final int AREA_5_ANGLE = 60;
    private biuoop.KeyboardSensor keyboard;

    private Rectangle rect;


    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard the keyboard
     */
    public Paddle(biuoop.KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.rect = new Rectangle(new Point(350, 560), 100, 20, Color.ORANGE);
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            rect.getUpperLeft().setX(rect.getUpperLeft().getX() - 10);
            //System.out.println("the 'left arrow' key is pressed");
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        rect.getUpperLeft().setX(rect.getUpperLeft().getX() + 10);
    }

    @Override
    public void timePassed() {
        //move the paddle right if it is not out of bounds
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) && (rect.getUpperLeft().getX() + rect.getWidth() + 5) < 785) {
            moveRight();
        }
        //move the paddle left if it is not out of bounds
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && (rect.getUpperLeft().getX() - 5) > 15) {
            moveLeft();
        }
    }

    /**
     * Draw on the paddle.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(rect.getColor());
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        int hitArea = hitArea(collisionPoint);
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        //hit point in area 1
        if (hitArea == AREA_1) {
            Velocity newV = Velocity.fromAngleAndSpeed(AREA_1_ANGLE, Math.sqrt(dx * dx + dy * dy));
            currentVelocity.setDx(-Math.abs(newV.getDx()));
            currentVelocity.setDy(-Math.abs(newV.getDy()));
            //hit point in area 2
        } else if (hitArea == AREA_2) {
            Velocity newV = Velocity.fromAngleAndSpeed(AREA_2_ANGLE, Math.sqrt(dx * dx + dy * dy));
            currentVelocity.setDx(-Math.abs(newV.getDx()));
            currentVelocity.setDy(-Math.abs(newV.getDy()));
            //hit point in area 3
        } else if (hitArea == AREA_3) {
            currentVelocity.setDy(-currentVelocity.getDy());
            /*
            Velocity newV = Velocity.fromAngleAndSpeed(AREA_3_ANGLE, Math.sqrt(dx * dx + dy * dy));
            currentVelocity.setDx(newV.getDx());
            currentVelocity.setDy(newV.getDy());
             */
            //hit point in area 4
        } else if (hitArea == AREA_4) {
            Velocity newV = Velocity.fromAngleAndSpeed(AREA_4_ANGLE, Math.sqrt(dx * dx + dy * dy));
            currentVelocity.setDx(Math.abs(newV.getDx()));
            currentVelocity.setDy(-Math.abs(newV.getDy()));
            //hit point in area 5
        } else if (hitArea == AREA_5) {
            Velocity newV = Velocity.fromAngleAndSpeed(AREA_5_ANGLE, Math.sqrt(dx * dx + dy * dy));
            currentVelocity.setDx(Math.abs(newV.getDx()));
            currentVelocity.setDy(-Math.abs(newV.getDy()));
        }
        return currentVelocity;
    }

    /**
     * function returns the hit area in the paddle.
     *
     * @param collisionPoint the collision point
     * @return the int
     */
    public int hitArea(Point collisionPoint) {
        double x = collisionPoint.getX();
        double upperX = rect.getUpperLeft().getX();
        double fifthWidth = (rect.getWidth() / 5);
        //area 1
        if (x >= upperX && x <= upperX + fifthWidth * 1) {
            return AREA_1;
            //area 2
        } else if (x > upperX + fifthWidth * 1 && x <= upperX + fifthWidth * 2) {
            return AREA_2;
            //area 3
        } else if (x > upperX + fifthWidth * 2 && x <= upperX + fifthWidth * 3) {
            return AREA_3;
            //area 4
        } else if (x > upperX + fifthWidth * 3 && x <= upperX + fifthWidth * 4) {
            return AREA_4;
            //area 5
        } else {
            return AREA_5;
        }
    }

    @Override
    public void addToGame(Game g) {
        //add the paddle to both sprites and collidables lists
        g.addSprite(this);
        g.addCollidable(this);
    }
}