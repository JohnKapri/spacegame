package com.johnkapri.spacegame.entity;

import com.johnkapri.spacegame.gfx.render.Render;

public class EntityMissle extends EntityBullet {
	
	public EntityMissle(int x, int y, float xVel, float tilt) {
		super(x, y, xVel, tilt);
		this.yAcc = 8.0F;
		this.renderId = Render.renderMissle.getRenderId();
	}
}
