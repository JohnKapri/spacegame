package com.johnkapri.spacegame.entity;

import com.johnkapri.spacegame.Game;
import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;

public class EntityBullet extends Entity{

	public EntityBullet(int x, int y, float xVel, float tilt) {
		super(x, y);
		this.xVel = xVel;
		this.tilt = tilt;
		this.renderId = Render.renderBullet.getRenderId();
		this.yVel = -5.0F * (float) Math.cos(tilt);
		this.xVel += 5.0F * (float) Math.sin(tilt);
		this.friction = 1.0F;
	}
	
	@Override
	public void tick(PlayField field) {
		//super.tick(field);
		xVel = xVel + xAcc / 10.F;
		x += xVel;
		if(x < width / 2) {
			x = width / 2;
		}
		if(x > Game.WIDTH - width / 2) {
			x = Game.WIDTH - width / 2;
		}
		y += yVel;
		xVel = xVel / friction;
		yVel = yVel / friction;
		if(x <= 0 || x >= Game.WIDTH || y < -2) {
			field.destroyEntity(this);
		}
		
		doCollision(field);
	}
	
	private void doCollision(PlayField p) {
		for(Entity e : p.getCollisionListFor(this)) {
			if(e != null && e instanceof EntityObstacle) {
				p.destroyEntity(e);
				p.destroyEntity(this);
			}
		}
	}
}
