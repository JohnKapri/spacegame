package com.johnkapri.spacegame.gfx.render;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

public class RenderStartButton extends Render{

	public RenderStartButton(int id) {
		super(id);
		shapes.add(new RoundRectangle2D.Float(-30, -8, 60, 16, 5, 5));
	}
	
	@Override
	public void render(Graphics2D g2d, int x, int y, float pitch, float tilt, int ticks) {
		g2d.translate(x, y);
		g2d.rotate(tilt);
		
		for(Shape s : shapes) {
			g2d.draw(s);
		}
		
		Font textFont = g2d.getFont();  
		FontMetrics textMetrics = g2d.getFontMetrics(textFont);  
		g2d.setFont(textFont);  
		  
		int centeredX = -textMetrics.stringWidth("Start")/2;  
		int centeredY = textMetrics.getHeight()/2-2;  
		  
		g2d.drawString("Start", centeredX, centeredY);
		
		g2d.rotate(-tilt);
		g2d.translate(-x, -y);
	}
}
