package Renderer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Application.Game;
import Math.Vector2;
import Objects.Animation;
import Objects.GameObject;
import Objects.ObjectManager;


public class MasterRenderer {

	public ObjectManager Manager;
     private Game game;
   
	
	
	public MasterRenderer(ObjectManager Manager, Game game){
		this.Manager = Manager;
		this.game = game;
	}
	

	
	
	public void renderObjects(Graphics g){
		
	
		List<GameObject> GameObjects = Manager.GameObjects;
		
	
		
		for(int i = 0 ; i < GameObjects.size(); i++){
			
	

				GameObject Player = GameObjects.get(i);
				

			Graphics2D g2d = (Graphics2D)g;
			AffineTransform backUp = g2d.getTransform();
			AffineTransform at = new AffineTransform();
			
			backUp.setToIdentity();
			at.rotate(Player.getAngle(), Player.getX(), Player.getY());

		
			g2d.setTransform(at);


	
			g2d.drawImage(Player.getImage(),(int)Player.getCenterX(), (int)Player.getCenterY(), Player.getWidth(), Player.getHeight(), null);
		
		g2d.setTransform(backUp);
			
		}

	
	    renderExplosions(g);
		game.getBulletHandler().renderBullets(g);

	}
	
	
	public void renderExplosions(Graphics g){
	
		 
		
		 
		 
	    for(int i = 0 ; i < game.explosions.size(); i++){
	    	
	    	

	    game.explosions.get(i).a.playAnimation(game.explosions.get(i).getV(), g);

	    	
        
        	
        	
	    		
	    	}
	
	
	    }
	    
	}
	
	

