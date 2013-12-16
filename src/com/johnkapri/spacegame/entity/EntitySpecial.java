package com.johnkapri.spacegame.entity;

import com.johnkapri.spacegame.gfx.PlayField;
import com.johnkapri.spacegame.gfx.render.Render;

public class EntitySpecial extends Entity{

	private Type type;
	
	public EntitySpecial(int x, int y, Type t) {
		super(x, y);
		this.type = t;
		this.renderId = Render.renderHourGlass.getRenderId();
		this.yAcc = 6.0F;
	}
	
	@Override
	public void tick(PlayField field) {
		super.tick(field);
		doCollision(field);
	}

	@Override
	public void onCollide(Entity e, PlayField p) {
		if(e instanceof EntityPlayer) {
			castEffect((EntityPlayer) e, p);
			p.destroyEntity(this);
		}
	}
	
	private void doCollision(PlayField p) {
		for(Entity e : p.getCollisionListFor(this)) {
			if(e != null && e instanceof EntityPlayer) {
				castEffect((EntityPlayer) e, p);
				p.destroyEntity(this);
			}
		}
	}
	
	private void castEffect(EntityPlayer p, PlayField f) {
		switch(type) {
		case SLOMO:
			f.setSlomo(3, 1200);
			break;
		case MISSLE:
//			p.setWeapon(EntityPlayer.Weapon.MISSLE);
		case LASER:
//			p.setWeapon(EntityPlayer.Weapon.LASER);
			break;
		case LIGHTNING:
//			p.setWeapon(EntityPlayer.Weapon.LIGHTNING);
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
		SLOMO,
		MISSLE,
		LASER,
		LIGHTNING,
		SHIELD,
		PACHMAN;
	}
}
