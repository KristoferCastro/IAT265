package lab5;

import processing.core.*;

public class DancingBallMain extends PApplet {
	int numBalls = 64;
	BallAttract[] ballList = new BallAttract[numBalls];
	
	int sizeBallsRandom;
	BallRandom[] ballsRandom;
	

	public void setup() {
	  size(600, 400);
	  noStroke();
	  
	  sizeBallsRandom = 20;
	  ballsRandom = new BallRandom[sizeBallsRandom];
	  
	  for(int i = 0; i < sizeBallsRandom; i++){
		  ballsRandom[i] = new BallRandom(this);
	  }
	  
	  for (int i = 0; i < numBalls; i++) {
	    ballList[i] = new BallAttract(this); //creates an instance of Ball
	    //and places it at index i in ballList array
	  }
	}

	public void draw() {
	  //background(255);
	  fill(0, 32);
	  rect(0, 0, width, height);
	  
	  for (int i = 0; i < numBalls; i++) {
	    for (int j = i + 1; j < numBalls; j++) {
	      //ballList[i].collision( ballList[j] );
	    }
	    ballList[i].update();
	    ballList[i].walls();
	    ballList[i].drawMe();
	  }
	  
	  
	  for(int i = 0; i < sizeBallsRandom; i++){
		  ballsRandom[i].update();
		  ballsRandom[i].walls();
		  ballsRandom[i].drawMe();
	  }
	  
	}
}