package Event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Application.Game;

public class KeyboardEvents implements KeyListener {

	private Game game;
	
	public KeyboardEvents(Game game){
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e){
		game.keyPressed(e);
	}

	
public void keyReleased(KeyEvent e){
	game.keyReleased(e);
}

@Override
public void keyTyped(KeyEvent e) {

	
}


	
	
	
}
