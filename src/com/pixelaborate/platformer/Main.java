package com.pixelaborate.platformer;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;





public class Main {

	public static void main(String args[])  {		
		//Map h = new Map("images/heightmap.bmp", "images/heightmap.bmp");
//		//Entity e = new Entity();
		Game game1 = new Game();
		game1.run();
		
			
		//OBJ_Parser b = new OBJ_Parser("res/models/Rock.obj");
		
		//Model m = b.parseOBJ();
		//m.render();
		
//		//System.out.println(h.getColorData());
		
	}
}