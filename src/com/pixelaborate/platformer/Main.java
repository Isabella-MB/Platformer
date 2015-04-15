package com.pixelaborate.platformer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Main {
	
	private GameState currentState;
	private GameState lastState;
	

	
	
	public static void main(String args[]) {
		Game game1 = new Game();
		game1.run();
	}
	
}
