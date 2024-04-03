package Math;

public class Vector2 {

	public float x,y;
	

	public Vector2(float x, float y){
		this.x = x;
		this.y= y;
	}
	
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	
	public void NormalizeVector(){
		float l = getLength1(this);
		if(l > 0){
		x /= l;
		y /= l;
		}
		
	}

	
	public static Vector2 scaleVector(Vector2 p , float scaler){
		return new Vector2(p.getX() * scaler, p.getY() * scaler);
	}
	
	public float getLengthSquared(){
		return this.getX() * this.getX() + this.getY() * this.getY();
	}
	

	public float getLength(){
	
		float l = (float) Math.sqrt(this.x * this.x + this.y + this.y); 
		return l;
		
	}
	public static float getLength1(Vector2 v){
		
		float l = (float) Math.sqrt(v.x * v.x + v.y + v.y); 
		return l;
		
	}
	
	public static float getDistance1(Vector2 v1, Vector2 v2){
		return (float) Math.sqrt(Math.pow((v2.y - v1.y),2) + Math.pow((v2.x - v1.x),2));
	}


	public float getDistance(Vector2 v1, Vector2 v2){
		
		return (float) Math.sqrt(Math.pow((v2.y - v1.y),2) + Math.pow((v2.x - v1.x),2));
	}
	

	
	public static float getAngleOfVector(Vector2 p1, Vector2 p2){
		float yDiff = p2.y - p1.y;
		float xDiff = p2.x - p1.x;
		

	float angle = (float) (Math.atan2(yDiff,xDiff) - Math.PI / 2);
	

		return angle;
	}
	
	public float dot(Vector2 v){
		return this.x * v.x + this.y + v.y;
	}
	
	public Vector2 perpendicularVector(){
		return new Vector2(this.getY(), -this.getX());
	}
	
}
