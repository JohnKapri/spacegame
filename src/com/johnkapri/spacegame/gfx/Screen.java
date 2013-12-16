package com.johnkapri.spacegame.gfx;

import java.awt.Graphics;

public interface Screen {

	public abstract void render(Graphics g);
	
	public abstract void tick();
}
