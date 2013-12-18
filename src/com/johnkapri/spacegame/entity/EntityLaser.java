package com.johnkapri.spacegame.entity;

import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;

public class EntityLaser extends EntityBullet {

	private int bonus;
	
	public EntityLaser(int x, int y, float xVel, float tilt) {
		super(x, y, xVel, tilt);
		this.renderId = Render.renderLaser.getRenderId();
		this.yVel = -30.0F * (float) Math.cos(tilt);
		this.xVel += 30.0F * (float) Math.sin(tilt);
	}
	
	@Override
	protected void doCollision(PlayField p) {
		for (Entity e : p.getCollisionListFor(this)) {
			if (e != null && e instanceof EntityObstacle) {
				p.destroyEntity(e);
				p.score(bonus);
				bonus += 10 ;
			}
		}
	}
}
