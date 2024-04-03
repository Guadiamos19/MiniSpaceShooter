package Objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Math.Vector2;
import Texture.BufferedImageLoader;
import Texture.TextureSeperator;

public class Animation {

	
	public BufferedImage i;
	public int numberofFrames;
	public BufferedImage[] Frames;
	public Frame currentFrame;
	
	public int numberOfRows, numberOfColumns;
	private TextureSeperator Ani;
	public BufferedImageLoader loader = new BufferedImageLoader();
	public int PictureSizeX, PictureSizeY;
	
	public int index = 0; 
	public int count = 0;
	private int speed = 3;
   public boolean start = false;
   public boolean finished = false;

 
	
	public Animation(BufferedImage i , int numberOfFrames , int numberOfRows, int numberOfColumns){
		this.i = i;
		this.numberofFrames = numberOfFrames;
        this.Frames = new BufferedImage[numberofFrames];
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        Ani = new TextureSeperator(i);
        
  	  PictureSizeX = i.getWidth() / numberOfColumns;
  	  PictureSizeY = i.getHeight() / numberOfRows;
  	  
        createFrames();
        
        currentFrame = new Frame(Frames[0], 0);	
       
	}
	
	
	
	public boolean isStart() {
		return start;
	}







	public boolean isFinished() {
		return finished;
	}






	public void createFrames()
	{

		
	
	  
	for(int i = 0 ; i < numberofFrames; i++){
	
		int col = i % numberOfColumns;
		int row = (int) Math.floor(i / numberOfColumns);
		

		
		Frames[i] = Ani.getTextureSection(col, row, PictureSizeX , PictureSizeY);
	
	}
		
	
	
	
		
	}
	
	public void nextFrame(){
		
		for(int i = 0 ; i < Frames.length; i++){
			
			if(count == i){
				
			currentFrame = new Frame(Frames[i], i);	
			}
		
			
		}
		count++;
		
		if(count > Frames.length){
		
			count = 0;
			
			finished = true;
		  
		}
	
	}
	
	
	public void runAnimation(){
		if(start){
		index++;
		if(index > speed){
			index = 0;
			nextFrame();
		}
		}
	}
	

	
	public void playAnimation(Vector2 p, Graphics g){
		
	
		
		Graphics g2d = (Graphics2D) g;
   
		g2d.drawImage( currentFrame.getI(),(int) p.getX(), (int)p.getY(), 64, 64,  null);

	}
	
	
	
	
	}
	
	
	

