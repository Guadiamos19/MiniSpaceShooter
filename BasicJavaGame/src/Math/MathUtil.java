package Math;

public class MathUtil {


	/*public static void RotatePointAroundPoint(Vector2 point, Vector2 center, float angle){
		
		double x1 = point.x - center.x;
		double y1 = point.y - center.y;

		double x2 = x1 * Math.cos(angle) - y1 * Math.sin(angle);
		double y2 = x1 * Math.sin(angle) + y1 * Math.cos(angle);
	
		
		point.x = (float) (x2 + center.x);
		point.y = (float) (y2 + center.y);
		
		
	}
	*/

	public static Vector2 RotatePointAroundPoint(Vector2 point, Vector2 center, float angle){
		
		double x1 = point.x - center.x;
		double y1 = point.y - center.y;

		double x2 = x1 * Math.cos(angle) - y1 * Math.sin(angle);
		double y2 = x1 * Math.sin(angle) + y1 * Math.cos(angle);
	
		
		point.x = (float) (x2 + center.x);
		point.y = (float) (y2 + center.y);
		
		return point;
	}
	
	public static float PointDistanceFromLine(Vector2 point, Vector2 l1, Vector2 l2){
		
        return (float) (Math.abs((l2.x - l1.x)*(l1.x - point.y) - (l1.x - point.x)*(l2.y - l1.y))/
                Math.sqrt(Math.pow(l2.x - l1.x, 2) + Math.pow(l2.y - l1.y, 2)));
	}
	
	
	public static float LineToPointDistance(Vector2 point, Vector2 l1, Vector2 l2){
		 Vector2 AB = new Vector2(l2.x - l1.x, l2.y - l2.y);
		 Vector2 PT1 = new Vector2(point.x - l1.x, point.y - l1.y);
		 Vector2 PT2 = new Vector2(point.x - l2.x, point.y - l2.y);
		 
		 float t = PT1.dot(AB) / (AB.x * AB.x + AB.y * AB.y);
		 
		 if(t < 0){
			 return (float) Math.sqrt(PT1.x * PT1.x + PT1.y * PT1.y);
		 }
		 
		 else if(t > 1){
			return (float) Math.sqrt(PT2.x * PT2.x + PT2.y * PT2.y);
		 }
		 else{
			 Vector2 closest = new Vector2(l1.x + t * AB.x, l1.y + t * AB.y);
			 
		float dx = point.x - closest.x;
		float dy = point.y - closest.y;
		
		return (float) Math.sqrt(dx * dx + dy * dy);
		
		 }
	/*	Vector2 AC =  new Vector2(point.x - l1.x, point.y - l1.y);
		Vector2 AB = new Vector2(l2.x - l1.x, l2.y - l1.y);
		Vector2 perpAB = AB.perpendicularVector();
		
		float dot = AC.dot(perpAB);
		
		float distance = dot / perpAB.getLength();
		
		return distance;*/
		
	}
	
	
	public static Vector2 ClosestPointOnLineToPoint(Vector2 point, Vector2 l1, Vector2 l2){
		 Vector2 AB = new Vector2(l2.x - l1.x, l2.y - l2.y);
		 Vector2 PT1 = new Vector2(point.x - l1.x, point.y - l1.y);
		 
		 float t = PT1.dot(AB) / (AB.x * AB.x + AB.y * AB.y);
		 
		 if(t < 0){
			 return l1;
		 }
		 
		 else if(t > 1){
			 return l2;
		 }
		 else{
			 Vector2 closest = new Vector2(l1.x + t * AB.x, l1.y + t * AB.y);
			 return closest;
		 }

		
	}
	
	/*	Vector2 AC =  new Vector2(point.x - l1.x, point.y - l1.y);
		Vector2 AB = new Vector2(l2.x - l1.x, l2.y - l1.y);
		Vector2 perpAB = AB.perpendicularVector();
		
		float dot = AC.dot(perpAB);
		
		float distance = dot / perpAB.getLength();
		
		return new Vector2(l1.x + (distance * (dot * (l2.x - l1.x))), l1.y + (distance * (dot * (l2.y - l1.y))));*/

}
