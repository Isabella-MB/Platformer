package com.pixelaborate.platformer;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Camera {
	int x, y;
	
	public Camera()
	{
		
	}
	
	public void makeProjection()
	{
		glMatrixMode(GL_PROJECTION);
		glClearColor (.5f, .5f, .5f, 1f);
		glLoadIdentity(); 
		glViewport(0, 0, 1280, 720);
		glFrustum(-1.0, 1.0, -1.0, 1.0, 1, 1000.0);
		
		glMatrixMode(GL_MODELVIEW);
		glTranslatef(0, -500f, -100);
		glRotatef(60, 1, 0, 0);
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LESS);
	}
	
	public void makeOrtho()
	{
		glLoadIdentity(); 
		glOrtho((float)0, (float)700, (float)520, (float)0,(float) 1, (float)-1);
	}
	
	public void update()
	{
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			glTranslatef(10f, 0, 0);
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			glTranslatef(-10f, 0, 0);
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			glTranslatef(0, 0, -10f);
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			glTranslatef(0, 0, 10f);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
			glRotatef(1, -1, 0, 0);
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			glRotatef(1, 0, 1, 0);
	}
}