package com.pixelaborate.platformer;


public class Main {
	
	private GameState currentState;
	private GameState lastState;
	

	
	
	public static void main(String args[]) {
		
//		Map h = new Map("images/heightmap.bmp", "images/heightmap.bmp");
//		//Entity e = new Entity();
		Game game1 = new Game();
		game1.run();
//		//System.out.println(h.getColorData());
	}
	
}
