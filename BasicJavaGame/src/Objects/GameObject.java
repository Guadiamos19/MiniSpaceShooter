package Objects;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import Application.Game;
import Math.AABB;
import Math.MathUtil;
import Math.OOB;
import Math.Vector2;
import Shapes.Shape;
import Shapes.Triangle;

public class GameObject { 
	
	
	public float x,y;
	public int width,height;
	public ObjectID ID;
	public BufferedImage image;
	public float velocityX,velocityY;
	public float CenterX, CenterY;
	public float prevAngle,Angle = 0.0f;
 

	public Shape shape;

	


	
	
	public GameObject(float	x, float y, int width, int height, ObjectID ID, BufferedImage image, Shape shape) {
	
		this.x = x ;
		this.y = y ;
        this.CenterX = x - width / 2;
        this.CenterY = y - height / 2;
		this.width = width;
		this.height = height;
		this.ID = ID;
		this.image = image;
    	this.shape = shape;


	}
	
	public GameObject(float x, float y,  int width, int height,  ObjectID ID, BufferedImage image, Shape shape,float velocityX, float velocityY, float angleRotation){
		this.x = x ;
		this.y = y ;
	    this.CenterX = x - width / 2;
	    this.CenterY = y - height / 2;
	    this.width = width;
		this.height = height;
		this.Angle = angleRotation;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.ID = ID;
		this.image = image;
    	this.shape = shape;
	}
	
	
	public void tick(){
		
		x+= velocityX;
		y+= velocityY;
		
		CenterX+= velocityX;
		CenterY+= velocityY;
		
		

		shape.tick(velocityX, velocityY, Angle);
	
		shape.box.Update(velocityX, velocityY);
	       
	       
	}
	
	public void KeepInBounds(){
		if (x - width < 0) {
	        x = width;
	        shape.Position.x = x;
	        
	       shape.box.centerX = x;
	       shape.box.xMin = x - width /2;
	       shape.box.xMax = x + width / 2;
	       
	        CenterX = x - width / 2;
	        
	    } else if (x + width > Game.Width) {
	        x = Game.Width - width;
	       
	        shape.Position.x = x;
	        
	        shape.box.centerX = x;
		       shape.box.xMin = x - width /2;
		      shape.box.xMax = x + width / 2;
	        
	        CenterX = x - width / 2;
	    }
	    if (y - height < 0) {
	        y = height;
	       shape.Position.y = y;
	        
	        shape.box.centerY = y;
		       shape.box.yMin = y + height /2;
		       shape.box.yMax = y - height / 2;
	        
	        CenterY = y - height / 2;
	        
	    } else if (y + height > Game.Height) {
	        y = Game.Height - height;
	       shape.Position.y = y;
	        
	        shape.box.centerY = y;
		       shape.box.yMin = y + height /2;
		       shape.box.yMax = y - height / 2;
	        
	        
	        CenterY = y - height / 2;
	    }
	    
	   
	

	}
	


	public void setAngle(float Angle){
		this.Angle = Angle;
	}
	public float getAngle(){
		return Angle;
	}




	public Shape getShape() {
		return shape;
	}




	public float getX() {
		return x;
	}
	

	public float getCenterX(){
		return CenterX;
	}
	public float getCenterY(){
		return CenterY;
	}
	public float getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public ObjectID getID() {
		return ID;
	}
	public BufferedImage getImage(){
		return image;
	}
	public float getVelocityX() {
		return velocityX;
	}
	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}
	public float getVelocityY() {
		return velocityY;
	}
	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}

	/*public OOB getBox(){
		return box;
	}
	*/
}
