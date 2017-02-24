package demo;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class ImageViewer {
	public static void main(String[] args){
		EventQueue.invokeLater(()->{
			JFrame frame=new ImageViewerFrame();
			frame.setTitle("hahah");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
		});
	}
}
