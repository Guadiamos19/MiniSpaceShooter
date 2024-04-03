package Shapes;

import Math.Vector2;

public class Circle extends Shape {


	
	public Circle(int width , int height, float radius, Vector2 Pos, ShapeID ID){
		super(width, height, radius, Pos, ID);
	}

	@Override
	public void init() {
 
		
	}

	@Override
	public void tick(float velocityX, float velocityY, float angle) {
	
	
		
		Position.x += velocityX;
		Position.y += velocityY;
	}
	
	

}
