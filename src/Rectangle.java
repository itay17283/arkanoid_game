//322622010 Itay Chananel

/**
 * The type Pow.
 *
 * @author Itay Chananel < itay.chananel@live.biu.ac.il >
 * @version 1.0
 * @since 2023 -05-05
 */

import java.util.LinkedList;

/**
 * The type Rectangle.
 */
public class Rectangle {

    private Point upperLeft;

    private double width;

    private double height;

    private java.awt.Color color;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     */
    public Rectangle(Point upperLeft, double width, double height, java.awt.Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Gets left line.
     *
     * @return the left line
     */
    public Line getLeftLine() {
        return new Line(getUpperLeft().getX(), getUpperLeft().getY(),
                getUpperLeft().getX(), getUpperLeft().getY() + height);
    }

    /**
     * Gets right line.
     *
     * @return the right line
     */
    public Line getRightLine() {
        return new Line(getUpperLeft().getX() + width, getUpperLeft().getY(),
                getUpperLeft().getX() + width, getUpperLeft().getY() + height);
    }

    /**
     * Gets top line.
     *
     * @return the top line
     */
    public Line getTopLine() {
        return new Line(getUpperLeft().getX(), getUpperLeft().getY(),
                getUpperLeft().getX() + width, getUpperLeft().getY());
    }

    /**
     * Gets bottom line.
     *
     * @return the bottom line
     */
    public Line getBottomLine() {
        return new Line(getUpperLeft().getX(), getUpperLeft().getY() + height,
                getUpperLeft().getX() + width, getUpperLeft().getY() + height);
    }


    /**
     * Get width double.
     *
     * @return the double
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get height double.
     *
     * @return the double
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get upper left point.
     *
     * @return the point
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * function returns all the intersection points of this rectangle with the given line.
     *
     * @param line the line
     * @return the java . util . list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> list1 = new LinkedList<>();
        //checks if intersecting with left line
        if (line.isIntersecting(getLeftLine())) {
            if (line.intersectionWith(getLeftLine()) != null) {
                list1.add(line.intersectionWith(getLeftLine()));
            }
        }
        //checks if intersecting with right line
        if (line.isIntersecting(getRightLine())) {
            if (line.intersectionWith(getRightLine()) != null) {
                list1.add(line.intersectionWith(getRightLine()));
            }
        }
        //checks if intersecting with top line
        if (line.isIntersecting(getTopLine())) {
            if (line.intersectionWith(getTopLine()) != null) {
                list1.add(line.intersectionWith(getTopLine()));
            }
        }
        //checks if intersecting with bottom line
        if (line.isIntersecting(getBottomLine())) {
            if (line.intersectionWith(getBottomLine()) != null) {
                list1.add(line.intersectionWith(getBottomLine()));
            }
        }
        return list1;
    }

    /**
     * Is point exist in list boolean.
     *
     * @param list1 the list 1
     * @param p1    the p 1
     * @return the boolean
     */
    public boolean isPointExistInList(java.util.List<Point> list1, Point p1) {
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).equals(p1)) {
                return true;
            }
        }
        return false;
    }
}
