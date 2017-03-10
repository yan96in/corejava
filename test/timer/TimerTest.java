/**
 * 
 */
package timer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
// to resolve conflict with java .util.Timer

/**
 * @author yan96in
 *
 */
public class TimerTest {

	public static void main(String[] args) {
		ActionListener listener = new TimePrinter();
		// construct a time that calls the listener
		// once every 2 seconds
		Timer t = new Timer(2000, listener);
		t.start();

		JOptionPane.showInternalMessageDialog(null, "Quit program");
		//JOptionPane.showInputDialog(null, "Quit program");
		System.exit(0);
	}

}

class TimePrinter implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("At the tone, the time is " + new Date());
		Toolkit.getDefaultToolkit().beep();
	}

}