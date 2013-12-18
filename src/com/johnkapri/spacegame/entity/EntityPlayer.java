package com.johnkapri.spacegame.entity;

import com.johnkapri.spacegame.InputHandler;
import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;

public class EntityPlayer extends Entity {

	private InputHandler input;
	private boolean useReleased = true;
	private int shootCooldown;
	private Weapon weapon;
	private int weaponLasting = 0;

	public EntityPlayer(InputHandler input, int x, int y) {
		super(x, y);
		this.input = input;
		this.renderId = Render.renderShip.getRenderId();
		this.width = 40;
		this.weapon = Weapon.BULLET;
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
				switch (weapon) {
				default:
				case BULLET:
					field.addEntitiy(new EntityBullet(
							(int) (x + Math.sin(tilt) * 10),
							(int) (y - 10 * Math.cos(tilt)), xVel, tilt));
					break;
				case MISSLE:
					field.addEntitiy(new EntityMissle(this, (int) (x + Math
							.sin(tilt) * 10), (int) (y - 10 * Math.cos(tilt)),
							xVel, tilt));
					break;
				case LASER:
					field.addEntitiy(new EntityLaser(
							(int) (x + Math.sin(tilt) * 10),
							(int) (y - 10 * Math.cos(tilt)), xVel, tilt));
					break;
				case NUKE:
					field.setSlomo(3, 100);
					field.endRound();
					weapon = Weapon.BULLET;
					break;
				}
				shootCooldown = weapon.getCoolDown();
				field.score(-weapon.getCost());
			}
			useReleased = weapon.isRapidFire();
		} else {
			useReleased = true;
		}

		if (weaponLasting <= 0) {
			this.weapon = Weapon.BULLET;
		} else {
			weaponLasting--;
		}

		doCollision(field);
	}

	private void doCollision(PlayField p) {
		for (Entity e : p.getCollisionListFor(this)) {
			if (e != null && e instanceof EntityObstacle) {
				p.gameOver();
				p.destroyEntity(this);
			}
		}
	}

	@Override
	public void onCollide(Entity e, PlayField p) {
		if (e instanceof EntityObstacle) {
			p.gameOver();
			p.destroyEntity(this);
		}
	}

	public enum Weapon {
		MISSLE(16, false, 10), LASER(8, false, 5), BULLET(4, true, 0), NUKE(30, false, 0);

		int coolDown;
		boolean rapid;
		int cost;

		Weapon(int c, boolean r, int m) {
			coolDown = c;
			rapid = r;
			cost = m;
		}

		public int getCoolDown() {
			return coolDown;
		}

		public boolean isRapidFire() {
			return rapid;
		}
		
		public int getCost() {
			return cost;
		}
	}

	public void setWeapon(Weapon w) {
		if (this.weapon == w) {
			weaponLasting += 1200;
		} else {
			weaponLasting = 1200;
		}
		this.weapon = w;
	}
}
