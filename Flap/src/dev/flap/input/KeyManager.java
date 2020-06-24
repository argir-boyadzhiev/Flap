package dev.flap.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	public boolean jump,restart;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void update() {
		jump = keys[KeyEvent.VK_UP]||keys[KeyEvent.VK_SPACE];
		restart = keys[KeyEvent.VK_ENTER];
	}
	


	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
}
