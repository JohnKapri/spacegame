package com.johnkapri.spacegame.entity;

import java.util.Random;

import com.johnkapri.spacegame.gfx.PlayField;

public class EntityParticle extends Entity{

	protected int liveTime = 20;
	protected Random rand = new Random();
	
	public EntityParticle(int x, int y) {
		super(x, y);
		liveTime = 20 + rand.nextInt(10);
		this.solid = false;
	}
	
	@Override
	public void tick(PlayField p) {
		super.tick(p);
		if(ticks > liveTime) {
			p.destroyEntity(this);
		}
	}
}
