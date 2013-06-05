package lab5;
import processing.core.*;

public class BallRandom extends Ball {

	PApplet p;
	
	float angle;
	
	BallRandom(PApplet p) {
		super(p);
		
		this.p = p;
		
		// override the parent color
		ballColour = p.color(p.random(128),p.random(128),0);
	}
	
	void update(){
		super.update();
		randomMove();
	}
	
	void randomMove(){
		
		// just some random angle
		angle = p.noise(x,y)*32 - 16;
		
		accelerationX = 2 * p.cos(angle);
		accelerationY = 2 * p.cos(angle);
	}
}
