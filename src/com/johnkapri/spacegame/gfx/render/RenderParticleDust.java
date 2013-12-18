package com.johnkapri.spacegame.gfx.render;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.Random;

public class RenderParticleDust extends Render{

	Random rand = new Random();
	
	public RenderParticleDust(int id) {
		super(id);
		int ammount = 4 + rand.nextInt(4);
		for(int i = 0; i < ammount; i++) {
			Polygon p = new Polygon();
			p.addPoint(3 + rand.nextInt(5), 3 + rand.nextInt(5));
			p.addPoint(3 + rand.nextInt(5), 3 + rand.nextInt(5));
			p.addPoint(3 + rand.nextInt(5), 3 + rand.nextInt(5));
			shapes.add(p);
		}
	}
		
	@Override
	public void render(Graphics2D g2d, int x, int y, float pitch, float tilt, int ticks) {
		g2d.translate(x, y);
		g2d.rotate(tilt);
		g2d.scale(0.5, 0.5);
		
		for(Shape s : RenderPresets.getStarShapes()) {
			g2d.draw(s);
		}
		
		g2d.scale(1/0.5, 1/0.5);
		g2d.rotate(-tilt);
		g2d.translate(-x, -y);
	}
}
