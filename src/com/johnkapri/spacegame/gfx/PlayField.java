package com.johnkapri.spacegame.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.johnkapri.spacegame.Game;
import com.johnkapri.spacegame.InputHandler;
import com.johnkapri.spacegame.entity.Entity;
import com.johnkapri.spacegame.entity.EntityObstacle;
import com.johnkapri.spacegame.entity.EntityObstacleSpecial;
import com.johnkapri.spacegame.entity.EntityPlayer;
import com.johnkapri.spacegame.entity.EntityStart;
import com.johnkapri.spacegame.gfx.render.Render;

public class PlayField implements Screen {

	public static Color defaultColor = Color.GREEN;
	public static Color defaultBackground = Color.BLACK;

	List<Entity> entities = new ArrayList<Entity>();
	List<Entity> newEntities = new ArrayList<Entity>();
	Random rand = new Random();

	private InputHandler input;
	private EntityPlayer player;

	int score = 0;
	private boolean useReleased = true;
	boolean running = false;
	boolean firstRun = true;
	boolean gameOver = false;
	int gameOverTimer;

	int ticks;
	boolean display;
	int slomoEffect = 0;
	int slomoCount = 0;
	int slomoTimer;

	public PlayField(InputHandler input) {
		this.input = input;
	}

	@Override
	public void tick() {
		ticks++;
		entities.addAll(newEntities);
		newEntities.clear();

		if (ticks % 60 == 0) {
			display = !display;
		}
		if (running) {
			firstRun = false;
			spawnObstacle();
		}

		if (slomoTimer > 0) {
			slomoTimer--;
		}
		if (slomoTimer <= 0) {
			slomoEffect = 0;
		}

		player.tick(this);
		if (slomoCount <= 0) {
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				if (e != player) {
					e.tick(this);
				}
				slomoCount = slomoEffect;
			}
		}
		slomoCount--;

		if (input.use) {
			if (gameOver && useReleased && (gameOverTimer <= 0)) {
				reset();
			}
			useReleased = false;
		} else {
			useReleased = true;
		}
		
		if(gameOverTimer > 0) {
			gameOverTimer--;
		}
	}

	@Override
	public void render(Graphics g) {
		if (slomoEffect > 0) {
			g.setColor(new Color(0.0F, 0.0F, 0.0F, 0.051F));
		} else {
			g.setColor(defaultBackground);
		}
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		g.setColor(defaultColor);
		g.setFont(new Font("Courier New, Courier", 12, 12));

		if (firstRun) {
			g.drawString("Use WASD to move", 40, 60);
			g.drawString("Use SPACE to shoot", 40, 76);
			g.drawString("Shoot as many blocks as possible", 40, 92);
			g.drawString("Boxes with a star contain special stuff", 40, 108);
			g.drawString("Avoid getting hit by anything!", 40, 124);
		} else {
			if (gameOver) {
				g.drawString(
						"GAME OVER",
						Game.WIDTH
								/ 2
								- (int) g
										.getFont()
										.getStringBounds(
												"GAME OVER",
												new FontRenderContext(null,
														false, false))
										.getWidth() / 2, Game.HEIGHT / 2 - 50);
				g.drawString(
						"Final score: " + score,
						Game.WIDTH
								/ 2
								- (int) g
										.getFont()
										.getStringBounds(
												"Final score " + score,
												new FontRenderContext(null,
														false, false))
										.getWidth() / 2, Game.HEIGHT / 2 - 30);
				if (display) {
					g.drawString(
							"Press SPACE to continue",
							Game.WIDTH
									/ 2
									- (int) g
											.getFont()
											.getStringBounds(
													"Spress SPACE to continue",
													new FontRenderContext(null,
															false, false))
											.getWidth() / 2, Game.HEIGHT / 2);
				}
			} else {
				g.drawString("Score: " + score, 20, 20);
			}
		}

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
		if (rand.nextInt(100) <= 8) {
			for (int i = 0; i < 10; i++) {
				int xPos = rand.nextInt(Game.WIDTH - 30) + 15;
				Entity o = new EntityObstacle(xPos, 0);
				if (rand.nextInt(100) <= 2) {
					o = new EntityObstacleSpecial(xPos, 0);
				}
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

	public boolean addEntitiy(Entity e) {
		if (entities.contains(e) || newEntities.contains(e)) {
			return false;
		}
		newEntities.add(e);
		if (e instanceof EntityPlayer) {
			this.player = (EntityPlayer) e;
		}
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

	public void setSlomo(int factor, int time) {
		slomoEffect = factor;
		slomoTimer = time;
	}

	public void startRound() {
		running = true;
	}

	public void reset() {
		if (!running) {
			slomoTimer = 0;
			slomoEffect = 1;
			score = 0;
			gameOver = false;
			addEntitiy(new EntityPlayer(input, 30, Game.HEIGHT - 50));
			addEntitiy(new EntityStart(Game.WIDTH / 2, Game.HEIGHT / 2));
		}
	}

	public void gameOver() {
		running = false;
		endRound();
		gameOver = true;
		gameOverTimer = 100;
	}

	public void endRound() {
		int j = entities.size() - 1;
		for (int i = 0; i < j; i++) {
			int k = j - i;
			if (entities.get(k) != null
					&& entities.get(k) instanceof EntityObstacle) {
				destroyEntity(entities.get(k));
			}
		}
	}
}
