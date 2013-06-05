package lab5;
import processing.core.*;

/*
 * This class is a subclass of ball that attracts balls wherever your mouse is pointed to.
 */
public class BallAttract extends Ball {

	PApplet p;
	
	float angle, speed;
	
	public BallAttract(PApplet p) {
		super(p);
		this.p = p;
		speed = 2;
	}
	
	public void update(){
		attract();
		super.update();
	}
	
	public void attract(){
		angle = p.atan2(p.mouseY - y, p.mouseX-x);
		
		accelerationX = 2*p.cos(angle);
		accelerationY = 2*p.sin(angle);
	}
}
