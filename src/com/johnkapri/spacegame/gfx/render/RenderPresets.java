package com.johnkapri.spacegame.gfx.render;

import java.awt.Polygon;
import java.awt.Shape;

public class RenderPresets {

	public static Shape[] getStarShapes() {
		Shape[] shapes = new Shape[1];
		
		int[] xs = new int[10];
		int[] ys = new int[10];
		float f = 360F / 10F;
		for(int i = 0; i < 10; i++) {
			double o = ((2F * Math.PI) / 360F) * (i * f + f / 2.0F);
			float j = (i % 2) * 4.0F + 4.0F;
			xs[i] = (int) (Math.cos(o) * j);
			ys[i] = (int) (Math.sin(o) * j) + 3;
		}
		shapes[0] = new Polygon(xs, ys, 10);
		return shapes;
	}
}
