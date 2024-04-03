package Event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import Application.Game;
import Math.AABB;
import Math.MathUtil;
import Math.Vector2;
import Objects.GameObject;
import Util.QuadTree;

public class MouseEvents extends MouseAdapter implements MouseMotionListener {

	Game game;
	
	public MouseEvents(Game game){
		
		game.addMouseMotionListener(this);
		game.addMouseListener(this);
		this.game = game;
		
	}
//Create a MouseX and MouseY in Gameobject and use that to set the laserpoint position only once because you are setting it three times
	@Override
	public void mouseDragged(MouseEvent arg0) {
		  
		  int x = arg0.getX();
		  int y = arg0.getY();
			
		  
		  Game.MouseX = x;
		  Game.MouseY = y;
		  
		  GameState state = Game.getGamestate();
	  
			if(state == GameState.GAME){

	 
	  	 Vector2 v1 = new Vector2(x,y);

	  	 Vector2 v2 = new Vector2( game.getPlayerInstance().getX(), game.getPlayerInstance().getY());
	
		 
	  	
	 
	       float angle = Vector2.getAngleOfVector(v1,v2);
	       
	       game.getPlayerInstance().setAngle(angle);


	       
	
	   }

	

		 
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
  
		  int x = arg0.getX();
		  int y = arg0.getY();
			
		  
		  Game.MouseX = x;
		  Game.MouseY = y;
		  
		  GameState state = Game.getGamestate();
	  
			if(state == GameState.GAME){

	 
	  	 Vector2 v1 = new Vector2(x,y);
	  	 
	
	  	Vector2 v2 = new Vector2( game.getPlayerInstance().getX(), game.getPlayerInstance().getY());

	 
	       float angle = Vector2.getAngleOfVector(v1,v2);
	       
	       game.getPlayerInstance().setAngle(angle);

	  
	
	
	    
	   }

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		  int x = arg0.getX();
		  int y = arg0.getY();
		
		  
		  Game.MouseX = x;
		  Game.MouseY = y;
		  
		  GameState state = Game.getGamestate();
		  
		  
		  if(arg0.getButton() == MouseEvent.BUTTON1){
		
			  if(state == GameState.GAME){
			  
		  
	 
	
	    
			  
				
				 

				 
			  	 Vector2 v1 = new Vector2(x,y);
			 	 Vector2 v2 = new Vector2( game.getPlayerInstance().getX(), game.getPlayerInstance().getY());
				//Vector2 v2 = new Vector2( game.getPlayerInstance().getX(), game.getPlayerInstance().getY());
				 
			  	
		
			  	 
			       float angle = Vector2.getAngleOfVector(v1,v2);
			       game.getPlayerInstance().setAngle(angle);
			
			     
		

			           
			       game.getBulletHandler().createBullet(angle,  game.getPlayerInstance().getX(), game.getPlayerInstance().getY());

			     

				     
				       game.LeftButtonPressed = true;
				   
	           
	        }
			  else if(state == GameState.MENU){
				
		
					 if(game.menu.StartButton.IsMouseTouchingButton(x, y) == true){
						 
					 Game.gamestate = GameState.GAME;
					 
		
					 
					 }
			  }
		  }
}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		  if(arg0.getButton() == MouseEvent.BUTTON1){
	        	game.LeftButtonPressed = false;
	        }
		
	}
}

