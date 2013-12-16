package com.johnkapri.spacegame.entity;

import java.util.Random;

import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;

public class EntityObstacleSpecial extends EntityObstacle {

	public EntityObstacleSpecial(int x, int y) {
		super(x, y);
		this.renderId = Render.renderObstacleSpecial.getRenderId();
	}

	@Override
	public boolean onDestroy(PlayField p) {
		super.onDestroy(p);
		EntitySpecial.Type t = EntitySpecial.Type.values()[new Random()
				.nextInt(EntitySpecial.Type.values().length)];
		p.addEntitiy(new EntitySpecial((int) x, (int) y, EntitySpecial.Type.SLOMO));
		return false;
	}
}
