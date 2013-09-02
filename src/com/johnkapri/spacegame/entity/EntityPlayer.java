package com.johnkapri.spacegame.entity;

import com.johnkapri.spacegame.InputHandler;
import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;

public class EntityPlayer extends Entity {

	private InputHandler input;
	private boolean useReleased = true;
	private int shootCooldown;
	
	public EntityPlayer(InputHandler input, int x, int y) {
		super(x, y);
		this.input = input;
		this.renderId = Render.renderShip.getRenderId();
		this.width = 40;
	}

	@Override
	public void tick(PlayField field) {
		super.tick(field);
		if (input.left) {
			xAcc = -4.0F;
		} else if (input.right) {
			xAcc = 4.0F;
		} else {
			xAcc = 0;
		}
		if (shootCooldown > 0) {
			shootCooldown--;
		}
		if (input.use) {
			if ((shootCooldown == 0) && useReleased) {
				field.addEntitiy(new EntityLaser((int) (x + Math.sin(tilt) * 10), (int) (y - 10 * Math.cos(tilt)), xVel, tilt));
				shootCooldown = 8;
			}
			useReleased = false;
		} else {
			useReleased = true;
		}
	}
	
	@Override
	public void onCollide(Entity e, PlayField p) {
		if(e instanceof EntityObstacle) {
			p.endRound();
			p.destroyEntity(this);
		}
	}
}
