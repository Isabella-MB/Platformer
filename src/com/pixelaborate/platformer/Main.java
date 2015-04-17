package com.pixelaborate.platformer;


public class Main {
	
	private GameState currentState;
	private GameState lastState;
	

	
	
	public static void main(String args[]) {
		
		//Heightmap h = new Heightmap("images/heightmap.bmp");
		Game game1 = new Game();
		game1.run();
		//System.out.println(h.getColorData());
	}
	
}
