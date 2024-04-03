package Texture;

import java.awt.image.BufferedImage;


public class TextureAtlas {
	
	private TextureSeperator EntitySheet;
	private BufferedImage EntityTexture;
	public BufferedImage[] Entities = new BufferedImage[3];
	public BufferedImageLoader loader = new BufferedImageLoader();
	
	
	public TextureAtlas(String path){

		EntityTexture = loader.loadImage(path);
		EntitySheet = new TextureSeperator(EntityTexture);
		setTextures();
   
	}

	
	public void setTextures(){
		/*Player*/Entities[0] = loader.resize(EntitySheet.getTextureSection(0, 0, 128, 128), 64,64);
		/*EnemyShip*/Entities[1] = loader.resize(EntitySheet.getTextureSection(1, 0, 128, 128), 64,64);
	 /*Laser*/Entities[2] = loader.resize(EntitySheet.getTextureSection(2, 0, 128, 128), 32, 32);
	}

   
   
}
