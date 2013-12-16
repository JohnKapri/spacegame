package com.johnkapri.spacegame.entity;

import com.johnkapri.spacegame.entity.particle.ParticleExplosion;
import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;

public class EntityObstacle extends Entity {

	public EntityObstacle(int x, int y) {
		super(x, y);
		this.renderId = Render.renderObstacle.getRenderId();
		this.yAcc = 2.0F;
	}

	@Override
	public void onCollide(Entity e, PlayField p) {
		if (e instanceof EntityBullet) {
			p.score(5);
			p.destroyEntity(this);
			p.destroyEntity(e);
		}
	}

	@Override
	public boolean onDestroy(PlayField p) {
		// Random rand = new Random();
		// int force = 10;
		// for(int i = 0; i < 6 + rand.nextInt(4); i++) {
		// p.addEntitiy(new ParticleExplosion((int) x, (int) y, force -
		// rand.nextFloat() * force * 2, force - rand.nextFloat() * force * 2));
		// }

		int stripes = 8;
		int force = 10;
		for (int i = 0; i <= stripes; i++) {
			p.addEntitiy(new ParticleExplosion((int) x, (int) y, (float) Math
					.sin(((Math.PI * 2) / stripes) * i) * force, (float) Math
					.cos(((Math.PI * 2) / stripes) * i) * force));
		}

		p.score(5);
		return false;
	}
}
