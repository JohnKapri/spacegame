package com.johnkapri.spacegame.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

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
	protected Color color;

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		color = getRandomColor();
	}
	
	public void tick(PlayField field) {
		ticks++;
		float lastX = x;
		
		xVel = xVel + xAcc / 10.0F;
		yVel = yVel + yAcc / 10.0F;
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
		
		if(y > Game.HEIGHT + 10) {
			field.destroyEntity(this);
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
	
	public Color getColor() {
		return color;
	}
	
	private Color getRandomColor() {
		Color c = Color.MAGENTA;
		int i = new Random().nextInt(7) + 1;
		switch(i) {
		case 1:
			c = Color.RED;
			break;
		case 2:
			c = Color.BLUE;
			break;
		case 3:
			c = Color.GREEN;
			break;
		case 4:
			c = Color.CYAN;
			break;
		case 5:
			c = Color.PINK;
			break;
		case 6:
			c = Color.YELLOW;
			break;
		case 7:
			c = Color.MAGENTA;
			break;
		case 8:
			c = Color.ORANGE;
			break;
		}
		
		return c;
	}
}
