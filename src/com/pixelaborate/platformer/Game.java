package com.pixelaborate.platformer;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game {
	
	private GameState currentState;
	private GameState lastState;

	public Game()
	{
		currentState = new PlayingState();
		
		try {
			Display.setDisplayMode(new DisplayMode(700, 520));
			Display.setTitle("dank memes");
			Display.create();
			
			Display.sync(60);//set fps of game
	
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity(); 
			glOrtho(0, 640, 480, 0, 1, -1);
			glMatrixMode(GL_MODELVIEW);
	
	
			glClear(GL_COLOR_BUFFER_BIT);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		} catch (LWJGLException e) {
			System.err.println("Error");
			System.exit(1);
		}
	}
	
	public void run()
	{
		while (!Display.isCloseRequested()) {
			currentState.render();
			currentState.input();
			currentState.update();
		}
		
		System.exit(0);
	}
}
