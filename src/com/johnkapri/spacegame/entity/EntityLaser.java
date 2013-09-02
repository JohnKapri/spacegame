package com.johnkapri.spacegame.entity;

import com.johnkapri.spacegame.gfx.render.Render;

public class EntityLaser extends EntityBullet{

	public EntityLaser(int x, int y, float xVel, float tilt) {
		super(x, y, xVel, tilt);
		this.renderId = Render.renderLaser.getRenderId();
		this.yVel = -15.0F * (float) Math.cos(tilt);
		this.xVel += 15.0F * (float) Math.sin(tilt);
	}
}
