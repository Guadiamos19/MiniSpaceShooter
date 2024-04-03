package Application;

import java.awt.Canvas;
import java.awt.Color;import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import Event.GameState;
import Event.KeyboardEvents;
import Event.MouseEvents;
import Objects.Animation;
import Objects.BulletHandler;
import Objects.GameObject;
import Objects.ObjectID;
import Objects.ObjectManager;

import Renderer.MasterRenderer;
import Shapes.Circle;
import Shapes.ShapeID;
import Shapes.Triangle;
import Texture.BufferedImageLoader;
import Texture.Texture;
import Texture.TextureAtlas;
import Util.EnemyAI;
import Util.QuadTree;
import Math.SATcollision;
import Math.Vector2;
import Objects.Explosion;

public class Game extends Canvas implements Runnable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7747923588020656655L;
	private Thread thread;
	private boolean running = false;
	public static final int Width = 1280;
	public static final int Height = 720;
	
	public static final int FPS_CAP = 60;
	protected MasterRenderer renderer;
	private Random random = new Random();
	
	public TextureAtlas texture ;
    public Texture Background;
	
	public GameObject Player;
	private boolean MoveRight = false;
	private boolean MoveLeft = false;
	private boolean MoveUp = false;
	private boolean MoveDown = false;
	public boolean LeftButtonPressed = false;
	public static GameState gamestate;
	public Window window;
	public BulletHandler bHandler;
	public Menu menu;
	public static float MouseX = Game.Width / 2;
	public static float MouseY = Game.Height / 3;
    public QuadTree q;
    public ObjectManager manager;
    public List<GameObject> objects = new ArrayList<GameObject>();
    public List<Explosion> explosions = new ArrayList<Explosion>();
    
   public Canvas canvas = new Canvas();
   public Font Text =  new Font("arial", Font.BOLD, 35);
   public Font ScoreNumber = new Font("arial", Font.BOLD, 35);
    
    public BufferedImage Explosion;
   // public Animation ExplosionEff;
    public BufferedImageLoader ImageLoader = new BufferedImageLoader();
    public GameObject BasicEnemy;
    public EnemyAI ai = new EnemyAI();
    public int BaseScore = 0;
    public float time = 0;

    public long LAST;
    public long CURRENT;

    
  

	
	public Game(){
		
		 this.requestFocus();

		
		window = new Window("JavaGame", Width, Height, this);

	}
	
	
	@Override
	public void run() {
	
        init();
        long lastTime = System.nanoTime();
       /**/ LAST = System.nanoTime();
  
        
        
        
        
        double amountOfTicks = FPS_CAP;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
   	               
                    long now = System.nanoTime();
                  /**/  CURRENT = System.nanoTime();
                   
                   
               
                   
                    delta += (now - lastTime) / ns;
               
                    lastTime = now;
                    
                  time =  (float) ((CURRENT - LAST) / 1000000000.0) ;
                 
                
           
                    while(delta >=1)
                            {
                    	 
                             delta--;
                             tick();
                            }
                            if(running)
                                render();
                            frames++;
                        
                            if(System.currentTimeMillis() - timer > 1000)
                            {
                                timer += 1000;
                            
                                
                              
                           //    System.out.println("FPS: "+ frames);
                                
                                frames = 0;
                            }
        }
                stop();
		
	}
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}
		catch(Exception e){
			
		}
	}
	public void tick(){
		
		while(gamestate == GameState.GAMEOVER ){
			for(int j = 0 ; j < manager.GameObjects.size(); j++){
				manager.GameObjects.get(j).velocityX = 0;
				manager.GameObjects.get(j).velocityY = 0;
			}
		}
		
		if(gamestate == GameState.GAME){
			   
		if(MoveLeft){
			MovePlayerLeft();
		}
	
		if(MoveRight){
			MovePlayerRight();
		}
		
		if(MoveUp){
			MovePlayerUp();
		}
		
		if(MoveDown){
			MovePlayerDown();
		}

	
		//Player.tick();
		Player.KeepInBounds();
		 bHandler.tick();
		 
	
		  for(int x = 0 ; x < explosions.size(); x++){
			  explosions.get(x).a.runAnimation();
			  if(explosions.get(x).getA().isFinished()){
				  explosions.remove(x);
			  }
		  }
		
		  manager.NumberOfEnemies();
		  
	

		  
		  if(time > 0.5f  && manager.getNumberOfEnemies() < EnemyAI.MAX_ENEMIES){
			  
				GameObject ENEMY = ai.RandomSpawn(BasicEnemy);
				   manager.addGameObject(ENEMY);
				
			
				time = 0;
				LAST = CURRENT;
			}
		  
		  else if (time > 0.5f){
			  time = 0;
			  LAST = CURRENT;
		  }
	
			
		
	
			for(int j = 0 ; j < manager.GameObjects.size(); j++){
				 manager.GameObjects.get(j).tick();
				
				if(manager.GameObjects.get(j).getID() == ObjectID.ENEMY){

				objects.add(manager.GameObjects.get(j));
				}
				
			
			
				
			}
		
		
			  
			  
			for(int i = 0 ; i < BulletHandler.bulletList.size(); i++){
				
				objects.add(BulletHandler.bulletList.get(i));
		
			}
			for (int i = 0; i < objects.size(); i++) {
				  q.insert(objects.get(i));
				}
		
			
		
			
			List<GameObject> returnObjects = new ArrayList<GameObject>();
			
			for(int j = 0 ;  j < objects.size(); j++){
			returnObjects.clear();
          	q.retrieve(returnObjects, objects.get(j));

		

			
				for( int q = 0 ; q < returnObjects.size(); q++){
					for( int w = q + 1; w < returnObjects.size(); w++){
						
				
					if(objects.contains(returnObjects.get(q)) && objects.contains(returnObjects.get(w))){
						
						if(returnObjects.get(q).getID() == ObjectID.ENEMY && returnObjects.get(w).getID() == ObjectID.Projectile){
							boolean collision = SATcollision.SpaceShipCollision(returnObjects.get(w).shape, returnObjects.get(q).shape);
							if(collision){
							
				
						
								manager.GameObjects.remove(returnObjects.get(q));
								BulletHandler.bulletList.remove(returnObjects.get(w));
						
								objects.remove(returnObjects.get(q));
								objects.remove(returnObjects.get(w));
								
								
							
							Vector2 v = new Vector2(returnObjects.get(q).CenterX , returnObjects.get(q).CenterY);
							
							 if(explosions.size() != 0 ){
								 int pointer = 0 ; 
					    for(int i = 0 ; i < explosions.size(); i++){
						
					    	if(areDifferent(explosions.get(i).getV(), v)){
								pointer++;
							 }
					    }
					    if(pointer == explosions.size()){
					    	Explosion e = new Explosion(new Animation(Explosion, 9 , 2 , 5),v);
					    	 e.a.start = true;
					    	explosions.add(e);
					    }
					    }
							 else{
								 Explosion e = new Explosion(new Animation(Explosion, 9 , 2 , 5),v);
								 e.a.start = true;
								 explosions.add(e);
							 }
							 
						// returnObjects.remove(q);
			
						
					
							
					
						 
							BaseScore++;
							
					
							
							}
						} 
			
						else if(returnObjects.get(q).getID() == ObjectID.Projectile && returnObjects.get(w).getID() == ObjectID.ENEMY){
							boolean collision = SATcollision.SpaceShipCollision(returnObjects.get(q).shape, returnObjects.get(w).shape);
							if(collision){
								
					
						
								manager.GameObjects.remove(returnObjects.get(w));
								BulletHandler.bulletList.remove(returnObjects.get(q));
								
								objects.remove(returnObjects.get(q));
								objects.remove(returnObjects.get(w));
								
							Vector2 v = new Vector2(returnObjects.get(w).CenterX , returnObjects.get(w).CenterY);
							
							 if(explosions.size() != 0 ){
								 int pointer = 0 ; 
					    for(int i = 0 ; i < explosions.size(); i++){
						
					    	if(areDifferent(explosions.get(i).getV(), v)){
								pointer++;
							 }
					    }
					    if(pointer == explosions.size()){
					    	Explosion e = new Explosion(new Animation(Explosion, 9 , 2 , 5),v);
					    	 e.a.start = true;
					    	explosions.add(e);
					    }
					    }
							 else{
								 Explosion e = new Explosion(new Animation(Explosion, 9 , 2 , 5),v);
								 e.a.start = true;
								 explosions.add(e);
							 }
							 
						
							// returnObjects.remove(w);
							 
						
					
									
							
							 BaseScore++;
								
							 
					
								
							 
							 
							 
							}
						} 
					}
				}
				}
				
				
					}
			   q.clear();
				
			
			}
			
			
		
		 
			for(int i = 0 ; i < objects.size(); i++){
				
				if(objects.get(i).getID() == ObjectID.ENEMY){
					
					
					ai.MoveTowardsPlayer(objects.get(i), new Vector2(Player.getX(), Player.getY()));
					
					if(SATcollision.SpaceShipCollision(Player.shape, objects.get(i).shape)){
		               
						gamestate = GameState.GAMEOVER;
						
						}
				}
			}
			
		   
			objects.clear();
			//   q.clear();
			   
		}
		
	
	public void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
    Graphics g = bs.getDrawGraphics();




