package com.johnkapri.spacegame.gfx.render;

import java.awt.geom.Ellipse2D;

public class RenderPowerup extends Render{

	public RenderPowerup(int id) {
		super(id);
		shapes.add(new Ellipse2D.Float(-20, -20, 40, 40));
	}
}
