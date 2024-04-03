package Texture;

import java.awt.image.BufferedImage;

import Application.Game;

public class Texture {

	public BufferedImageLoader loader = new BufferedImageLoader();
	protected BufferedImage EntityTexture;
	
	public Texture(String path){
		EntityTexture = loader.loadImage(path);
		EntityTexture = loader.resize(EntityTexture, Game.Height , Game.Width);
	}
	
	
	public BufferedImage getTexture(){
		return EntityTexture;
	}
}
