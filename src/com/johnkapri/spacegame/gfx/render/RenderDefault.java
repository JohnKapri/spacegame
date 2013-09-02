package com.johnkapri.spacegame.gfx.render;

import java.awt.geom.Rectangle2D;

public class RenderDefault extends Render{
	
	public RenderDefault(int id) {
		super(id);
		shapes.add(new Rectangle2D.Float(-5, -7, 10, 15));
	}
}
