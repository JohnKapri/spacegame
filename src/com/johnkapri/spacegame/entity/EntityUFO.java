package com.johnkapri.spacegame.entity;

import java.util.Random;

import com.johnkapri.spacegame.Game;
import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;

public class EntityUFO extends Entity {

	private Random rand = new Random();
	private int moveX;
	private int moveY;

	public EntityUFO(int x, int y) {
		super(x, y);
		this.renderId = Render.renderUFO.getRenderId();
	}

	@Override
	public void tick(PlayField field) {
		if (moveX == 0) {
			while (true) {
				moveX = rand.nextInt(Game.WIDTH / 2) - Game.WIDTH / 4;
				if (x + moveX > 50 && x + moveX < Game.WIDTH - 50) {
					break;
				}
			}
		} else {
			if(moveX < 0) {
				x--;
				moveX++;
			} else {
				x++;
				moveX--;
			}
		}
		if (moveY == 0) {
			while (true) {
				moveY = rand.nextInt(Game.WIDTH / 2) - Game.WIDTH / 4;
				if (y + moveY > 30 && y + moveY < 80) {
					break;
				}
			}
		} else {
			if(moveY < 0) {
				y--;
				moveY++;
			} else {
				y++;
				moveY--;
			}
		}
	}
}
