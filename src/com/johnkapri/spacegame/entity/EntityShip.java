package com.johnkapri.spacegame.entity;

import com.johnkapri.spacegame.gfx.render.Render;

public class EntityShip extends Entity{
	
	public EntityShip (int x, int y) {
		super(x, y);
		this.renderId = Render.renderShip.getRenderId();
	}
}
