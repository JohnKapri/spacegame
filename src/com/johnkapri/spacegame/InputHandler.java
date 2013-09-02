package com.johnkapri.spacegame;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener, FocusListener {
	public boolean[] keys = new boolean[65536];

	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public boolean use;
	
	public void focusGained(FocusEvent arg0) {
	}

	public void focusLost(FocusEvent arg0) {
		for (int i = 0; i < keys.length; i++) {
			keys[i] = false;
		}
		update();
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code > 0 && code < keys.length) {
			keys[code] = true;
			update();
		}
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code > 0 && code < keys.length) {
			keys[code] = false;
			update();
		}
	}

	public void keyTyped(KeyEvent arg0) {
	}
	
	private void update() {
		up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_NUMPAD8];
		down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_NUMPAD2];
		left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_NUMPAD4];
		right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_NUMPAD6];
		use = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_ENTER] || keys[KeyEvent.VK_NUMPAD0];
	}
}
