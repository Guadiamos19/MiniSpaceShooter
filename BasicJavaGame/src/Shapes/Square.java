package Shapes;

import java.util.Arrays;

import Math.MathUtil;
import Math.Vector2;

public class Square extends Shape{

	public Square(int width, int height, Vector2 Position, float angle,ShapeID ID){
		super(width, height, Position, angle,ID);
	}

	@Override
	public void init() {

		  points = new Vector2[4];

		    points[0] = MathUtil.RotatePointAroundPoint(new Vector2(Position.getX() - getWidth() / 2, Position.getY() - getHeight() / 2), Position, Angle);// Top Left
		    points[1] = MathUtil.RotatePointAroundPoint(new Vector2(Position.getX() - getWidth() / 2, Position.getY() + getHeight() / 2), Position, Angle);// Bot Left
		    points[2] = MathUtil.RotatePointAroundPoint(new Vector2(Position.getX() + getWidth() / 2, Position.getY() + getHeight() / 2), Position, Angle);// Bot Right
		    points[3] = MathUtil.RotatePointAroundPoint(new Vector2(Position.getX() + getWidth() / 2, Position.getY()  - getHeight()/ 2), Position, Angle);// Top Right
	}

	@Override
	public void tick(float velocityX, float velocityY, float angle) {
		
		Position.x += velocityX;
		Position.y += velocityY;
		
		
		for(int i = 0 ; i < points.length; i++){
			
			points[i].x += velocityX;
			points[i].y += velocityY;
			
		}
		
	  if(angle != prevAngle){
		 
			Arrays.fill(points, new Vector2(0,0));
		  
		  setAngle(angle);
		 

	
	
		    points[0] = MathUtil.RotatePointAroundPoint(new Vector2(Position.getX() - getWidth() / 2, Position.getY() - getHeight() / 2), Position, angle);// Top Left
		    points[1] = MathUtil.RotatePointAroundPoint(new Vector2(Position.getX() - getWidth() / 2, Position.getY() + getHeight() / 2), Position, angle);// Bot Left
		    points[2] = MathUtil.RotatePointAroundPoint(new Vector2(Position.getX() + getWidth() / 2, Position.getY() + getHeight() / 2), Position, angle);// Bot Right
		    points[3] = MathUtil.RotatePointAroundPoint(new Vector2(Position.getX() + getWidth() / 2, Position.getY() - getHeight() / 2), Position, angle);// Top Right
	
		    prevAngle = angle;
	  }
		
	}
	
	
	
}
