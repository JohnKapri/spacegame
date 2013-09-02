package com.johnkapri.spacegame;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class GameApplet extends Applet {

	Game game = new Game();

	@Override
	public void init() {
		setLayout(new BorderLayout());
		setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
		setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
		setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
		add(game, BorderLayout.CENTER);
		setVisible(true);
	}

	@Override
	public void start() {
		game.start();
	}

	@Override
	public void stop() {
		game.stop();
	}
}
