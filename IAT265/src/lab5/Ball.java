package lab5;

import processing.core.PApplet;

public class Ball {
	PApplet p;
	float x, y, velocityX, velocityY, wave, waveSpeed, amp;
	int ballSize;
	int ballColour;

	float accelerationX, accelerationY, damp;
	
	Ball(PApplet parent) 
	{
		this.p = parent;
		x = p.random(p.width);
		y = p.random(p.height);
		
		velocityX = p.random(-2, 2);
		velocityY = p.random(-2, 2);
		
//		wave = p.random(p.TWO_PI);
//		waveSpeed = p.random(0.1f, 0.4f); //by default floating numbers are type of double, append 'f' to make them float type
//		amp = p.random(1, 4);

		ballSize = (int) p.random(16, 32);
		ballColour = p.color(p.random(128) + 127, p.random(128), 0);
		
		accelerationX = 0;
		accelerationY = 0;
		damp = 0.98f;
	}
	
	// a method for ball to ball collision
	void collision(Ball other) 
	{
		if (p.dist(x, y, other.x, other.y) < ballSize / 2 + other.ballSize / 2) 
		{
			float angle = p.atan2(y - other.y, x - other.x);
			velocityX = 2 * p.cos(angle);
			velocityY = 2 * p.sin(angle);
			other.velocityX = 2 * p.cos(angle - p.PI);
			other.velocityY = 2 * p.sin(angle - p.PI);
			
		}
	}

	// a method for updating a ball position
	void update() {
//		wave += waveSpeed;
		
		// add acceleration to our velocity
		velocityX += accelerationX;
		velocityY += accelerationY;
		
		x += velocityX;
		y += velocityY; // + p.sin(wave) * amp;
		
		// eventually stops (dampens)
		velocityX *= damp;
		velocityY *= damp;
	
		accelerationX = 0;
		accelerationY = 0;
	}

	// a method for handling wall collision
	void walls() 
	{
		// right wall
		if (x > p.width - ballSize / 2) 
		{
			x = p.width - ballSize / 2;
			velocityX *= -1;
		}
		// left wall
		if (x < ballSize / 2) 
		{
			x = ballSize / 2;
			velocityX *= -1;
		}
		// bottom wall
		if (y > p.height - ballSize / 2) 
		{
			y = p.height - ballSize / 2;
			velocityY *= -1;
		}
		// top wall
		if (y < ballSize / 2)
		{
			y = ballSize / 2;
			velocityY *= -1;
		}
	}

	// a method for drawing a ball
	void drawMe() {
		p.fill(ballColour);
		p.pushMatrix();
		p.translate(x, y);
		p.ellipse(0, 0, ballSize, ballSize);
		p.popMatrix();
	}
}// class