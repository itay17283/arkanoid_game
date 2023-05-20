//322622010 Itay Chananel

/**
 * The type Pow.
 *
 * @author Itay Chananel < itay.chananel@live.biu.ac.il >
 * @version 1.0
 * @since 2023 -05-05
 */


/**
 * The type Line.
 */
public class Line {

    /**
     * The Threshlod.
     */
    static final double THRESHLOD = 0.0000001;

    /**
     * The Epsilon.
     */
    static final double EPSILON = 1e-5;


    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private Point start;
    private Point end;

    /**
     * constructor for new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.start = new Point(this.x1, this.y1);
        this.end = new Point(this.x2, this.y2);
    }

    /**
     * Instantiates a new Line.
     *
     * @param start the start point
     * @param end   the end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * function calculates the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        double squareX = (this.x1 - this.x2) * (this.x1 - this.x2);
        double squareY = (this.y1 - this.y2) * (this.y1 - this.y2);
        return Math.sqrt(squareX + squareY);
    }

    /**
     * calculates the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    /**
     * Start point.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * calculates the slope of line (defined ny the two given points).
     *
     * @param starter the start point of the kine
     * @param ender   the end point of the line
     * @return the slope of the given line
     */
    public double slope(Point starter, Point ender) {
        return ((starter.getY() - ender.getY()) / (starter.getX() - ender.getX()));
    }

    /**
     * Private case at least one of the lines have infinity slope.
     *
     * @param other the other
     * @return true if the lines intersect, false otherwise
     */
    public boolean privateCaseParallelToY(Line other) {
        //checks if both lines have infinity slope
        if (other.isParallelToY() && isParallelToY()) {
            return sameSlopeIsTouch(other);
            //only the current line has infinity slope
        } else if (isParallelToY()) {
            double x = this.x1;
            double y = other.slope(other.start, other.end) * x + other.axisYIntercept();
            return isIntersectionPointOnBothLines(other, x, y);
            //only the other line has infinity slope
        } else {
            double x = other.x1;
            double y = slope(this.start, this.end) * x + axisYIntercept();
            return other.isIntersectionPointOnBothLines(this, x, y);
        }
    }

    /**
     * Private case least one of the lines have infinity slope .
     *
     * @param other the other
     * @return the intersection point of the lines if existed, null otherwise
     */
    public Point privateCaseParallelToYPoint(Line other) {
        //checks if both lines have infinity slope
        if (other.isParallelToY() && isParallelToY()) {
            if (sameSlopeIsInfinite(other)) {
                return null;
            }
            return sameSlopeOnePoint(other);
            //only the current line has infinity slope
        } else if (isParallelToY()) {
            double x = this.x1;
            double y = other.slope(other.start, other.end) * x + other.axisYIntercept();
            if (isIntersectionPointOnBothLines(other, x, y)) {
                return new Point(x, y);
            }
            return null;
            //only the other line has infinity slope
        } else {
            double x = other.x1;
            double y = slope(this.start, this.end) * x + axisYIntercept();
            if (other.isIntersectionPointOnBothLines(this, x, y)) {
                return new Point(x, y);
            }
            return null;
        }
    }


    /**
     * checks if the current line and the given line are intersecting.
     *
     * @param other the other
     * @return true if intersecting and false if not
     */
    public boolean isIntersecting(Line other) {
        //if the lines are equal return true
        if (equals(other)) {
            return true;
        }
        //special case that of the lines parallel to Y axis
        if (other.isParallelToY() || isParallelToY()) {
            return privateCaseParallelToY(other);
        }
        //variables for the slopes of each line
        double slopeCurrent = slope(this.start, this.end);
        double slopeOther = slope(other.start(), other.end());
        //variables for the y-intercept of the lines
        double yInterceptCurrent = axisYIntercept();
        double yInterceptOther = other.axisYIntercept();
        //special case that the slopes are equal
        if (Math.abs(slopeCurrent - slopeOther) < THRESHLOD) {
            // if the slopes are equal and the y-intercept not equal the lines are parallel and will never intersect
            if (yInterceptCurrent != yInterceptOther) {
                return false;
            }
            return sameSlopeIsTouch(other);
        }
        //if the slopes are different it means that in some point the equations of the lines will intersect
        //variables for the intersection point
        double x = (yInterceptCurrent - yInterceptOther) / (slopeOther - slopeCurrent);
        double y = slopeCurrent * x + yInterceptCurrent;
        //checks if the intersection point of the equations of the lines is in the range of the two lines
        return isIntersectionPointOnBothLines(other, x, y);
    }

