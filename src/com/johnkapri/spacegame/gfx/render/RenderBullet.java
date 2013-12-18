package com.johnkapri.spacegame.gfx.render;

import java.awt.geom.Rectangle2D;

public class RenderBullet extends Render {

	public RenderBullet(int id) {
		super(id);
		shapes.add(new Rectangle2D.Float(-0.5F, -3.0F, 0.5F, +3.0F));
	}
}
