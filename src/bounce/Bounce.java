package bounce;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bounce {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(() -> {
			JFrame frame = new BounceFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

/**
 * The frame with ball component and buttons.
 *
 */
class BounceFrame extends JFrame {
	private BallComponent comp;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;

	/**
	 * Constructs the frame with the component for showing the bouncing ball and
	 * Start and Close buttons
	 */
	public BounceFrame() {
		setTitle("Bounce");
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", event -> addBall());
		addButton(buttonPanel, "Close", event -> System.exit(0));
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}

	/**
	 * Adds a button to a container
	 * 
	 * @param c
	 *            the container
	 * @param title
	 *            the button title
	 * @param listener
	 *            the action listener for the button
	 */
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}

	/**
	 * Adds a bouncing ball to the panel and makes it bounce 1000 times.
	 */
	public void addBall() {
		try {
			Ball ball = new Ball();
			comp.add(ball);
			for (int i = 0; i < STEPS; i++) {
				ball.move(comp.getBounds());
				comp.paint(comp.getGraphics());
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			
		}
	}
}