if(gamestate == GameState.GAME){
	
	
	 
	g.drawImage(Background.getTexture(), 0, 0, Background.getTexture().getWidth(), Background.getTexture().getHeight(), null);
	
	renderer.renderObjects(g);
	
	String ScoreNumber = Integer.toString(BaseScore * 10);
	
	menu.AlignFont(g, 85, 60, "Score:", Text, Color.white);
	
	

	menu.AlignFont(g, 150 + ScoreNumber.length() / 2 * Text.getSize() / 2  , 60,  ScoreNumber, Text, Color.white);


}

else if(gamestate == GameState.MENU){
	g.setColor(Color.black);
	g.fillRect(0, 0, Width, Height);
menu.renderMenu(g);

}

else if(gamestate == GameState.GAMEOVER){
	g.drawImage(Background.getTexture(), 0, 0, Background.getTexture().getWidth(), Background.getTexture().getHeight(), null);
	renderer.renderObjects(g);
	menu.AlignFont(g, Game.Width / 2 - Text.getSize() / 2,  Game.Height / 2 - Text.getSize() / 2 , "Game Over", Text, Color.white);
}

	
		g.dispose();
		bs.show();
		 
	}
	
	public static void main(String args[]){
	 new Game();
		
	}
	
	public void init(){
		
		menu = new Menu(canvas);
		
		Explosion = ImageLoader.loadImage("res/ExplosionSheet.png");
		
		q = new QuadTree(Game.Width, Game.Height);
	
		  
		this.requestFocus();
		
	
		
		gamestate = GameState.MENU;
		
     bHandler = new BulletHandler(this);
     
	
   
 
      
		 manager = new ObjectManager();
		
		   texture = new TextureAtlas("res/EntitySheet.png");
		   Background = new Texture("res/SpaceBG.png");
		   
			float radius = (float) Math.sqrt(((texture.Entities[1].getWidth() / 2) * (texture.Entities[1].getWidth() / 2)) + ((texture.Entities[1].getHeight() / 2) * (texture.Entities[1].getHeight() / 2)));
		
			BasicEnemy = new GameObject(0,0, texture.Entities[1].getWidth(), texture.Entities[1].getHeight(), ObjectID.ENEMY,texture.Entities[1], new Circle(texture.Entities[0].getWidth(), texture.Entities[0].getHeight(),radius, new Vector2(0,0) , ShapeID.Circle)); 
		   
	


		
	 //new GameObject(x,y, texture.Entities[1].getWidth(), texture.Entities[1].getHeight(), ObjectID.ENEMY,texture.Entities[1], new Circle(texture.Entities[0].getWidth(), texture.Entities[0].getHeight(),radius, new Vector2(x,y) , ShapeID.Circle))
	    Player = new GameObject(Width / 2 ,  Height / 2 , texture.Entities[0].getWidth(), texture.Entities[0].getHeight(), ObjectID.PLAYER, texture.Entities[0], new Triangle(texture.Entities[0].getWidth(),texture.Entities[0].getHeight(), new Vector2(Width / 2,Height / 2), 0 ,ShapeID.Triangle));
        //objects.add(Player);
	    manager.addGameObject(Player);
      
	    
	

	 renderer = new MasterRenderer(manager, this);



		
		new MouseEvents(this);
	    addKeyListener(new KeyboardEvents(this)); 
	  
	
	}
