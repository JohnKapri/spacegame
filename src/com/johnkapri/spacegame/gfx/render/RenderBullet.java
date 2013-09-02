package com.johnkapri.spacegame.gfx.render;

import java.awt.geom.Line2D;

public class RenderBullet extends Render{

	public RenderBullet(int id) {
		super(id);
		shapes.add(new Line2D.Float(0, -2, 0, +2));
	}
}
