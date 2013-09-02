package com.johnkapri.spacegame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.johnkapri.spacegame.entity.EntityPlayer;
import com.johnkapri.spacegame.entity.EntityStart;
import com.johnkapri.spacegame.gfx.PlayField;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 360;
	public static final int HEIGHT = (WIDTH / 2) * 3;
	public static final Dimension DIMENSIONS = new Dimension(WIDTH, HEIGHT);
	public static final String NAME = "spacegame";

	public Thread main;
	public JFrame frame = new JFrame(NAME);
	public InputHandler input = new InputHandler();
	public PlayField p;
	private boolean running = false;

	public Game() {
//		setMinimumSize(Game.DIMENSIONS);
//		setMaximumSize(Game.DIMENSIONS);
//		setPreferredSize(Game.DIMENSIONS);
		requestFocus();
		addFocusListener(input);
		addKeyListener(input);
	}
	
	@Override
	public void run() {
		p = new PlayField();
		p.addEntitiy(new EntityPlayer(input, 30, Game.HEIGHT - 50));
		p.addEntitiy(new EntityStart(WIDTH / 2, HEIGHT / 2));
		long lastTick = System.nanoTime();
		double nsPerTick = 1000000000D / 40D;

		long lastFrame = System.nanoTime();
		double nsPerFrame = 1000000000D / 60D;

		int ticksPS = 0;
		int framesPS = 0;
		
		long lastTimer = System.currentTimeMillis();
		double deltaTick = 0;
		double deltaFrame = 0;

		while (running) {
			long now = System.nanoTime();
			deltaTick += (now - lastTick) / nsPerTick;
			lastTick = now;

			deltaFrame += (now - lastFrame) / nsPerFrame;
			lastFrame = now;

			while (deltaTick >= 1) {
				ticksPS++;
				p.tick();
				deltaTick -= 1;
				render();
			}
			
			while (deltaFrame >= 1) {
				framesPS++;
				deltaFrame -= 1;
				render();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println("Ent: " + p.getEntityAmmount());
				System.out.println("FPS: " + framesPS);
				System.out.println("TPS: " + ticksPS);
				System.out.println("-------");
				framesPS = 0;
				ticksPS = 0;
			}
			
			try {
				Thread.sleep(10L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		if(p != null) {
			p.render(g);
		}
		
		g.dispose();
		bs.show();
	}

	public void start() {
		main = new Thread(this, Game.NAME);
		running = true;
		main.start();
	}

	public void stop() {
		running = false;
		try {
			main.join(200L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
