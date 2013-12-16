package com.johnkapri.spacegame.gfx.render;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class RenderHourGlass extends Render{

	public RenderHourGlass(int id) {
		super(id);
		shapes.add(new Rectangle2D.Float(-5F, -8F, 10F, 2F));
		shapes.add(new Rectangle2D.Float(-5F, 6F, 10F, 2F));
		shapes.add(new Rectangle2D.Float(-2F, -1F, 4F, 2F));
		shapes.add(new Line2D.Float(-5F, -5.5F, -2F, -2F));
		shapes.add(new Line2D.Float(-5F, 5.5F, -2F, 2F));
		shapes.add(new Line2D.Float(5F, -5.5F, 2F, -2F));
		shapes.add(new Line2D.Float(5F, 5.5F, 2F, 2F));
	}
}
