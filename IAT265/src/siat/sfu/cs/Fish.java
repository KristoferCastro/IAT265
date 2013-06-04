package siat.sfu.cs;

import processing.core.PApplet;

/*
 * Fish object
 */
public class Fish{
	private PApplet p;
	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 500;
	private final int FRAME_MARGIN = 10;
	
	private int speedX;
	
	private int bodyW;
	private int bodyH;
	private int headW;
	private int headH;
	
	// center of the body is the anchor point
	private int fishX;
	private int fishY;
	
	/**
	 * Constructor
	 * @param p
	 * @param speedX speed of the fish
	 * @param x x coordinate of the fish
	 * @param y y coordinate of the fish
	 */
	public Fish(PApplet p, int speedX, int x, int y) {
		this.p = p;
		this.speedX = speedX;
		bodyW = 250;
		bodyH = 0;
		headW = 200;
		headH = 100;
		this.fishX = x;
		this.fishY = y;	
	}
	
	public void drawFish(){
		p.translate(fishX,fishY);
		if ( speedX < 0 ){
			p.rotate(p.PI);
			p.scale(1,-1);
		}
		drawTail();
		drawFins();
		drawBody();
		drawHead();
	}
	
	public void update(){
		if (isAtEdge()){
			speedX = speedX * -1;
		}
		fishX = fishX + speedX;
	}
	
	public int getX(){
		return this.fishX;
	}
	
	public int getY(){
		return this.fishY;
	}
	
	public void setX(int x){
		this.fishX = x;
	}
	
	public void setY(int y){
		this.fishY = y;
	}
	
	private boolean isAtEdge(){
		boolean result = false;
		p.stroke(0);
		//line(headCollisionX, headCollisionY, headCollisionX, headCollisionY-200);
		if (fishX+bodyW/2+headW/2-30> FRAME_WIDTH-FRAME_MARGIN 
				|| fishX-bodyW/2-headW/2+30 < FRAME_MARGIN){
			return true;
		}
		return result;
	}
	
	private void drawBody(){
		p.stroke(0);
		p.fill(255,50,25);
		int bodyX1 = -bodyW/2;
		int bodyY1 = 0;
		int bodyX2 = bodyW/2;
		int bodyY2 = bodyY1;
		
		int ctrlptX1 = bodyX1;
		int ctrlptY1 = 500;
		int ctrlptX2 = bodyX2;
		int ctrlptY2 = 500;
		p.curve(ctrlptX1,ctrlptY1+400, bodyX1, bodyY1, bodyX2, bodyY2, ctrlptX2 ,ctrlptY2+400);
		p.curve(ctrlptX1,-ctrlptY1, bodyX1, bodyY1, bodyX2, bodyY2, ctrlptX2 ,-ctrlptY2);
		
		// scale decorations
		// triangle scales
		int shiftY = -10;
		int shiftX = 30;
		for(int k = 0; k < 3; k++){
			p.fill(200,125,125,125);
			p.noStroke();
			p.pushMatrix();
			p.translate(-bodyW/2 + shiftX,shiftY-15);
			shiftX += 15;
			shiftY += 25;
			p.triangle(0,0,0,30,20,15);
			
			for(int i = 0; i < 5 - k; i++){
				p.translate(40,0);
				p.pushMatrix();
				p.rotate((float)Math.toRadians(30+i*20));
				p.triangle(0,0,0,30,20,15);
				p.popMatrix();
			}
			p.popMatrix();
			
		}
		shiftY = -20;
		shiftX = 30;
		for(int k = 0; k < 3; k++){
			p.fill(200,125,125,125);
			p.noStroke();
			p.pushMatrix();
			p.translate(-bodyW/2 + shiftX,shiftY);
			shiftX += 15;
			shiftY -= 25;
			p.triangle(0,0,0,30,20,15);
			
			for(int i = 0; i < 5 - k; i++){
				p.translate(40,0);
				p.pushMatrix();
				p.rotate((float)Math.toRadians(30+i*20));
				p.triangle(0,0,0,30,20,15);
				p.popMatrix();
			}
			p.popMatrix();
			
		}
		p.noFill();
	}
	
