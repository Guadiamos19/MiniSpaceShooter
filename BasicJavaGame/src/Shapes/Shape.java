package Shapes;

import Math.AABB;
import Math.Projection;
import Math.Vector2;

public abstract class Shape {

	public float radius;
	public Vector2 Position;
	public ShapeID ID;
	public int width, height;
	public float prevAngle ,Angle = 0.0f;
	public Vector2[] points;
	public Projection projection;
	public AABB box;
	
	
	public Shape(int width, int height,float radius, Vector2 Position, ShapeID ID){
		
		box = new AABB(Position.getX() - width / 2 , Position.getY() - height / 2, width, height);
		
		this.radius = radius;
		this.Position = Position;
		this.ID = ID;
		init();
	}

	
	public Shape(int width, int height, Vector2 Position,float angle ,ShapeID ID){
	
		box = new AABB(Position.getX() - width / 2 , Position.getY() - height / 2, width, height);
		
		this.width = width;
		this.height = height;
		this.Position = Position;
		this.Angle = angle;
		this.ID = ID;
		 init();
		
	}

   public abstract void init();

  
   
   
	public float getAngle() {
	return Angle;
}


	

public AABB getBox() {
		return box;
	}


public void setAngle(float angle) {
	this.Angle = angle;
}


	public float getRadius() {
		return radius;
	}


	public Vector2 getPosition() {
		return Position;
	}


	public ShapeID getID() {
		return ID;
	}


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	public Projection getProjection() {
		return projection;
	}


	public void setProjection(Projection projection) {
		this.projection = projection;
	}


	public void tick(float velocityX, float velocityY, float angle){};
	
	
}
