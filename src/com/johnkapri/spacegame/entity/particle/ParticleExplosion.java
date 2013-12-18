package com.johnkapri.spacegame.entity.particle;

import java.util.Random;

import com.johnkapri.spacegame.entity.EntityParticle;
import com.johnkapri.spacegame.gfx.render.Render;

public class ParticleExplosion extends EntityParticle{

	public ParticleExplosion(int x, int y, float xVel, float yVel) {
		super(x, y);
		Random rnd = new Random();
		this.yAcc = 4F;
		this.xVel = xVel;
		this.yVel = yVel;
		this.liveTime = 20 + rnd.nextInt(10);
		this.renderId = Render.renderParticleDust.getRenderId();
	}
}
