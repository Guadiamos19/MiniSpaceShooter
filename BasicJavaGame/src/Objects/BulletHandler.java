package Objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import Application.Game;
import Math.Vector2;
import Shapes.ShapeID;
import Shapes.Square;

public class BulletHandler {


	private static final float DISTANCEY = -40; /*Negative because of coordinate System*/
	private static final float DISTANCEX = 40;
	public static List<GameObject> bulletList = new ArrayList<GameObject>();
	public Game game;
//	public final int BULLET_SIZE = 32;
	
	public BulletHandler(Game game){
		this.game = game;
	}

	
	public void createBullet(float Angle , float x, float y){
	
		float directionX = (float) ( Math.sin(Angle));
		float directionY = (float) ( Math.cos(Angle));

		Vector2 Position = new Vector2( x  + directionX *  DISTANCEX, y + directionY * DISTANCEY);

		GameObject bullet = new GameObject(Position.getX(), Position.getY(),  game.texture.Entities[2].getWidth(),  game.texture.Entities[2].getHeight() ,ObjectID.Projectile, game.texture.Entities[2], new Square( game.texture.Entities[2].getWidth(),  game.texture.Entities[2].getHeight(), Position, Angle,ShapeID.Box),(directionX *  DISTANCEX) /2 , (directionY * DISTANCEY) / 2,Angle);
	
		bulletList.add(bullet);
	}
	
	
	public void tick(){
		
		for(int i = 0 ; i < bulletList.size(); i++){
			bulletList.get(i).tick();
			
	int pointer = 0;
			
			for(int j = 0 ; j < bulletList.get(i).shape.points.length; j++){
			     Vector2 Point = new Vector2(0,0);
			     Point.x =  bulletList.get(i).shape.points[j].getX();
			     Point.y =  bulletList.get(i).shape.points[j].getY();
			     
			     if(Point.x > Game.Width + 1 || Point.x < 0 || Point.y > Game.Height + 1 || Point.y < 0){
			    	 pointer++;
			     }
			}
			
			if(pointer >= 4){
				bulletList.remove(i);
			}
		}
	}
	
	
	/*Place and rotate bullet by position and rotation around or close to player*/
	public  void renderBullets(Graphics g){
	
		for(int i = 0 ; i < bulletList.size(); i++){
			
			GameObject bullet = bulletList.get(i);
			Graphics2D g2d = (Graphics2D) g;
			
		
			
			
		
	
			AffineTransform backUp = g2d.getTransform();
			AffineTransform at = new AffineTransform();
			
			backUp.setToIdentity();
			
	
	
			
			at.rotate(bullet.getAngle(), bullet.getX(), bullet.getY());

		
			g2d.setTransform(at);
		
			g2d.drawImage(bullet.getImage(),(int)bullet.getCenterX(), (int)bullet.getCenterY(), bullet.getWidth(),bullet.getHeight(), null);
		
		g2d.setTransform(backUp);
		
		  
	
		}
	}
	

	
	
}
