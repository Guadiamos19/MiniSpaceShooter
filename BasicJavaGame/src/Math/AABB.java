package Math;

public class AABB {

    
	public float xMax, yMax;
	public float xMin, yMin;
	public float centerX, centerY;
	public float width, height;


	

	
		public AABB(float x, float y, float width, float height){
	
		this.xMin = x;
		this.xMax = x + width;
		this.yMin = y + height;
		this.yMax = y;
		this.centerX = x + width / 2;
		this.centerY = y - height / 2;
	    this.width = width;
	    this.height = height;
	}

	
  public static boolean IsColliding(AABB box1, AABB box2){
	  
	  return !(box1.getXMax() < box2.getXMin() ||
		         box1.getYMax() > box2.getYMin() ||
		         box1.getXMin() > box2.getXMax() ||
		         box1.getYMin() < box2.getYMax());
		        
  }
  
  
  public void Update(float velocityX, float velocityY){

		this.xMin += velocityX;
		this.xMax += velocityX;
		this.yMin += velocityY;
		this.yMax += velocityY;
		this.centerX += velocityX;
		this.centerY += velocityY;
	  
	  
  }
  
	
	public boolean IsMouseTouchingButton(float x, float y){
		
		boolean X = x > xMin && x < xMax;
		boolean Y = y < yMin && y > yMax;
		
		if(X && Y){
			return true;
		}
		
		return false;
	}
	

	public float getXMax() {
		return xMax;
	}

	public float getYMax() {
		return yMax;
	}

	public float getXMin() {
		return xMin;
	}

	public float getYMin() {
		return yMin;
	}

	public float getCenterX() {
		return centerX;
	}

	public float getCenterY() {
		return centerY;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	
	
}
