package com.pixelaborate.platformer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Main {
	//dank memes

	public static void main(String args[]) {
		try {
			Display.setDisplayMode(new DisplayMode(700, 520));
			Display.setTitle("dank memes");
			Display.create();

			glMatrixMode(GL_PROJECTION);
			glLoadIdentity(); // Resets any previous projection matrices
			glOrtho(0, 640, 480, 0, 1, -1);
			glMatrixMode(GL_MODELVIEW);


			glClear(GL_COLOR_BUFFER_BIT);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


		} catch (LWJGLException e) {
			System.err.println("Error");
			System.exit(1);
		}

		while (!Display.isCloseRequested()) {
		}
	}
}
