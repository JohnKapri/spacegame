package com.johnkapri.spacegame.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;
import com.johnkapri.spacegame.gfx.render.RenderPresets;

public class EntitySpecial extends Entity {

	private Type type;

	public EntitySpecial(int x, int y, Type t) {
		super(x, y);
		Random rnd = new Random();
		this.type = Type.values()[rnd.nextInt(4)];
		this.renderId = Render.renderPowerup.getRenderId();
		this.yAcc = 6.0F;
	}

	@Override
	public void tick(PlayField field) {
		super.tick(field);
		doCollision(field);
	}

	@Override
	public void onCollide(Entity e, PlayField p) {
		if (e instanceof EntityPlayer) {
			castEffect((EntityPlayer) e, p);
			p.destroyEntity(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		Render.render(this, g);
		if (type.hasRender()) {
			Render.renderers[type.getRenderId()].render((Graphics2D) g,
					(int) x, (int) y, 0, (float) Math.PI / 2, ticks);
		} else {
			switch (type) {
			case NUKE:
				RenderPresets.renderNuke((Graphics2D) g, x, y, 0.6F);
				break;
			default:
				break;
			}
		}
	}

	private void doCollision(PlayField p) {
		for (Entity e : p.getCollisionListFor(this)) {
			if (e != null && e instanceof EntityPlayer) {
				castEffect((EntityPlayer) e, p);
				p.destroyEntity(this);
			}
		}
	}

	private void castEffect(EntityPlayer p, PlayField f) {
		switch (type) {
		case SLOMO:
			f.setSlomo(3, 1200);
			break;
		case MISSLE:
			p.setWeapon(EntityPlayer.Weapon.MISSLE);
			break;
		case LASER:
			p.setWeapon(EntityPlayer.Weapon.LASER);
			break;
		case NUKE:
			p.setWeapon(EntityPlayer.Weapon.NUKE);
			break;
		case PACHMAN:
			break;
		case SHIELD:
			break;
		default:
			break;
		}
	}

	public enum Type {
		SLOMO(Render.renderHourGlass.getRenderId(), true), MISSLE(
				Render.renderMissle.getRenderId(), true), LASER(
				Render.renderLaser.getRenderId(), true), NUKE(
				Render.renderDefault.getRenderId()), SHIELD(
				Render.renderDefault.getRenderId()), PACHMAN(
				Render.renderDefault.getRenderId());

		private int rndId;
		private float tlt;
		private boolean hasRender = false;

		Type(int r, boolean b) {
			this.rndId = r;
			// this.tlt = t;
			this.hasRender = b;
		}

		Type(int r) {
			this(r, false);
		}

		public int getRenderId() {
			return rndId;
		}

		public float getRenderTilt() {
			return tlt;
		}

		public boolean hasRender() {
			return hasRender;
		}
	}
}
