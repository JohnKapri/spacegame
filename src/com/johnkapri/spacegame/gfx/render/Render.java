package com.johnkapri.spacegame.gfx.render;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

import com.johnkapri.spacegame.entity.Entity;
import com.johnkapri.spacegame.entity.EntityParticle;

public abstract class Render {

	public static final Render[] renderers = new Render[32];
	public static final Render renderDefault = new RenderDefault(1);
	public static final Render renderShip = new RenderShip(2);
	public static final Render renderObstacle = new RenderObstacle(3);
	public static final Render renderBullet = new RenderBullet(4);
	public static final Render renderUFO = new RenderUFO(5);
	public static final Render renderMissle = new RenderMissle(6);
	public static final Render renderLaser = new RenderLaser(7);
	public static final Render renderParticleDust = new RenderParticleDust(8);
	public static final Render renderStartButton = new RenderStartButton(9);
	public static final Render renderObstacleSpecial = new RenderObstacleSpecial(
			10);
	public static final Render renderHourGlass = new RenderHourGlass(11);

	private int id;
	protected List<Shape> shapes = new ArrayList<Shape>();

	public Render(int id) {
		if (Render.renderers[id] != null) {
			throw new RuntimeException("Invalid Render id at " + id
					+ "! Id already used!");
		}
		this.id = id;
		Render.renderers[id] = this;
	}

	public int getRenderId() {
		return id;
	}

	public void render(Graphics2D g2d, int x, int y, float pitch, float tilt,
			int ticks) {
		g2d.translate(x, y);
		g2d.rotate(tilt);

		for (Shape s : shapes) {
			g2d.draw(s);
		}

		g2d.rotate(-tilt);
		g2d.translate(-x, -y);
	}

	public static void render(Entity e, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(e.getColor());
		renderers[e.getRenderId()].render(g2d, (int) e.x, (int) e.y, 0.0F,
				e.tilt, e.ticks);
	}

	public static boolean doEntitiesCollide(Entity e1, Entity e2) {
		if (e1 == null || e2 == null || e1 instanceof EntityParticle
				|| e2 instanceof EntityParticle) {
			return false;
		}

		Area a1 = new Area();
		for (int i = 0; i < Render.renderers[e1.getRenderId()].shapes.size(); i++) {
			a1.add(new Area(Render.renderers[e1.getRenderId()].shapes.get(i)));
		}

		Area a2 = new Area();
		for (int i = 0; i < Render.renderers[e2.getRenderId()].shapes.size(); i++) {
			a2.add(new Area(Render.renderers[e2.getRenderId()].shapes.get(i)));
		}

		AffineTransform at1 = new AffineTransform();
		at1.translate(e1.x, e1.y);
		at1.rotate(e1.tilt);

		AffineTransform at2 = new AffineTransform();
		at2.translate(e2.x, e2.y);
		at2.rotate(e2.tilt);

		a1.transform(at1);
		a2.transform(at2);

		a1.intersect(a2);

		return !a1.isEmpty();
	}
}
