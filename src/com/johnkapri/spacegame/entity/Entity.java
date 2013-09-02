package com.johnkapri.spacegame.entity;

import java.awt.Graphics;

import com.johnkapri.spacegame.Game;
import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;

public class Entity {
	
	public float x;
	public float y;
	public float xVel;
	public float yVel;
	public float friction = 1.2F;
	public float xAcc;
	public float yAcc;
	public float tilt = 0.0F;
	public int width;
	public int ticks;
	protected int renderId = Render.renderDefault.getRenderId();

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(PlayField field) {
		ticks++;
		float lastX = x;
		
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
		tilt = tilt / 5.0F;
		tilt = - (lastX - x) / 10.0F;
		if((tilt < 0.01F && tilt > 0) || (tilt > -0.01F && tilt < 0)) {
			tilt = 0;
		}
	}
	
	public void render(Graphics g) {
		Render.render(this, g);
	}
	
	public boolean onDestroy(PlayField p) {
		return false;
	}
	
	public void onCollide(Entity e, PlayField p) {
		
	}

	public int getRenderId() {
		return renderId;
	}
}
