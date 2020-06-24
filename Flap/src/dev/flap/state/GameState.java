package dev.flap.state;

import java.awt.Color;
import java.awt.Graphics;

import dev.flap.Game;
import dev.flap.bird.Bird;
import dev.flap.tubeset.TubeSet;

public class GameState extends State {
	
	private boolean running = true;
	
	private int hole = 300;
	
	private TubeSet tubeset[] = new TubeSet[3];
	private Bird bird;
	
	
	private void resetHole() {
		int newHole = (int)Math.floor(System.nanoTime()%400 + 100);
		if(Math.abs(newHole-hole)>200) {
			if(newHole>hole) {
				hole += 200;
			}else {
				hole -= 200;
			}
		}else {
			hole = newHole;
		}
	}
	
	
	public GameState(Game game) {
		super(game);
		for(int i=0 ; i<3; i++) {
			tubeset[i] = new TubeSet(i*300+400,hole);
			tubeset[i].resetTubeSet(hole,i*300+400);
			resetHole();
		}
		bird = new Bird(game);
	}
	
	
	@Override
	public void update() {
		if(running) {
			for(int i = 0; i < 3; i++) {
				tubeset[i].update();
				if(tubeset[i].readyToReset()) {
					resetHole();
					tubeset[i].resetTubeSet(hole, 800);
				}
				
				if(tubeset[i].collision(bird.getY())) {
					running = false;
				}
			}
			
			bird.update();
		}else {
			if(game.getKeyManager().restart) restart();
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		
		if(running) {
			for(int i = 0; i < 3; i++) {
				tubeset[i].render(g);
			}
			
			bird.render(g);
		}else {
			g.setColor(new Color(255,0,0));
			g.fillRect(100, 100, 300, 400);
		}
	}
	
	
	private void restart() {
		running = true;
		for(int i=0 ; i<3; i++) {
			tubeset[i] = new TubeSet(i*300+400,hole);
			tubeset[i].resetTubeSet(hole,i*300+400);
			resetHole();
		}
		bird = new Bird(game);
	}

}
