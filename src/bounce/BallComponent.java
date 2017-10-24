package bounce;

import java.awt.*;
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

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);//fixed bug 忘了加super.，结果递归调用自己，形成了死循环。
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : balls) {
			g2.fill(b.getShape());
		}
	}

	/**
	 * If the <code>preferredSize</code> has been set to a
	 * non-<code>null</code> value just returns it.
	 * If the UI delegate's <code>getPreferredSize</code>
	 * method returns a non <code>null</code> value then return that;
	 * otherwise defer to the component's layout manager.
	 *
	 * @return the value of the <code>preferredSize</code> property
	 * @see #setPreferredSize
	 * @see //ComponentUI
	 */
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
}
