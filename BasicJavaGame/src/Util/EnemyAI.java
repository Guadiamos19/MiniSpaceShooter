package Util;

import java.util.Random;

import Application.Game;
import Math.Vector2;
import Objects.GameObject;
import Objects.ObjectID;
import Shapes.Circle;
import Shapes.ShapeID;
import Math.MathUtil;

public class EnemyAI {

	public static final float ENEMY_SPEED = 1.4f;
	public static final int MAX_ENEMIES = 10;
	public static Vector2[] ScreenPoints = new Vector2[4];
	
	public EnemyAI(){
		ScreenPoints[0] = new Vector2(0, 0);// TopLeft
		ScreenPoints[1] = new Vector2(0, Game.Height); // BottomLeft
		ScreenPoints[2] = new Vector2(Game.Width, Game.Height); // BottomRight
		ScreenPoints[3] = new Vector2(Game.Width , 0); // TopRight
	}
	
	

	
	public void MoveTowardsPlayer(GameObject enemy, Vector2 PlayerPos){
		
		float angle = Vector2.getAngleOfVector(new Vector2(enemy.getX(), enemy.getY()) , PlayerPos );
		
		enemy.setAngle(angle);
		
		float directionX = (float) ( Math.sin(angle));
		float directionY = (float) ( Math.cos(angle));

		enemy.velocityX = directionX *  -ENEMY_SPEED;
		enemy.velocityY =  directionY * ENEMY_SPEED;
		
	}
	
	public  GameObject RandomSpawn(GameObject enemy){
		
		Random random = new Random();
		
		float x = random.nextFloat() * Game.Width;
		float y = random.nextFloat() * Game.Height;
		
		Vector2 point = new Vector2(x,y);
		Vector2 anglePoint = new Vector2(0,0);
		
		
		float finalDistance = Float.MAX_VALUE;
		
		for(int i = 0 ; i < ScreenPoints.length; i++){
			
			   Vector2 v0 = ScreenPoints[i];
			  Vector2 v1 = ScreenPoints[(i + 1 == ScreenPoints.length) ? 0 : i + 1];
		

				float distance = MathUtil.LineToPointDistance(point, v0, v1);
				Vector2 P = MathUtil.ClosestPointOnLineToPoint(point, v0, v1);
				
				
				
		if(distance < finalDistance){
			finalDistance = distance;
			anglePoint = P;
		}
		
		}
	
		finalDistance += enemy.width;
		
		
		float angle = Vector2.getAngleOfVector(point,anglePoint);	

		
	float directionX = (float) ( Math.sin(angle));
	float directionY = (float) ( Math.cos(angle));
	
	float finalX = x + (directionX * (finalDistance));
	float finalY = y + (directionY * (finalDistance));
	
	

	
	//System.out.println(finalX  + "," + finalY);
	
	float radius = (float) Math.sqrt(((enemy.getWidth() / 2) * (enemy.getWidth() / 2)) + ((enemy.getHeight() / 2) * (enemy.getHeight() / 2)));
	
	Vector2 finalPoint = new Vector2(finalX,finalY);
	
	GameObject Enemy = new GameObject(finalX,finalY, enemy.getWidth(), enemy.getHeight(), ObjectID.ENEMY,enemy.getImage(), new Circle(enemy.getWidth(), enemy.getHeight(),radius, finalPoint , ShapeID.Circle));
	
	Enemy.x = finalX ;
	Enemy.CenterX = finalX  - enemy.getWidth() / 2;
	Enemy.y = finalY;
	Enemy.CenterY = finalY  - enemy.getHeight() / 2;
	
	
	return Enemy;
	
	}
	
	
	
	
	
}
