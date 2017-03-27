package bounce;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * The component that draws the balls;
 * 
 * @author bin
 *
 */
public class BallComponent extends JPanel {
	private static final int DEFAULT_WIDTH = 450;
	private static final int DEFAULT_HEIGHT = 350;
	private java.util.List<Ball> balls = new ArrayList<>();

	/**
	 * a ball to the component
	 * 
	 * @param b
	 *            the ball to add
	 */
	public void add(Ball b) {
		balls.add(b);
	}

	public void paintComponent(Graphics g) {
		paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : balls) {
			g2.fill(b.getShape());
		}
	}
}
