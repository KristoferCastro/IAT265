package siat.sfu.cs;
import processing.core.*;

/*
 * Part II : Drawing using Classes
 * @author Kristofer Ken Perez Castro
 */
public class DrawingUsingClass extends PApplet{

	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 500;
	private final int FRAME_MARGIN = 10;
	
	private Fish myFish;
	
	public void setup(){
		background(0);
		smooth();
		size(FRAME_WIDTH, FRAME_HEIGHT);
		
		// initialize my fish
		myFish = new Fish(this, 3,  125 + 80, FRAME_HEIGHT/2 - FRAME_MARGIN);
	}
	
	private void drawPond(){
		fill(82,127,166);
		rect(FRAME_MARGIN, FRAME_MARGIN, FRAME_WIDTH-2*FRAME_MARGIN, FRAME_HEIGHT-2*FRAME_MARGIN);
	}
	
	public void draw(){
		background(0);
		smooth();
		drawPond();
		myFish.drawFish();
		update();
	}
	
	public void update(){
		myFish.update();
	}
}