    /**
     * calculates the intersection point (if exists) of the two lines.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        //if the lines are equal we have infinite times of intersections and function return null
        if (equals(other)) {
            return null;
        }
        //special case that at least one of the lines parallel to Y axis
        if (other.isParallelToY() || isParallelToY()) {
            if (privateCaseParallelToY(other)) {
                return privateCaseParallelToYPoint(other);
            }
            return null;
        }
        //variables for the slopes of each line
        double slopeCurrent = slope(this.start, this.end);
        double slopeOther = slope(other.start(), other.end());
        //variables for the y-intercept of the lines
        double yInterceptCurrent = axisYIntercept();
        double yInterceptOther = other.axisYIntercept();
        //special case that the slopes are equal
        if (Math.abs(slopeCurrent - slopeOther) < THRESHLOD) {
            //if the slopes are equal and the y-intercept not equal the lines are parallel and will never intersect
            if (Math.abs(yInterceptCurrent - yInterceptOther) < THRESHLOD) {
                //checks if there are infinite times of intersections and return null if so
                if (sameSlopeIsInfinite(other)) {
                    return null;
                }
                //checks if the lines with the same slope touches each other in exactly one point,
                //returns this point if so and null otherwise because already checked that they don't
                // have infinite intersection points
                return sameSlopeOnePoint(other);
            }
            //lines with the same slope but don't touch each other
            return null;
        }
        //if the slopes are different it means that in some point the equations of the lines will intersect
        //variables for the intersection point
        double x = (yInterceptCurrent - yInterceptOther) / (slopeOther - slopeCurrent);
        double y = slopeCurrent * x + yInterceptCurrent;
        //checks if the intersection point of the equations of the lines is in the range of the two lines
        if (isIntersectionPointOnBothLines(other, x, y)) {
            return new Point(x, y);
        }
        return null;
    }

    /**
     * checks If the intersection point on both lines.
     *
     * @param other the other line
     * @param x     the x
     * @param y     the y
     * @return true if the intersection point on both lines
     */
    public boolean isIntersectionPointOnBothLines(Line other, double x, double y) {
        if (x + EPSILON >= Math.min(this.start.getX(), this.end.getX())
                && x <= Math.max(this.start.getX(), this.end.getX()) + EPSILON) {
            if (y + EPSILON >= Math.min(this.start.getY(), this.end.getY())
                    && y <= Math.max(this.start.getY(), this.end.getY()) + EPSILON) {
                if (x + EPSILON >= Math.min(other.start.getX(), other.end.getX())
                        && x <= Math.max(other.start.getX(), other.end.getX()) + EPSILON) {
                    if (y + EPSILON >= Math.min(other.start.getY(), other.end.getY())
                            && y <= Math.max(other.start.getY(), other.end.getY()) + EPSILON) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Axis y intercept .
     *
     * @return the y intercept
     */
    public double axisYIntercept() {
        return this.start.getY() - slope(this.start, this.end) * this.start.getX();
    }

    /**
     * checking if the line parallel to y-axis.
     *
     * @return the boolean
     */
    public boolean isParallelToY() {
        return (Math.abs(this.start.getX() - this.end.getX()) < THRESHLOD);
    }

    /**
     * checking if the lines are equal.
     *
     * @param other line to check if equals to the current line
     * @return true if the lines are equal and false if not
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        if (this.start.equals(other.end) && this.end.equals(other.start)) {
            return true;
        }
        return false;
    }

    /**
     * checks if two lines with the same slope have infinite intersection points.
     *
     * @param other the other
     * @return true if the lines have infinite points
     */
    public boolean sameSlopeIsInfinite(Line other) {
        //checks if there are infinite times of intersections and return null if so
        if (this.start.getX() > Math.min(other.start.getX(), other.end.getX())
                && this.start.getX() < Math.max(other.start.getX(), other.end.getX())) {
            return true;
        }
        if (this.end.getX() > Math.min(other.start.getX(), other.end.getX())
                && this.end.getX() < Math.max(other.start.getX(), other.end.getX())) {
            return true;
        }
        if (other.start.getX() > Math.min(this.start.getX(), this.end.getX())
                && other.start.getX() < Math.max(this.start.getX(), this.end.getX())) {
            return true;
        }
        if (other.end.getX() > Math.min(this.start.getX(), this.end.getX())
                && other.end.getX() < Math.max(this.start.getX(), this.end.getX())) {
            return true;
        }
        //the lines with the same slope don't have infinite points
        return false;
    }

    /**
     * after the lines checked that they don't have infinite intersections,
     * checking if they have exactly one intersection point.
     *
     * @param other the other
     * @return the exact intersection point if existed, null otherwise
     */
    public Point sameSlopeOnePoint(Line other) {
        if (Math.abs(this.start.getX() - other.start.getX()) < THRESHLOD
                && Math.abs(this.start.getY() - other.start.getY()) < THRESHLOD) {
            return new Point(this.start.getX(), this.start.getY());
        }
        if (Math.abs(this.start.getX() - other.end.getX()) < THRESHLOD
                && Math.abs(this.start.getY() - other.end.getY()) < THRESHLOD) {
            return new Point(this.start.getX(), this.start.getY());
        }
        if (Math.abs(this.end.getX() - other.start.getX()) < THRESHLOD
                && Math.abs(this.end.getY() - other.start.getY()) < THRESHLOD) {
            return new Point(this.end.getX(), this.end.getY());
        }
        if (Math.abs(this.end.getX() - other.end.getX()) < THRESHLOD
                && Math.abs(this.end.getY() - other.end.getY()) < THRESHLOD) {
            return new Point(this.end.getX(), this.end.getY());
        }
        return null;
    }

    /**
     * checks if two lines with the same slope touch each other.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean sameSlopeIsTouch(Line other) {
        //checks if the lines with the same slope but don't touch each other
        if (Math.min(other.start.getX(), other.end.getX()) > Math.max(this.start.getX(), this.end.getX())) {
            return false;
        }
        if (Math.min(this.start.getX(), this.end.getX()) > Math.max(other.start.getX(), other.end.getX())) {
            return false;
        }
        if (Math.min(other.start.getY(), other.end.getY()) > Math.max(this.start.getY(), this.end.getY())) {
            return false;
        }
        if (Math.min(this.start.getY(), this.end.getY()) > Math.max(other.start.getY(), other.end.getY())) {
            return false;
        }
        //the lines with the same slope and touch each other (one time or infinite times)
        return true;

    }

    /**
     * function checks if point is this line and returns true if so.
     *
     * @param point the point
     * @return true if the point is on the line and false if not
     */
    public boolean isPointOnLine(Point point) {
        if (point.getX() + EPSILON >= Math.min(this.start.getX(), this.end.getX())
                && point.getX() <= Math.max(this.start.getX(), this.end.getX()) + EPSILON) {
            if (point.getY() + EPSILON >= Math.min(this.start.getY(), this.end.getY())
                    && point.getY() <= Math.max(this.start.getY(), this.end.getY()) + EPSILON) {
                return true;
            }
        }
        return false;
    }

    /**
     * function checks if start point is on the rectangle and returns true if so.
     *
     * @param rect the rect
     * @return true if the point is on the rectangle and false if not
     */
    public boolean isStartPointOnRectangle(Rectangle rect) {
        if (rect.getLeftLine().isPointOnLine(start) || rect.getRightLine().isPointOnLine(start)
                || rect.getTopLine().isPointOnLine(start) || rect.getBottomLine().isPointOnLine(start)) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect the rect
     * @return the closest intersection to start of line point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //if the start point is on the rectangle return it (because none other point can be closer)
        if (isStartPointOnRectangle(rect)) {
            return start;
        }
        //list for all the intersection point of this line with the rectangle
        java.util.List<Point> list1 = rect.intersectionPoints(this);
        //if there are no intersection points return null
        if (list1.isEmpty()) {
            return null;
        }
        //initialize the closest point to be the first point in the list
        Point min = list1.get(0);
        //checking all the other points in the list (if exist)
        for (int i = 0; i < list1.size(); i++) {
            double d1 = start.distance(min);
            double d2 = start.distance(list1.get(i));
            //update the closest intersection point if was found
            if (d2 < d1) {
                min = list1.get(i);
            }
        }
        return min;
    }
}
