package siat.sfu.cs.labs;
import processing.core.*;

public class MyBall {

	PApplet p;
	
	// coordinates
	float x;
	float y;
	
	int size;
	
	// speed
	float velocityX;
	float velocityY;
	
	float randomX;
	float randomY;
	
	// constructor
	public MyBall(PApplet pApplet){
		this.p = pApplet;

		this.x = p.random(p.width);
		this.y = p.random(p.height);
		
		// initialize the velocity to be between -2 to 2;
		this.velocityX = p.random(-2,2);
		this.velocityY = p.random(-2,2);
		
		this.size = (int) p.random(16, 32);
	}	
	
	/**
	 * this method check if a ball has collided with another ball
	 * @param otherBall the ball we are detecting if it has collided against
	 */
	public void collision(MyBall otherBall){
		
		float distanceBetweenBalls = p.dist(x,y, otherBall.x, otherBall.y);
		float totalRadiusBetweenBalls = size/2 + otherBall.size/2;
		
		if ( distanceBetweenBalls < totalRadiusBetweenBalls ){
			
			// angle of collision
			float angle = p.atan2( y-otherBall.y , x-otherBall.x);
			
			// change velocity of our ball
			velocityX = 2 * p.cos(angle);
			velocityY = 2 * p.sin(angle);
			
			// now change the velocity of the other ball
			otherBall.velocityX = 2 * p.cos( angle - p.PI); // minus PI since opposite
			otherBall.velocityY = 2 * p.sin( angle - p.PI); // minus PI since opposite
		}
		
	}
	
	/**
	 * this method checks if the ball hits one of the walls
	 */
	public void walls(){
		
		// check if the ball hits the right wall
		if( x > p.width - size/2 ){
			
			// to prevent flickering in the edges (since when x,y randomly changes, it went way past the boundary 
			// that even when flipped, it is still passed.  SO reset ball onto frame edge;
			x = p.width - size/2;
			
			velocityX *= -1; // same as velocityX = velocity*(-1)
		}
		
		// left wall
		else if ( x < size/2){
			x = size/2;
			velocityX *= -1;
		}
		
		// hit top wall
		else if ( y < size/2 ){
			y = size/2 ;
			velocityY *= -1;
		}
		
		// hit bottom wall
		else if ( y > p.height - size/2){
			 y = p.height - size/2;
			velocityY *= -1;
		}
		
	}
	
	public void drawMe(){
		p.fill(0,32);
		p.noStroke();
		p.ellipse(x, y, size, size);
		
		dance();
	}
	
	public void dance(){
		this.randomX = p.random(-3,3);
		this.randomY = p.random(-3,3);
		
	}
	public void updateMe(){
		this.x += velocityX + randomX;
		this.y += velocityY + randomY;
	}
	
}
