package com.johnkapri.spacegame;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class GameLauncher {
	
	public static Game game;
	
	public static void main (String[] args) {
		game = new Game();
		
		game.setMinimumSize(Game.DIMENSIONS);
		game.setMaximumSize(Game.DIMENSIONS);
		game.setPreferredSize(Game.DIMENSIONS);
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLayout(new BorderLayout());
		game.frame.add(game, BorderLayout.CENTER);
		game.frame.pack();
		
        game.frame.setResizable(false);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

		game.start();
	}
}
