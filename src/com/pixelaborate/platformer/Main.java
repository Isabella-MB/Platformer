package com.pixelaborate.platformer;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String args[])  {		
		//Map h = new Map("images/heightmap.bmp", "images/heightmap.bmp");
//		//Entity e = new Entity();
		Game game1 = new Game();
		game1.run();
		
		OBJ_Parser b = new OBJ_Parser("res/models/Rock.obj");
		
		System.out.println(b.parseOBJ());
		
//		//System.out.println(h.getColorData());
	}
}