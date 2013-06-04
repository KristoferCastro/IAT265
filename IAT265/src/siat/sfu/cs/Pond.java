package siat.sfu.cs;

import java.util.ArrayList;

/*
 * A class that holds collection of fishes. (NOT BEING USED YET, IGNORE)
 */
public class Pond {

	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 500;
	private final int FRAME_MARGIN = 10;
	
	ArrayList<Fish> fishes;
	
	public Pond() {
		fishes = new ArrayList<Fish>();
	}
	
	public void addFish(Fish newFish){
		fishes.add(newFish);
	}
}
