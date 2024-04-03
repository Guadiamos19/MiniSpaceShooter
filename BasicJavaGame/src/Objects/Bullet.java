package Objects;

import Math.Vector2;

public class Bullet {

	
	protected Vector2 Position;
	protected float angleRotation;
	protected float velocityX, velocityY;
	protected GameObject object;
	
	public Bullet(Vector2 Position, float angleRotation, float velocityX, float velocityY, GameObject object){
		this.Position = Position;
		this.angleRotation = angleRotation;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.object = object;
	}
	
	
	public Vector2 getPosition(){
		return Position;
	}
	
	public float getAngleRotation(){
		return angleRotation;
	}
	

public GameObject getObject(){
	return object;
}	

public void tick(){
	//object.tick();
	

	object.x += velocityX;
	object.y += velocityY;
	object.CenterX += velocityX;
	object.CenterY += velocityY;
	
	object.shape.tick(velocityX, velocityY, object.getAngle());
	
    
}
	
	
	
}
