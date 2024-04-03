package Objects;

import java.util.ArrayList;
import java.util.List;

public class ObjectManager {
	
  public List<GameObject> GameObjects = new ArrayList<GameObject>();
  public int numberOfEnemies = 0;

	public void addGameObject(GameObject object){
		GameObjects.add(object);
	}
	
	public void removeGameObject(GameObject object){
		GameObjects.remove(object);
	}
	
	public void NumberOfEnemies(){
	   int pointer = 0;
	   
	
	   
		for(int i = 0 ; i < GameObjects.size(); i++){
	
			if(GameObjects.get(i).getID() == ObjectID.ENEMY){
		
				pointer++;
			}
		}
		
    numberOfEnemies = pointer;
		
	}

	public int getNumberOfEnemies() {
		return numberOfEnemies;
	}
	
	
}
