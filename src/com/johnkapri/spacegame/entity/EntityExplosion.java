package com.johnkapri.spacegame.entity;

import java.util.List;

import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;

public class EntityExplosion extends Entity {

	private int bonus;
	private boolean exploded;

	public EntityExplosion(int x, int y) {
		super(x, y);
		this.renderId = Render.renderExplosion.getRenderId();
		this.solid = false;
	}

	@Override
	public void tick(PlayField field) {
		ticks++;
		if (!exploded) {
			doCollision(field);
			exploded = true;
		}
		if (ticks >= 30) {
			field.destroyEntity(this);
		}
	}

	private void doCollision(PlayField p) {
		List<Entity> ents = p.getCollisionListFor(this);
		for (Entity e : ents) {
			if (e instanceof EntityObstacle) {
				p.destroyEntity(e);
				p.score(bonus);
				bonus += 20;
			}
		}
	}
}
