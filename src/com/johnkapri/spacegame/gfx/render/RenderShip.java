package com.johnkapri.spacegame.gfx.render;

import java.awt.Polygon;

public class RenderShip extends Render {

	private static final int[] xPoints = { 0, -5, -15, -20, -10, 10, 20, 15, 5 };
	private static final int[] yPoints = { -15, -5, -5, 10, 5, 5, 10, -5, -5 };

	public RenderShip(int id) {
		super(id);
		Polygon p = new Polygon();
		for (int i = 0; i < xPoints.length; i++) {
			p.addPoint(xPoints[i], yPoints[i]);
		}
		shapes.add(p);
	}
}
