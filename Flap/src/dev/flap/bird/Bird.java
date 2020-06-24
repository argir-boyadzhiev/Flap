package dev.flap.bird;

import java.awt.Color;
import java.awt.Graphics;

import dev.flap.Game;

public class Bird {
	
	private Game game;
	private double yVelocity=0,y=300,gravity=0.3;
	private int x=50,radius=20;
	
	public Bird(Game game) {
		this.game = game;
	}
	
	
	public void update() {
		if(game.getKeyManager().jump) {
			yVelocity = 5;
		}
		movement();
	}
	
	private void movement() {
		y -= yVelocity;
		if(yVelocity>-10) {
			yVelocity -= gravity;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(255,0,0));
		g.fillOval(x-radius, (int)Math.round(y), radius*2, radius);
	}
	
	public double getY() {
		return y;
	}
}
