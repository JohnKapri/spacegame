package com.johnkapri.spacegame.gfx.render;

import java.awt.geom.RoundRectangle2D;

public class RenderObstacle extends Render{

	public RenderObstacle(int id) {
		super(id);
		shapes.add(new RoundRectangle2D.Float(-15F, -5F, 30.0F, 15.0F, 5.0F, 5.0F));
	}
}
