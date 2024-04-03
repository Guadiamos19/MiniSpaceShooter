package Shapes;

import java.util.Arrays;

import Application.Game;
import Math.MathUtil;
import Math.Vector2;

public class Triangle extends Shape {
	


	
	public Triangle(int width, int height, Vector2 Position,float angle, ShapeID ID){
		super(width, height, Position, angle, ID);
	}

	@Override
	public void init() {
		
		 points = new Vector2[3];
	      
	      points[0] = new Vector2(Position.getX() - getWidth() / 2, Position.getY() + getHeight() / 2);
	      points[1] = new Vector2(Position.getX() + getWidth() / 2, Position.getY() + getHeight() / 2);
	      points[2] = new Vector2(Position.getX(), Position.getY() - getHeight() / 2);
	  
	      
	
	
	}

	@Override
	public void tick(float velocityX, float velocityY, float angle) {
	
		Position.x += velocityX;
		Position.y += velocityY;
		
		

		
		
		System.out.println(points[0].x + " , " + points[0].y );
		
	/*	for(int i = 0 ; i < points.length; i++){
			
			points[i].x += velocityX;
			points[i].y += velocityY;
			
		}
		

		
	  if(angle != prevAngle){*/
		 		  
			Arrays.fill(points, new Vector2(0,0));
		  
		    points[0] = new Vector2(Position.getX() - getWidth() / 2, Position.getY() + getHeight() / 2);
		      points[1] = new Vector2(Position.getX() + getWidth() / 2, Position.getY() + getHeight() / 2);
		      points[2] = new Vector2(Position.getX(), Position.getY() - getHeight() / 2);
			
	
	      
	    
	      

		      Vector2 v = new Vector2((points[0].getX() + points[1].getX() + points[2].getX()) / 3, (points[0].getY() + points[1].getY() + points[2].getY()) / 3);
	 		 
	
	
			  setAngle(angle);
			  
			  

				for(int i = 0 ; i < points.length; i++){
					
					
					Vector2 v1 = MathUtil.RotatePointAroundPoint(points[i],    v,(float) angle );
				    
					points[i].x = v1.getX();
					points[i].y = v1.getY();
					
				}

		
		   
	 
		    prevAngle = angle;
	//  }
	  

	}
}