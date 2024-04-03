package Math;


import Shapes.Shape;

public class SATcollision {

	

	
	public static boolean CircleCircleCollision(Shape s1, Shape s2){
		
		float xDiff = s1.getPosition().getX() - s2.getPosition().getX();
		float yDiff = s1.getPosition().getY() - s2.getPosition().getY();
		float distance = xDiff * xDiff + yDiff * yDiff;
		return distance < (s1.getRadius() + s2.getRadius()) * (s1.getRadius() * s2.getRadius());
	}
	

	
	public static boolean SpaceShipCollision(Shape s1, Shape s2){
		
		boolean BoxTest = AABB.IsColliding(s1.getBox(), s2.getBox());
		
		if(BoxTest){
		
			boolean PreciseCollision = PolygonCircleCollision(s1,s2);
			
			if(PreciseCollision){
				
				return true;
			}
			
			else{
				return false;
			}
			
		}
		
		else{
			return false;
		}
		
	}
	
	
	
	
public static boolean PolygonCircleCollision(Shape s1, Shape s2){
	


	 
	  for(int j = 0; j < s1.points.length; j++){
		  
		
		   
	     Vector2 v0 = s1.points[j];
	    
	     
	     Vector2 v1 = s1.points[(j + 1 == s1.points.length) ? 0 : j + 1];
	     
	     
	
	   
	     
    boolean c = lineCircle(v0.getX(),v0.getY(),v1.getX(),v1.getY(),s2.getPosition().getX(),s2.getPosition().getY(), s2.getRadius());
    
    if(c){
    	
    	return true;
    }
    
    }
	
   

	    boolean centerInside = polygonPoint(s1.points, s2.getPosition().getX(),s2.getPosition().getY());
	    
	     if (centerInside) {
	    	 return true;
	    	 }
	     
	

 
	  

	
			  
			
    return false;
		}


public static boolean PointToCircle(Vector2 [] p ,Vector2 center ,float radius){
	
	int pointer = 0;
	
	for(int i = 0 ; i < p.length; i++){
		
		Vector2 v = new Vector2(0,0);
		v.x = p[i].getX();
		v.y = p[i].getY();
		
		float distance = (v.getX() - center.getX()) * (v.getX() - center.getX()) + (v.getY() - center.getY()) + (v.getY() - center.getY()) ;
		
		if(distance <= radius * radius){
	  		pointer++;
		}
	}
	
	if(pointer > 0){
		return true;
	}
	
	return false;
}


	
public static boolean polygonPoint(Vector2[] a, float px, float py){
	
boolean collision = false;

	  for(int j = 0; j < a.length; j++){
		  

		   
	     Vector2 vc = a[j];
	    
	     
	     Vector2 vn = a[(j + 1 == a.length) ? 0 : j + 1];
	     
	     
	     if (((vc.y > py && vn.y < py) || (vc.y < py && vn.y > py)) &&
	             (px < (vn.getX()-vc.getX())*(py-vc.getY()) / (vn.getY()-vc.getY())+vc.getX())) {
	                collision = !collision;
	        }
	      }
	      return collision;
	     
}	



public static boolean lineCircle(float x1, float y1, float x2, float y2, float cx, float cy, float r){
	
	  boolean inside1 = pointCircle(x1,y1, cx,cy,r);
	  boolean inside2 = pointCircle(x2,y2, cx,cy,r);
	  if (inside1 || inside2) return true;

	  // get length of the line
	  float distX = x1 - x2;
	  float distY = y1 - y2;
	  float len = (float) Math.sqrt( (distX*distX) + (distY*distY) );

	  // get dot product of the line and circle
	  float dot = (float) (( ((cx-x1)*(x2-x1)) + ((cy-y1)*(y2-y1)) ) / Math.pow(len,2));

	  // find the closest point on the line
	  float closestX = x1 + (dot * (x2-x1));
	  float closestY = y1 + (dot * (y2-y1));

	  // is this point actually on the line segment?
	  // if so keep going, but if not, return false
	  boolean onSegment = linePoint(x1,y1,x2,y2, closestX,closestY);
 
	  if (!onSegment) return false;
	  
	  distX = closestX - cx;
	  distY = closestY - cy;
	  float distance = (float) Math.sqrt(( (distX*distX) + (distY*distY)));

	  // is the circle on the line?
	  if (distance <= r) {
	    return true;
	  }
	  return false;
	  
	  
}

public static boolean linePoint(float x1, float y1, float x2, float y2, float px, float py) {

	  // get distance from the point to the two ends of the line
	  float d1 = Vector2.getDistance1(new Vector2(px,py), new Vector2(x1,y1));
	  float d2 = Vector2.getDistance1(new Vector2(px,py), new Vector2(x2,y2));

	  // get the length of the line
	  float lineLen = Vector2.getDistance1(new Vector2(x1,y1), new Vector2(x2,y2));

	  // since floats are so minutely accurate, add
	  // a little buffer zone that will give collision
	     // higher # = less accurate

	  // if the two distances are equal to the line's
	  // length, the point is on the line!
	  // note we use the buffer here to give a range, rather
	  // than one #
	  
	  
	  if (d1+d2 >= lineLen - 0.1f && d1+d2 <= lineLen + 0.1f) {
		    return true;
		  }
		  return false;
}


public static boolean pointCircle(float px, float py, float cx, float cy, float r) {
	  
	  // get distance between the point and circle's center
	  // using the Pythagorean Theorem
	  float distX = px - cx;
	  float distY = py - cy;
	  float distance = (float) Math.sqrt(( (distX*distX) + (distY*distY)) );

	  // if the distance is less than the circle's 
	  // radius the point is inside!
	  if (distance <= r) {
	    return true;
	  }
	  return false;
	}



public static void projectCircle(Vector2 N, Shape s1 ,Shape s2) {
	
	float dot = s2.getPosition().dot(N);
	
/*List<Vector2> l = new ArrayList<Vector2>();

	float min = s2.getPosition().dot(N);
	float max = min;
	

	 
	 Vector2 P1 = new Vector2(s2.getPosition().getX() + (N.getX() * s2.getRadius()) , s2.getPosition().getY() + (N.getY() * s2.getRadius()));
	 Vector2 P2 = new Vector2( s2.getPosition().getX() + (-N.getX() * s2.getRadius()) ,  s2.getPosition().getY() + (-N.getY() * s2.getRadius()));
	 
    l.add(P1);
    l.add(P2);
		 

		 
for(int i = 0 ; i < l.size(); i++){
	float d = l.get(i).dot(N);
	if(d < min){
		min = d;
	}
	else if(d > max){
		max = d;
	}

		 
	
	 }*/

//s2.projection = new Projection(min,max);

	
	s2.projection = new Projection(dot - s2.getRadius(), dot + s2.getRadius());
}
	
	
	
		

		
	
	
	public static boolean PolygonCollision(Shape s1, Shape s2){

	
		
		  for(int j = 0; j < s1.points.length; j++){
			   Vector2 edge = new Vector2(0,0);
			   
		     Vector2 v0 = s1.points[j];
		    
		     
		     Vector2 v1 = s1.points[(j + 1 == s1.points.length) ? 0 : j + 1];
		     
		   
		     edge.x = v1.getX() - v0.getX();
		     edge.y = v1.getY() - v0.getY();
		      
		      Vector2 axis = edge.perpendicularVector(); // Separate axis is perpendicular to the edge
		   
		     axis.NormalizeVector();

		     project(axis,s1);
		 	project(axis, s2);
		 	
			  if(intervalsSeparated(s1.projection.min, s1.projection.max, s2.projection.min, s2.projection.max)) {
			       return false;
			       
			       }
		 
		   }
		  

		  for(int j1 = 0; j1 < s2.points.length; j1++){
			   Vector2 edge = new Vector2(0,0);
		  
		  Vector2 v0 = s2.points[j1];

		  
		  Vector2 v1 = s2.points[(j1 + 1 == s2.points.length) ? 0 : j1 + 1];

		  
		   edge.x = v1.getX() - v0.getX();
		  edge.y = v1.getY() - v0.getY();
		   
		   Vector2 axis = edge.perpendicularVector(); // Separate axis is perpendicular to the edge

		   axis.NormalizeVector();
		   
		  project(axis,s1);
		project(axis, s2);
		
		  if(intervalsSeparated(s1.projection.min, s1.projection.max, s2.projection.min, s2.projection.max)) {
		       return false;
		       
		       }
		 
			}
		  

		  
		return true;
			}
	



public static void project(Vector2 axis, Shape s) {
  
   float min = s.getPosition().dot(axis);
   float max = min;

  
 
   for (int i = 0; i < s.points.length; i++) {
	   float d = s.points[i].dot(axis);

      if (d < min){
         min = d;

      }
      else if (d > max){
         max = d;
      }
   }
   
   s.projection = new Projection(min,max);
}


public static Vector2 FindClosestPoint(Shape s1 , Shape s2) {


	float dist = Float.MAX_VALUE;
	
	Vector2 c = new Vector2(0,0);
	
	
	
	for(int i = 0; i < s1.points.length; i++){

		
		float currentDistance = Vector2.getDistance1(s1.points[i], s2.getPosition());
		
		if(currentDistance < dist){
			
			dist = currentDistance;
			c.x  = s1.points[i].getX();
			c.y  = s1.points[i].getY();
		}
		
	}

return c;
	}

public static boolean intervalsSeparated(float mina, float maxa, float minb, float maxb) {
	//System.out.println(mina + " "+ maxa + " " + minb + " " + maxb);
   return (mina > maxb) || (minb > maxa);
}


public static float lineOverlap(float mina, float maxa, float minb, float maxb) {
	  return Math.max(0, Math.min(maxa, maxb) - Math.max(mina, minb));

}
}
