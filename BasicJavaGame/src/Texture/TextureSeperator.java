package Texture;

import java.awt.image.BufferedImage;

public class TextureSeperator {

	private BufferedImage image;
	
	public TextureSeperator(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getTextureSection(int col, int row, int width, int height){
		BufferedImage i = image.getSubimage((col * width) , (row * height) , width, height);
		return i;
	}
	
}
