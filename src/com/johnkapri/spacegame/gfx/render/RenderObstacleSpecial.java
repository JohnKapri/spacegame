package com.johnkapri.spacegame.gfx.render;


public class RenderObstacleSpecial extends RenderObstacle{

	public RenderObstacleSpecial(int id) {
		super(id);
		shapes.add(RenderPresets.getStarShapes()[0]);
	}
}
