package com.johnkapri.spacegame.entity.particle;

import com.johnkapri.spacegame.entity.EntityParticle;
import com.johnkapri.spacegame.gfx.render.Render;

public class ParticleExplosion extends EntityParticle{

	public ParticleExplosion(int x, int y, float xVel, float yVel) {
		super(x, y);
		this.xVel = xVel;
		this.yVel = yVel;
		this.renderId = Render.renderParticleDust.getRenderId();
	}
}