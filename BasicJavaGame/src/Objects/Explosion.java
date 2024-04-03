package Objects;

import Math.Vector2;

public class Explosion {

	
	public Animation a;
	public Vector2 v;

	
	public Explosion(Animation a , Vector2 v){
		this.a = a;
		this.v = v;
	}


	public Animation getA() {
		return a;
	}


	public Vector2 getV() {
		return v;
	}
	

	
	
	
}
