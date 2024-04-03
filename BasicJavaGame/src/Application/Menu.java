package Application;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import Math.AABB;
import Math.Vector2;

public class Menu  {



	
	
    private int FONT_SIZE = 50;
    private int TITLE_FONT_SIZE = 72;
	public int START_X = Game.Width / 2 ;
	public int START_Y = Game.Height / 3  ;
	public int TITLE_X = Game.Width / 2 ;
	public  int TITLE_Y = Game.Height / 8;
	private Font Start, Title;
	private Canvas canvas;
	public AABB StartButton;

	public Menu(Canvas canvas){
		this.canvas = canvas;
		this.Start =  new Font("arial", Font.BOLD, FONT_SIZE);
		this.Title = new Font("arial", Font.BOLD, TITLE_FONT_SIZE);
		this.StartButton = TextBoxInfo(START_X,START_Y, "Start", Start);
		
	}
	
	
	public void renderMenu(Graphics g){
		

	
	drawTextBox(g, START_X, START_Y, "Start" , Start, Color.white);
	AlignFont(g,TITLE_X,TITLE_Y, "Space Game",Title,Color.white);
		
	
	}
	
	

	
	public void AlignFont(Graphics g , int x, int y, String text,  Font font, Color color) {
	
	    FontMetrics metric = canvas.getFontMetrics(font);
		g.setFont(font);
		g.setColor(color);
	  
   Graphics2D g2d = (Graphics2D) g;
   
	AffineTransform backUp = g2d.getTransform();
	
	    Shape outline = font.createGlyphVector(g.getFontMetrics().getFontRenderContext(), text).getOutline();
	 
	    AffineTransform transform = AffineTransform.getTranslateInstance(0,0 );
	    outline = transform.createTransformedShape(outline);
	    
	    g.translate(x - metric.stringWidth(text) / 2 , y - metric.getHeight() + metric.getAscent());   
	    g2d.fill(outline);
	g2d.setTransform(backUp);
	
	  
		
	}
	
	public AABB TextBoxInfo(int x, int y, String text, Font font){
		FontMetrics metric = canvas.getFontMetrics(font);
		AABB TextBoxPosition = new AABB((x - metric.stringWidth(text) / 2) ,(y - metric.getHeight() + metric.getAscent()) + (-metric.getHeight() / 2 - metric.getDescent() * 3 / 2 ), metric.stringWidth(text), metric.getHeight() ); 
	
	 return TextBoxPosition;
	}

	
	public void drawTextBox(Graphics g , int x, int y, String text,  Font font, Color color) {
		
	    FontMetrics metric = canvas.getFontMetrics(font);
		g.setFont(font);
		g.setColor(color);
	  
   Graphics2D g2d = (Graphics2D) g;
   
	AffineTransform backUp = g2d.getTransform();
	
	    Shape outline = font.createGlyphVector(g.getFontMetrics().getFontRenderContext(), text).getOutline();
	 
	    AffineTransform transform = AffineTransform.getTranslateInstance(0,0 );
	    outline = transform.createTransformedShape(outline);
 
	    g.translate(x - metric.stringWidth(text) / 2 , y - metric.getHeight() + metric.getAscent());   
	    g2d.drawRect(0, -metric.getHeight() / 2 - metric.getDescent() * 3 / 2   , metric.stringWidth(text), metric.getHeight());
	   
	    g2d.fill(outline);
	    
		
	g2d.setTransform(backUp);
	
	  
		
	}


	

	

	
}
