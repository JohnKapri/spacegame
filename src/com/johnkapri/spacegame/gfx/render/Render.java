package com.johnkapri.spacegame.gfx.render;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

import com.johnkapri.spacegame.entity.Entity;
import com.johnkapri.spacegame.gfx.PlayField;

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
	
	private int id;
	protected List<Shape> shapes = new ArrayList<Shape>();
	
	public Render(int id) {
		if(Render.renderers[id] != null) {
			throw new RuntimeException("Invalid Render id at " + id + "! Id already used!");
		}
		this.id = id;
		Render.renderers[id] = this;
	}
	
	public int getRenderId() {
		return id;
	}
	
	public void render(Graphics2D g2d, int x, int y, float pitch, float tilt, int ticks) {
		g2d.translate(x, y);
		g2d.rotate(tilt);
		
		for(Shape s : shapes) {
			g2d.draw(s);
		}
		
		g2d.rotate(-tilt);
		g2d.translate(-x, -y);
	}

	public static void render(Entity e, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(PlayField.defaultColor);
		renderers[e.getRenderId()].render(g2d, (int) e.x, (int) e.y, 0.0F, e.tilt, e.ticks);
	}
		
	public static boolean doEntitiesCollide(Entity e1, Entity e2) {
		List<Shape> e1mask = Render.renderers[e1.getRenderId()].shapes;
		List<Shape> e2mask = Render.renderers[e2.getRenderId()].shapes;
		
		for (Shape s1 : e1mask) {
			Area a = new Area(s1);
			AffineTransform at = new AffineTransform();
			at.translate(e1.x, e1.y);
			at.rotate(e1.tilt);
			a.transform(at);
			for(Shape s2 : e2mask) {
				Area a1 = new Area(s2);
				AffineTransform at1 = new AffineTransform();
				at1.translate(e2.x, e2.y);
				at1.rotate(e2.tilt);
				a1.transform(at1);
				if(a.getBounds2D().intersects(a1.getBounds2D())) {
					return true;
				}
			}
		}
		return false;
	}
}
