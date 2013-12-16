package com.johnkapri.spacegame.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import com.johnkapri.spacegame.Game;

public class StartLogo implements Screen {

	List<Shape> logo = new ArrayList<Shape>();
	
	int ticks;
	Game game;

	private static float SCALE = 3;

	public StartLogo(Game game) {
		this.game = game;
		logo.add(new Line2D.Float(-15F * SCALE, -12.5F * SCALE, -0F * SCALE,
				-12.5F * SCALE));
		logo.add(new Line2D.Float(-0F * SCALE, -12.5F * SCALE, -0F * SCALE,
				2.5F * SCALE));
		logo.add(new Line2D.Float(-0F * SCALE, 2.5F * SCALE, 15F * SCALE,
				-12.5F * SCALE));
		logo.add(new Line2D.Float(5F * SCALE, -2.5F * SCALE, 15F * SCALE,
				10F * SCALE));
		logo.add(new Arc2D.Float(-15F * SCALE, (2.5F - 7.5F) * SCALE,
				15 * SCALE, 15 * SCALE, 180, 180, Arc2D.OPEN));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		g.setColor(Color.GREEN);
		g.setFont(new Font("Courier New, Courier", 12, 12));

		Graphics2D g2 = (Graphics2D) g;
		g2.translate(Game.WIDTH / 2, Game.HEIGHT / 2);
		g2.scale(2D, 2D);
		renderLogo(g2);
		g2.scale(0.5D, 0.5D);
		String cr = "® John Kapri, 2013";
		g2.drawString(
				cr,
				- (float) g2
						.getFont()
						.getStringBounds(cr,
								new FontRenderContext(null, false, false))
						.getWidth() / 2, 30F * SCALE);
		g2.translate(-Game.WIDTH / 2, -Game.HEIGHT / 2);
	}

	@Override
	public void tick() {
		ticks++;
		if(ticks > 100) {
			game.displayPlayfield();
		}
	}

	private void renderLogo(Graphics2D g) {
		for (Shape s : logo) {
			g.draw(s);
		}
	}
}
