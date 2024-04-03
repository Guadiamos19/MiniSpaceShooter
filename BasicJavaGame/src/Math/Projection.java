package Math;

public class Projection {

	public float min, max;
	public float offset;
	
	public Projection(float min, float max){
		this.min = min;
		this.max = max;
	}

	public float getMin() {
		return min;
	}

	public float getMax() {
		return max;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public void setMax(float max) {
		this.max = max;
	}
	
	
	public void setOffset(float offset){
		this.offset = offset;
	}
	
	
}
