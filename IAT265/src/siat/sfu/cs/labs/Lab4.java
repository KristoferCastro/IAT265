package siat.sfu.cs.labs;
import processing.core.*;

/**
 * Dancing Ball using Object Orientated Design
 * @author Kristofer Ken PErez Castro
 * @date 5/28/2013
 */
public class Lab4 extends PApplet{

	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 500;
	
	private final int numberOfBalls = 64;
	
	// create an array of size 'numberOfBalls' holding Ball objects
	Ball[] ballArray = new Ball[numberOfBalls];

	public void setup(){
		size(FRAME_WIDTH,FRAME_HEIGHT);
		background(255);
		smooth();
		
		// instantiate my Ball array so we don't get a null pointer exception
		for(int i = 0 ; i < ballArray.length; i++){
			
			// let's instantiate a Ball in position/index i
			ballArray[i] = new Ball(this);
			
		}
	}
	
	public void draw(){
		
		// ensures that we don't leave any traces of ball (every single frame we erase old drawings)
		background(255);
		
		for(int i = 0 ; i < ballArray.length; i++){
			
			Ball currentBall = ballArray[i];
			
			// handle ball collisions (for each ball, check with every other ball)
			for(int j = i + 1; j < numberOfBalls; j++){
				
				Ball otherBall = ballArray[j];
				
				currentBall.collision(otherBall);
			}
			
			// let's call the draw method of each ball so all Balls will be drawn
			currentBall.drawMe();
			
			// call the method that does wall collision handling
			currentBall.walls();
			
			// call this method so that all the balls animate
			currentBall.updateMe();
		}
	}
	
}