	private void drawHead(){
		p.fill(255,50,25);
		p.stroke(0);
		int headX = headW/2-10;
		int headY = -20;		
		p.arc(headX,headY,headW,headH,3*p.PI/2, 2*p.PI);
		p.arc(headX,headY,headW,headH,0, p.PI/2);
		
		// draw eyes
		int eyesX = headX + headW/4;
		int eyesY = headY - 25;
		final int eyeSize = 50;
		p.fill(255);
		p.ellipse(eyesX,eyesY, eyeSize, eyeSize);
		
		// pupils
		p.fill(0);
		final int pupilSize = 15;
		p.ellipse(eyesX,eyesY, pupilSize, pupilSize);
		
		// some head decoration
		int gillsX = headX + 10;
		int gillsY = headY;

		p.noFill();
		p.stroke(0);
		p.strokeWeight(2);

		int size = 70;
		// gill 1
		p.arc(gillsX,gillsY, size/2, size/2, 3*p.PI/2 + 1*p.PI/4, 2*p.PI);
		p.arc(gillsX,gillsY, size/2, size/2, 0, p.PI/2);
		
		// gill 2
		p.arc(gillsX+10,gillsY+10, size/2, size/2, 3*p.PI/2 + 2*p.PI/4, 2*p.PI);
		p.arc(gillsX+10,gillsY+10, size/2, size/2, 0, p.PI/2);
		p.noStroke();
	}
	
	private void drawTail(){
		p.fill(255,50,25);
		p.stroke(0);
		
		int tailX = -bodyW/2 + 30;
		int tailY = 0;
		int tailH = 100;
		int tailW = 80;
		
		p.triangle(tailX, tailY, tailX-tailW, tailY + tailH/2, tailX-tailW, tailY - tailH/2);
		
		// tail decorations
		p.noFill();
		p.stroke(0);
		p.fill(200,50,10);
		p.curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2, tailX+100, tailY+150);
		p.fill(0);
		p.curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+10, tailX+100, tailY+150);
		p.fill(200,50,10);
		p.curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+20, tailX+100, tailY+150);
		p.fill(0);
		p.curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+30, tailX+100, tailY+150);
		p.fill(200,50,10);
		p.curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+40, tailX+100, tailY+150);
		p.fill(0);
		p.curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+50, tailX+100, tailY+150);
		p.fill(200,50,10);
		p.curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+60, tailX+100, tailY+150);
		p.fill(0);
		p.curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+70, tailX+100, tailY+150);
		p.fill(200,50,10);
		p.curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+80, tailX+100, tailY+150);
		p.fill(0);
		p.curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+90, tailX+100, tailY+150);
		p.fill(200,50,10);
		p.curve(tailX, tailY, tailX, tailY, tailX-tailW, tailY-tailH/2+100, tailX+100, tailY+150);
		p.fill(0);
	}
	
	private void drawFins(){
		int ctrlptX1 = 60;
		int ctrlptY1 = 500;
		int ctrlptX2 = 0;
		int ctrlptY2 = 500;
		int finWidth = 40;
		int finX = -58;
		int finY = 40;
		p.stroke(0);
		p.fill(200,50,10);
		p.curve(ctrlptX1,ctrlptY1-800, finX, finY, finX+finWidth+5, 60, ctrlptX2 ,ctrlptY2-500);
		finX = finX + 80;
		finY = finY - 10;
		finWidth = 100;
		p.curve(ctrlptX1+300,ctrlptY1-1200, finX+15, finY, finX+finWidth-30, finY-10, ctrlptX2 ,ctrlptY2-800);
				
		// fin decorations
		p.noFill();
		p.stroke(0);
		// bottom closest to head fin
		p.line(finX+10, finY, finX+10, finY+58);
		p.line(finX+10, finY, finX+20, finY+58);
		p.line(finX+10, finY, finX+30, finY+55);
		p.line(finX+10, finY, finX+40, finY+50);
		p.line(finX+10, finY, finX+50, finY+40);
		p.line(finX+10, finY, finX+60, finY+28);
		p.line(finX+10, finY, finX+69, finY+10);
		
		// bottom closest to tail fin
		finX = finX - 50;
		p.line(finX, finY, finX, finY+40);
		p.line(finX, finY, finX-10, finY+43);
		p.line(finX, finY, finX-20, finY+45);
		p.line(finX, finY, finX-30, finY+40);
		
		// head fin
		p.fill(0);
		finWidth = 150;
		int finX1 = -80;
		int finY1 = -80;
		int finX2 = finX1+finWidth+20;
		int finY2 = -50;
		ctrlptX1 = finX1 + finWidth/2;
		ctrlptY1 = finY1 + 500;
		ctrlptX2 = ctrlptX1;
		ctrlptY2 = ctrlptY1;
		p.curve(ctrlptX1, ctrlptY1, finX1, finY1, finX2, finY2, ctrlptX2, ctrlptY2);
		p.noStroke();		
	}


}
