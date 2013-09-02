package com.johnkapri.spacegame.entity;

import java.util.Random;

import com.johnkapri.spacegame.Game;
import com.johnkapri.spacegame.entity.particle.ParticleExplosion;
import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;

public class EntityObstacle extends Entity{

	public EntityObstacle(int x, int y) {
		super(x, y);
		this.renderId = Render.renderObstacle.getRenderId();
	}
	
	@Override
	public void tick(PlayField field) {
		y++;
		if(y > Game.HEIGHT + 7) {
			field.destroyEntity(this);
		}
	}
	
	@Override
	public void onCollide(Entity e, PlayField p) {
		if(e instanceof EntityBullet) {
			p.score(5);
			p.destroyEntity(this);
			p.destroyEntity(e);
			Random rand = new Random();
			for(int i = 0; i < 3 + rand.nextInt(4); i++) {
				p.addEntitiy(new ParticleExplosion((int) x, (int) y, 5 - rand.nextFloat() * 10, 5 - rand.nextFloat() * 10));
			}
		}
	}
}
