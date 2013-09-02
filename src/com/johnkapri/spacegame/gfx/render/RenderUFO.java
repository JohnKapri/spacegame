package com.johnkapri.spacegame.gfx.render;

import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;

public class RenderUFO extends Render{

	public RenderUFO(int id) {
		super(id);
		shapes.add(new Arc2D.Float(-15.0F, -10.0F, 30.0F, 20.0F, 180.0F, -180.0F, Arc2D.OPEN));
		shapes.add(new Arc2D.Float(-15.0F, -2.0F, 30.0F, 5.0F, 180.0F, 180.0F, Arc2D.OPEN));
		shapes.add(new Line2D.Float(-30, -5, -15, -5));
		shapes.add(new Line2D.Float(+30, -5, +15, -5));
		shapes.add(new Line2D.Float(-30, 15, +30, 15));
		shapes.add(new Arc2D.Float(-50.0F, -5.0F, 40.0F, 20.0F, 90.0F, 180.0F, Arc2D.OPEN));
		shapes.add(new Arc2D.Float(+10.0F, -5.0F, 40.0F, 20.0F, 90.0F, -180.0F, Arc2D.OPEN));
		shapes.add(new Arc2D.Float(-50.0F, 0, 100.0F, 10.0F, 180.0F, 180.0F, Arc2D.OPEN));
	}
}
