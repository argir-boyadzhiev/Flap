package dev.flap.tubeset;

import java.awt.Color;
import java.awt.Graphics;

public class TubeSet {
	
	private double x, xspeed = -3;
	private int gap, width = 80, hole = 70;
	//gap is the line in the centre of the hole
	
	public TubeSet(double x,int gap) {
		this.x = x;
		this.gap = gap;
	}
	
	
	public void update() {
		x += xspeed;
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(10,255,20));
		g.fillRect((int)Math.round(x), 0, width, gap - hole);
		g.fillRect((int)Math.round(x), gap + hole, width, 600 - (gap + hole));
	}
	
	public boolean readyToReset() {
		if(x<-80) return true;
		else return false;
	}
	
	public void resetTubeSet(int hole,double x) {
		gap = hole;
		this.x = x;
	}
	
	public boolean collision(double birdY) {
		if((x < 70 && x > 30 )&& ( birdY < gap - hole + 10|| birdY > gap + hole - 10)) return true;
		else return false;
	}
}