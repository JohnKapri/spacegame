package com.johnkapri.spacegame.gfx.render;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class RenderPresets {

	public static Shape[] getStarShapes() {
		Shape[] shapes = new Shape[1];

		int[] xs = new int[10];
		int[] ys = new int[10];
		float f = 360F / 10F;
		for (int i = 0; i < 10; i++) {
			double o = ((2F * Math.PI) / 360F) * (i * f + f / 2.0F);
			float j = (i % 2) * 4.0F + 4.0F;
			xs[i] = (int) (Math.cos(o) * j);
			ys[i] = (int) (Math.sin(o) * j) + 3;
		}
		shapes[0] = new Polygon(xs, ys, 10);
		return shapes;
	}

	public static void renderCircle(Graphics2D g2d, int x, int y, float size) {
		g2d.translate(x, y);
		g2d.draw(new Ellipse2D.Float(x, y, size, size));
		g2d.translate(-x, -y);
	}

	public static void renderNuke(Graphics2D g2d, float x, float y, float scale) {
		Shape[] shapes = new Shape[13];
		shapes[0] = new Arc2D.Float(-20 * scale, -20 * scale, 40 * scale,
				40 * scale, 180, 60, Arc2D.OPEN);
		shapes[1] = new Arc2D.Float(-20 * scale, -20 * scale, 40 * scale,
				40 * scale, 60, 60, Arc2D.OPEN);
		shapes[2] = new Arc2D.Float(-20 * scale, -20 * scale, 40 * scale,
				40 * scale, 300, 60, Arc2D.OPEN);

		shapes[3] = new Arc2D.Float(-7.5F * scale, -7.5F * scale, 15 * scale,
				15 * scale, 180, 60, Arc2D.OPEN);
		shapes[4] = new Arc2D.Float(-7.5F * scale, -7.5F * scale, 15 * scale,
				15 * scale, 60, 60, Arc2D.OPEN);
		shapes[5] = new Arc2D.Float(-7.5F * scale, -7.5F * scale, 15 * scale,
				15 * scale, 300, 60, Arc2D.OPEN);

		for (int i = 0; i < 6; i++) {
			shapes[6 + i] = new Line2D.Double(
					Math.cos(i * ((Math.PI) / 3)) * 7.5F * scale,
					Math.sin(i * ((Math.PI) / 3)) * 7.5F * scale, 
					Math.cos(i * ((Math.PI) / 3)) * 20 * scale,
					Math.sin(i * ((Math.PI) / 3)) * 20 * scale);
		}
		
		shapes[12] = new Ellipse2D.Float(-5 * scale, -5 * scale, 10 * scale, 10 * scale);

		g2d.translate(x, y);

		for (Shape s : shapes) {
			g2d.draw(s);
		}

		g2d.translate(-x, -y);
	}
}
