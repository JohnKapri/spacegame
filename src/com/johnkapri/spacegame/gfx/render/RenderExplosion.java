package com.johnkapri.spacegame.gfx.render;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class RenderExplosion extends Render{

	public RenderExplosion(int id) {
		super(id);
		shapes.add(new Ellipse2D.Float(-50, -50, 100, 100));
	}

	@Override
	public void render(Graphics2D g2d, int x, int y, float stuff, float tilt, int ticks) {
		g2d.translate(x, y);
		
		float s = 100F * (float) Math.sin(ticks * (Math.PI / 30));
		g2d.draw(new Ellipse2D.Float(-s/2, -s/2, s, s));
		
		g2d.translate(-x, -y);
	}
}
