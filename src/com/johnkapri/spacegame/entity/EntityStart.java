package com.johnkapri.spacegame.entity;

import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;

public class EntityStart extends EntityObstacle{

	public EntityStart(int x, int y) {
		super(x, y);
		this.renderId = Render.renderStartButton.getRenderId();
	}
	
	@Override
	public void tick(PlayField p) {
		
	}
	
	@Override
	public void onCollide(Entity e, PlayField p) {
		if(e instanceof EntityBullet) {
			p.startRound();
			p.destroyEntity(this);
		}
	}
}
