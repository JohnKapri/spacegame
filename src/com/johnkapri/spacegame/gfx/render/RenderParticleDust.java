package com.johnkapri.spacegame.gfx.render;

import java.awt.Polygon;
import java.util.Random;

public class RenderParticleDust extends Render{

	Random rand = new Random();
	
	public RenderParticleDust(int id) {
		super(id);
		int ammount = 4 + rand.nextInt(4);
		for(int i = 0; i < ammount; i++) {
			Polygon p = new Polygon();
			p.addPoint(3 + rand.nextInt(5), 3 + rand.nextInt(5));
			p.addPoint(3 + rand.nextInt(5), 3 + rand.nextInt(5));
			p.addPoint(3 + rand.nextInt(5), 3 + rand.nextInt(5));
			shapes.add(p);
		}
	}
}
