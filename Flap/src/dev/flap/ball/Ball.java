package dev.flap.ball;
import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	private int radius,x,y;
	private double realx,realy,xspeed,yspeed;
	private Color color;
	
	public Ball(double realx, double realy, int radius,double xspeed, double yspeed, Color color) {
		this.realx = realx;
		this.realy = realy;
		this.radius = radius;
		this.xspeed = xspeed;
		this.yspeed = yspeed;
		this.color = color;
		updateIntegerPositions();
	}
	
	public void update(int width, int height) {
		wallDetection(width,height);
		move();
		updateIntegerPositions();
	}
	
	public void render(Graphics g) {
		draw(g);
	}
	
	private void updateIntegerPositions() {
		x = (int)Math.round(realx);
		y = (int)Math.round(realy); 
	}
	
	private void move() {
		realx += xspeed;
		realy += yspeed;
	}
	
	private void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x-radius, y-radius, radius*2, radius*2);
	}
	
	private void wallDetection(int width, int height) {
		if(x+radius>width) xspeed = -1*Math.abs(xspeed);
		if(x-radius<0) xspeed = Math.abs(xspeed);
		if(y+radius>height) yspeed = -1*Math.abs(yspeed);
		if(y-radius<0) yspeed = Math.abs(yspeed);
	}
	
}
