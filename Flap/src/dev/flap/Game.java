package dev.flap;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import dev.flap.state.*;

import dev.flap.display.Display;
import dev.flap.input.KeyManager;

public class Game implements Runnable{
	
	
	private Display display;
	
	private String title;
	private int width, height;
	private boolean running = false;
	
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private State gameState;
	
	private KeyManager keyManager;
	
	private void init() {
		display = new Display(title, width, height);
		display.getJFrame().addKeyListener(keyManager);
		gameState = new GameState(this);
		State.setState(gameState);
	}
	
	private void tick() {
		keyManager.update();
		if(State.getState()!=null) {
			State.getState().update();
		}
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		//DRAW
		
		if(State.getState()!=null) {
			State.getState().render(g);
		}
		
		//DRAW
		
		bs.show();
		g.dispose();
	}
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public void run() {
		
		int fps = 60;
		double timePerTick = 1_000_000_000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		init();
		
		while(running) {
			now = System.nanoTime();
			delta += (now-lastTime)/timePerTick;
			lastTime = now;
			
			if(delta>=1) {
				tick();
				render();
				delta--;
			}
		}
	}
	
	public synchronized void start() {
			if(running==true) return;
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	
	public synchronized void stop() {
		if(running==false) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
