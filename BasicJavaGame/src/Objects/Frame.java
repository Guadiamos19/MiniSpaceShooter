package Objects;

import java.awt.image.BufferedImage;

public class Frame {

	public BufferedImage i;
	public int frameIndex;
	
	
	public Frame(BufferedImage i , int frameIndex){
		this. i = i;
		this.frameIndex = frameIndex;
	}


	public BufferedImage getI() {
		return i;
	}


	public int getFrameIndex() {
		return frameIndex;
	}
	
	
	
}
