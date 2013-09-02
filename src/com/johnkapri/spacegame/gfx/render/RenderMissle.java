package com.johnkapri.spacegame.gfx.render;

import java.awt.Polygon;


public class RenderMissle extends Render{

	private static final int xPoints[] = {0, -4, -2, -2, -4, -4, -2, 2, 4, 4, 2, 2, 4};
	private static final int yPoints[] = {-4, 0, 0, 6, 8, 12, 10, 10, 12, 8, 6, 0, 0};
	
	public RenderMissle(int id) {
		super(id);
		Polygon p = new Polygon();
		for(int i = 0; i < xPoints.length; i++) {
			p.addPoint(xPoints[i], yPoints[i]);
		}
		shapes.add(p);
	}
}
