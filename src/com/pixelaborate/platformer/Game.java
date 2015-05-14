package com.pixelaborate.platformer;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glGetString;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game {

	private GameState currentState;
	private GameState lastState;


	public Game()
	{
		
	}

	public void run() {
		try {

			System.setProperty ("org.lwjgl.opengl.Window.undecorated" , "true" ) ;

			Display.setDisplayMode(new DisplayMode(1280, 720));
//			Display.setTitle(currentState.getClass().getSimpleName());
			Display.create();

			Display.setVSyncEnabled(true);//ensures that image is fully drawn before drawing next frame
			
		//if(Mouse.isGrabbed() && Mouse.isInsideWindow())
			//Mouse.setGrabbed(true); //removes the mouse from the screen. When running the program(pressing the green and white arrow in eclipse), be sure to move the mouse away from the button, otherwise each click the user makes will cause the program to restart 
			
		 
		    
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity(); 
			glOrtho((float)0, (float)1280, (float)720, (float)0,(float) 1, (float)-1);
			glMatrixMode(GL_MODELVIEW);

			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			
			
			currentState = new MenuState(this);
		}
		
		catch (LWJGLException e) {
			System.err.println("Error");
			System.exit(1);
		}

		while (!Display.isCloseRequested()) {
			Display.update();
			Display.sync(60);//sets fps of game. MUST REMAIN HERE FOR SCREEN TO CLEAR
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			currentState.render();
			currentState.input();
			currentState.update();
			
			
		}
		
		Display.destroy();
		System.exit(0);
	}


	public void changeState(GameState a) {
		lastState = currentState;
		currentState = a;
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		System.out.println("change state");

	}
	
	public void goToLastState(GameState a) {
	
		currentState = lastState;
		lastState = a;
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		System.out.println("change state to last state");
		
	}
}
