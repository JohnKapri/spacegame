package com.johnkapri.spacegame.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.johnkapri.spacegame.Game;
import com.johnkapri.spacegame.entity.Entity;
import com.johnkapri.spacegame.entity.EntityObstacle;
import com.johnkapri.spacegame.entity.EntityParticle;
import com.johnkapri.spacegame.gfx.render.Render;

public class PlayField {

	public static Color defaultColor = Color.GREEN;
	public static Color defaultBackground = Color.BLACK;

	List<Entity> entities = new ArrayList<Entity>();
	Random rand = new Random();

	int score = 0;
	boolean running = false;
	boolean firstRun = true;

	public void tick() {
		if (running) {
			firstRun = false;
			spawnObstacle();
		}

		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick(this);
			if (!(e instanceof EntityParticle)) {
				for (Entity e1 : getCollisionListFor(e)) {
					e.onCollide(e1, this);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(defaultBackground);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		g.setColor(defaultColor);
		g.setFont(new Font("Courier", 12, 12));

		if (firstRun) {
			g.drawString("Use WASD to move", 40, 60);
			g.drawString("Use SPACE to shoot", 40, 76);
			g.drawString("Shoot as many blocks as possible", 40, 92);
		}
		
		g.drawString("Score: " + score, 20, 20);

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}
	}

	public List<Entity> getCollisionListFor(Entity e) {
		List<Entity> r = new ArrayList<Entity>();
		for (int j = 0; j < entities.size(); j++) {
			Entity e1 = entities.get(j);
			if (e != e1 && Render.doEntitiesCollide(e, e1)) {
				r.add(e1);
			}
		}
		return r;
	}

	public boolean doesCollideWith(Entity e, Entity e1) {
		for (Entity e2 : getCollisionListFor(e)) {
			if (e2 == e1) {
				return true;
			}
		}
		return false;
	}

	private void spawnObstacle() {
		if (rand.nextInt(100) <= 2) {
			for (int i = 0; i < 10; i++) {
				int xPos = rand.nextInt(Game.WIDTH);
				if (xPos > 15 && xPos < Game.WIDTH - 15) {
					EntityObstacle o = new EntityObstacle(xPos, 0);
					Object[] e = getCollisionListFor(o).toArray();
					if (e.length > 0) {
						for (int j = 0; j < e.length; j++) {
							if (!(e[j] instanceof EntityObstacle)) {
								addEntitiy(o);
								return;
							}
						}
					} else {
						addEntitiy(o);
						return;
					}
				}
			}
		}
	}

	public boolean addEntitiy(Entity e) {
		if (entities.contains(e)) {
			return false;
		}
		entities.add(e);
		return true;
	}

	public boolean destroyEntity(Entity e) {
		if (!entities.contains(e)) {
			return false;
		}
		if (!e.onDestroy(this)) {
			entities.remove(e);
		}
		return true;
	}

	public int getEntityAmmount() {
		return entities.size();
	}

	public void score(int i) {
		score += i;
	}
	
	public void startRound() {
		if(!running) {
			score = 0;
			running = true;
		}
	}
	
	public void endRound() {
		running = false;
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) != null && entities.get(i) instanceof EntityObstacle) {
				destroyEntity(entities.get(i));
			}
		}
	}
}
