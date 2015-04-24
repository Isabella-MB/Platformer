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
		currentState = new MenuState(this);
	}

	public void makeWindow(GameState g) {
		try {

			//System.setProperty ("org.lwjgl.opengl.Window.undecorated" , "true" ) ;

			Display.setDisplayMode(new DisplayMode(700, 520));
			Display.setTitle(g.getClass().getSimpleName());
			Display.create();

			Display.sync(60);//set fps of game

			glMatrixMode(GL_PROJECTION);
			glLoadIdentity(); 
			glOrtho(0, 640, 480, 0, 1, -1);
			glMatrixMode(GL_MODELVIEW);

			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		} catch (LWJGLException e) {
			System.err.println("Error");
			System.exit(1);
		}

		while (!Display.isCloseRequested()) {
			Display.update();
			Display.sync(60);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			g.render();
			g.input();
			g.update();
			
			
		}
		
		Display.destroy();
		System.exit(0);
	}


	public void changeState(GameState a) {

		//Display.destroy();
		lastState = currentState;
		currentState = a;

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		System.out.println("change state");

	}
}
