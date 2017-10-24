package bounce;

import util.u;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
/**
 * a ball that moves and boundce off the edges of a rectangle
 * @author bin
 *
 */
public class Ball {
	private static final int XSIZE = 15;
	private static final int YSIZE = 15;
	private double x = 0;
	private double y = 0;
	private double dx = 1;
	private double dy = 1;

	/**
	 * Moves the ball to the next position,reversing direction if it hits one of
	 * the edges
	 * 
	 * @param bounds
	 */
	public void move(Rectangle2D bounds) {
		x += dx;
		y += dy;
		if (x < bounds.getMinX()) {	//fixed bug ,小于MinX而不是MaxX
			x = bounds.getMinX();
			dx = -dx;
		}
		if (x + XSIZE >= bounds.getMaxX()) {
			x = bounds.getMaxX() - XSIZE;
			dx = -dx;
		}
		if (y < bounds.getMinY()) {
			y = bounds.getMinY();
			dy = -dy;
		}
		if (y + YSIZE >= bounds.getMaxY()) {
			y = bounds.getMaxY() - YSIZE;
			dy = -dy;
		}
	}

	/**
	 * Get the shape of the ball at its current position
	 * 
	 * @return
	 */
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}
}