public GameObject getPlayerInstance(){
	return Player;
}


public void keyPressed(KeyEvent e){
	int key = e.getKeyCode();
	
	if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
		MoveLeft = true;
   /*  Game.getPlayerInstance().setVelocityX(-5);	*/
     /* System.out.println("LEFT");*/
	}
	
	else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
		MoveRight = true;
      /*   Game.getPlayerInstance().setVelocityX(5);*/		
         /*  System.out.println("RIGHT");*/
		}

	else if(key == KeyEvent.VK_W  || key == KeyEvent.VK_UP){
		MoveUp = true;
        /*  Game.getPlayerInstance().setVelocityY(-5);	*/
          /*  System.out.println("UP");*/
		}
	else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
		MoveDown = true;
       /*   Game.getPlayerInstance().setVelocityY(5);*/
        /*  System.out.println("DOWN");*/
		}


if(key == KeyEvent.VK_ESCAPE){
	System.exit(1);
}


}

public void keyReleased(KeyEvent e){
	int key = e.getKeyCode();
	
	if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
		MoveLeft = false;
		this.getPlayerInstance().setVelocityX(0);	

		}
		else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			MoveRight = false;
			this.getPlayerInstance().setVelocityX(0);	
	     
			}
	
		else if(key == KeyEvent.VK_W  || key == KeyEvent.VK_UP){
			MoveUp = false;
			this.getPlayerInstance().setVelocityY(0);	
			}
		else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
			MoveDown = false;
			this.getPlayerInstance().setVelocityY(0);			
		}
	
}

public void MovePlayerRight(){
	this.getPlayerInstance().setVelocityX(8);		
}

public void MovePlayerLeft(){
	this.getPlayerInstance().setVelocityX(-8);		
}

public void MovePlayerUp(){
	   this.getPlayerInstance().setVelocityY(-8);	
}

public void MovePlayerDown(){
	this.getPlayerInstance().setVelocityY(8);	
}


public static GameState getGamestate() {
	return gamestate;
}


public static void setGamestate(GameState gamestate) {
	Game.gamestate = gamestate;
}



public BulletHandler getBulletHandler(){
	return bHandler;
}

public boolean areDifferent(Vector2 a , Vector2 b){
	if(a.x == b.x && a.y == b.y){
		return false;
	}
	return true;
}




}







