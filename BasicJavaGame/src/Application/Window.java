package Application;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import Event.KeyboardEvents;
import Event.MouseEvents;

public class Window extends Canvas {
	
	
	private static final long serialVersionUID = -8838254880476296186L;
	/**
	 * 
	 */

     public JFrame frameWork;

	public Window(String Name, int width, int height, Game game){
	
		 frameWork = new JFrame(Name);
		frameWork.setPreferredSize(new Dimension(width,height));
		frameWork.setMaximumSize(new Dimension(width, height));
		frameWork.setMinimumSize(new Dimension(width,height));
		frameWork.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameWork.setResizable(false);
		frameWork.setLocationRelativeTo(null);
		frameWork.add(game);
	
		frameWork.setVisible(true);
		game.start();
		
		
	}
	
	public JFrame getFrameWork(){
		return frameWork;
	}

}
