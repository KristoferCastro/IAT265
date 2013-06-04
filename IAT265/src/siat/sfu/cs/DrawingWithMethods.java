package siat.sfu.cs;
import processing.core.*;
/*
 * Drawing and animation a fish using methods.
 * @author Kristofer Ken Perez Castro
 */
public class DrawingWithMethods extends PApplet{

	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 500;
	private final int FRAME_MARGIN = 10;
	private final int SIZE = 100;
	private int speedX;
	
	private int bodyW = 250;
	private int bodyH = 0;
	private int headW = 200;
	private int headH = 100;
	
	// center of the body is the anchor point
	private int fishX;
	private int fishY;
	
	public void setup(){
		background(0);
		speedX = 3;
		bodyW = 250;
		bodyH = 0;
		headW = 200;
		headH = 100;
		fishX = bodyW/2 + 80;
		fishY = FRAME_HEIGHT/2 - FRAME_MARGIN;
		size(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	public void draw(){
		background(0);
		smooth();
		drawPond();
		drawFish();
		update();
	}
	
	private void update(){
		if (isAtEdge()){
			speedX = speedX * -1;
		}
		fishX = fishX + speedX;
	}
	
	private boolean isAtEdge(){
		boolean result = false;
		stroke(0);
		//line(headCollisionX, headCollisionY, headCollisionX, headCollisionY-200);
		if (fishX+bodyW/2+headW/2-30> FRAME_WIDTH-FRAME_MARGIN 
				|| fishX-bodyW/2-headW/2+30 < FRAME_MARGIN){
			return true;
		}
		return result;
	}
	
	private void drawPond(){
		fill(82,127,166);
		rect(FRAME_MARGIN, FRAME_MARGIN, FRAME_WIDTH-2*FRAME_MARGIN, FRAME_HEIGHT-2*FRAME_MARGIN);
	}
	
	private void drawFish(){
		translate(fishX,fishY);
		if ( speedX < 0 ){
			rotate(PI);
			scale(1,-1);
		}
		drawTail();
		drawFins();
		drawBody();
		drawHead();
	}
	
	private void drawBody(){
		stroke(0);
		fill(255,50,25);
		int bodyX1 = -bodyW/2;
		int bodyY1 = 0;
		int bodyX2 = bodyW/2;
		int bodyY2 = bodyY1;
		
		int ctrlptX1 = bodyX1;
		int ctrlptY1 = 500;
		int ctrlptX2 = bodyX2;
		int ctrlptY2 = 500;
		curve(ctrlptX1,ctrlptY1+400, bodyX1, bodyY1, bodyX2, bodyY2, ctrlptX2 ,ctrlptY2+400);
		curve(ctrlptX1,-ctrlptY1, bodyX1, bodyY1, bodyX2, bodyY2, ctrlptX2 ,-ctrlptY2);
		
		// scale decorations
		// triangle scales
		int shiftY = -10;
		int shiftX = 30;
		for(int k = 0; k < 3; k++){
			fill(200,125,125,125);
			noStroke();
			pushMatrix();
			translate(-bodyW/2 + shiftX,shiftY-15);
			shiftX += 15;
			shiftY += 25;
			triangle(0,0,0,30,20,15);
			
			for(int i = 0; i < 5 - k; i++){
				translate(40,0);
				pushMatrix();
				rotate((float)Math.toRadians(30+i*20));
				triangle(0,0,0,30,20,15);
				popMatrix();
			}
			popMatrix();
			
		}
		shiftY = -20;
		shiftX = 30;
		for(int k = 0; k < 3; k++){
			fill(200,125,125,125);
			noStroke();
			pushMatrix();
			translate(-bodyW/2 + shiftX,shiftY);
			shiftX += 15;
			shiftY -= 25;
			triangle(0,0,0,30,20,15);
			
			for(int i = 0; i < 5 - k; i++){
				translate(40,0);
				pushMatrix();
				rotate((float)Math.toRadians(30+i*20));
				triangle(0,0,0,30,20,15);
				popMatrix();
			}
			popMatrix();
			
		}
		noFill();
	}
	
	private void drawHead(){
		fill(255,50,25);
		stroke(0);
		int headX = headW/2-10;
		int headY = -20;		
		arc(headX,headY,headW,headH,3*PI/2, 2*PI);
		arc(headX,headY,headW,headH,0, PI/2);
		
		// draw eyes
		int eyesX = headX + headW/4;
		int eyesY = headY - 25;
		final int eyeSize = 50;
		fill(255);
		ellipse(eyesX,eyesY, eyeSize, eyeSize);
		
		// pupils
		fill(0);
		final int pupilSize = 15;
		ellipse(eyesX,eyesY, pupilSize, pupilSize);
		
		// some head decoration
		int gillsX = headX + 10;
		int gillsY = headY;

		noFill();
		stroke(0);
		strokeWeight(2);

		int size = 70;
		// gill 1
		arc(gillsX,gillsY, size/2, size/2, 3*PI/2 + 1*PI/4, 2*PI);
		arc(gillsX,gillsY, size/2, size/2, 0, PI/2);
		
		// gill 2
		arc(gillsX+10,gillsY+10, size/2, size/2, 3*PI/2 + 2*PI/4, 2*PI);
		arc(gillsX+10,gillsY+10, size/2, size/2, 0, PI/2);
		noStroke();
	}
	
	private void drawTail(){
		fill(255,50,25);
		stroke(0);
		
		int tailX = -bodyW/2 + 30;
		int tailY = 0;
		int tailH = 100;
		int tailW = 80;
		
		triangle(tailX, tailY, tailX-tailW, tailY + tailH/2, tailX-tailW, tailY - tailH/2);
		
		// tail decorations
		noFill();
		stroke(0);
		fill(200,50,10);
		curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2, tailX+100, tailY+150);
		fill(0);
		curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+10, tailX+100, tailY+150);
		fill(200,50,10);
		curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+20, tailX+100, tailY+150);
		fill(0);
		curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+30, tailX+100, tailY+150);
		fill(200,50,10);
		curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+40, tailX+100, tailY+150);
		fill(0);
		curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+50, tailX+100, tailY+150);
		fill(200,50,10);
		curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+60, tailX+100, tailY+150);
		fill(0);
		curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+70, tailX+100, tailY+150);
		fill(200,50,10);
		curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+80, tailX+100, tailY+150);
		fill(0);
		curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+90, tailX+100, tailY+150);
		fill(200,50,10);
		curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+100, tailX+100, tailY+150);
		fill(0);
	}
	
	private void drawFins(){
		int ctrlptX1 = 60;
		int ctrlptY1 = 500;
		int ctrlptX2 = 0;
		int ctrlptY2 = 500;
		int finWidth = 40;
		int finX = -58;
		int finY = 40;
		stroke(0);
		fill(200,50,10);
		curve(ctrlptX1,ctrlptY1-800, finX, finY, finX+finWidth+5, 60, ctrlptX2 ,ctrlptY2-500);
		finX = finX + 80;
		finY = finY - 10;
		finWidth = 100;
		curve(ctrlptX1+300,ctrlptY1-1200, finX+15, finY, finX+finWidth-30, finY-10, ctrlptX2 ,ctrlptY2-800);
				
		// fin decorations
		noFill();
		stroke(0);
		// bottom closest to head fin
		line(finX+10, finY, finX+10, finY+58);
		line(finX+10, finY, finX+20, finY+58);
		line(finX+10, finY, finX+30, finY+55);
		line(finX+10, finY, finX+40, finY+50);
		line(finX+10, finY, finX+50, finY+40);
		line(finX+10, finY, finX+60, finY+28);
		line(finX+10, finY, finX+69, finY+10);
		
		// bottom closest to tail fin
		finX = finX - 50;
		line(finX, finY, finX, finY+40);
		line(finX, finY, finX-10, finY+43);
		line(finX, finY, finX-20, finY+45);
		line(finX, finY, finX-30, finY+40);
		
		// head fin
		fill(0);
		finWidth = 150;
		int finX1 = -80;
		int finY1 = -80;
		int finX2 = finX1+finWidth+20;
		int finY2 = -50;
		ctrlptX1 = finX1 + finWidth/2;
		ctrlptY1 = finY1 + 500;
		ctrlptX2 = ctrlptX1;
		ctrlptY2 = ctrlptY1;
		curve(ctrlptX1, ctrlptY1, finX1, finY1, finX2, finY2, ctrlptX2, ctrlptY2);
		noStroke();		
	}
	
	
}
