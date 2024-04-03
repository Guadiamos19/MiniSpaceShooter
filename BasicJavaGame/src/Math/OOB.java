package Math;

import Shapes.Shape;

public class OOB {

	
	public float x, y;
	public float xMax, yMax;
	public float xMin, yMin;
	public float centerX, centerY;
	public int width, height;
	public Vector2[] points = new Vector2[5];

	public Shape shape;
	public float Angle;
	
	public Projection projection;
	
	
	public OOB(float x, float y, int width, int height, float Angle, Shape shape){
		
		this.x = x;
		this.y = /*Game.Height -*/ y;
		this.xMin = x - width / 2;
		this.xMax = x + width / 2;
		this.yMin = /*Game.Height - */(y + height / 2);
		this.yMax =/* Game.Height -*/ (y - height / 2);
		this.centerX = x /*+ width / 2*/;
		this.centerY = /*Game.Height -*/ (y /*+ height / 2*/ );
	    this.width = width;
	    this.height = height;
	    this.shape = shape;
	    createPoints();
	
	}



	public void createPoints(){
		points[0] = new Vector2(centerX,centerY); //center
	  /*  points[1] = MathUtil.RotatePointAroundPoint(new Vector2(xMin, yMax), new Vector2(centerX,centerY), Angle);// Top Left
	    points[2] = MathUtil.RotatePointAroundPoint(new Vector2(xMin, yMin), new Vector2(centerX,centerY), Angle);// Bot Left
	    points[3] = MathUtil.RotatePointAroundPoint(new Vector2(xMax, yMin), new Vector2(centerX,centerY), Angle);// Bot Right
	    points[4] = MathUtil.RotatePointAroundPoint(new Vector2(xMax, yMax), new Vector2(centerX,centerY), Angle);// Top Right*/
	

	}

	public Shape getShape() {
		return shape;
	}



	public float getxMax() {
		return xMax;
	}


	public float getyMax() {
		return yMax;
	}


	public float getxMin() {
		return xMin;
	}


	public float getyMin() {
		return yMin;
	}


	public float getCenterX() {
		return centerX;
	}


	public float getCenterY() {
		return centerY;
	}


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	public Vector2[] getPoints() {
		return points;
	}


	public float getAngle() {
		return Angle;
	}
	
	public void setAngle(float Angle){
		this.Angle = Angle;
	}
	
	
	
	
}
