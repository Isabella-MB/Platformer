package com.pixelaborate.platformer;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;



public class MenuState implements GameState {

	
	private TrueTypeFont font;
   
    
	@Override
	public void input() {
	
		
	}

	@Override
	public void update() {
	}

	@Override
	public void render() {
		System.out.println("drawing square");
		GL11.glColor3f(0.0f,0.8f, 0.0f);
		
		font.drawString(100, 100, "Begin");
		
		
		glBegin(GL11.GL_QUADS);
		glVertex2d(370,220);
		glVertex2d(370,280);
		glVertex2d(270,280);
		glVertex2d(270,220);
		glEnd();
		

	}

}
