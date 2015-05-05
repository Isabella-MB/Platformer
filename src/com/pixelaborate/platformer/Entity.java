package com.pixelaborate.platformer;


import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;


public class Entity {
	
	private Sphere s;

	
	public Entity() {
		s = new Sphere();
	}

	public void update() {

	}

	public void input() {

	}

	public void render() {
		GL11.glColor3f(1.0f,1.0f,1.0f);
		glBegin(2);
		glVertex2d(100,100);
		glVertex2i(300, 250);
		glEnd();
		
		drawSphere(10);
	}
	
	public void drawSphere(float radius) {
		s.draw(1.0f, 20, 16);
		
	}


}
