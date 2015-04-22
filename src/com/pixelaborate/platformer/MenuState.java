package com.pixelaborate.platformer;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;



public class MenuState implements GameState {


	private TrueTypeFont font;


	@Override
	public void input() {
		//System.out.println("input");
		didClickGreenBox();
		System.out.println(Mouse.getX() + "," + Mouse.getY());

	}

	@Override
	public void update() {
		//System.out.println("update");
	}

	@Override
	public void render() {
		//Display.update();
		//System.out.println("render");
		GL11.glColor3f(0.0f,0.8f, 0.0f);
		//will insert image of text 'begin' overtop the green button. code will be implemented here. 


		glBegin(GL11.GL_QUADS);
		glVertex2d(370,220);
		glVertex2d(370,280);
		glVertex2d(270,280);
		glVertex2d(270,220);
		glEnd();


	}

	public void didClickGreenBox() {
		if (((Mouse.getX() >= 300) && (Mouse.getX() <= 400)) && ((Mouse.getY() >= 220) && (Mouse.getY() <= 280))) {
			System.out.println("the mouse is in the box");


		}
	}

}
