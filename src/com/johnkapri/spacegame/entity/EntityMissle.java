package com.johnkapri.spacegame.entity;

import java.util.List;

import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;

public class EntityMissle extends EntityBullet {
	
	private Entity origin;
	
	public EntityMissle(Entity origin, int x, int y, float xVel, float tilt) {
		super(x, y, xVel, tilt);
		this.yAcc = 8.0F;
		this.renderId = Render.renderMissle.getRenderId();
		this.origin = origin;
	}
	
	@Override
	protected void doCollision(PlayField p) {
		List<Entity> ents = p.getCollisionListFor(this);
		for(Entity e : ents) {
			if(e.isSolid() && e != origin) {
				p.addEntitiy(new EntityExplosion((int) x, (int) y));
				p.destroyEntity(this);
			}
		}
	}
}
