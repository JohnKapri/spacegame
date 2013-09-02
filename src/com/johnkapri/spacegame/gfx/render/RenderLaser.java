package com.johnkapri.spacegame.gfx.render;

import java.awt.geom.Rectangle2D;

public class RenderLaser extends Render{

	public RenderLaser(int id) {
		super(id);
		shapes.add(new Rectangle2D.Float(-0.5F, -8.0F, 0.5F, +8.0F));
	}
}
