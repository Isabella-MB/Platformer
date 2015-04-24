package com.pixelaborate.platformer;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class MenuState extends GameState {


	public MenuState(Game game) {
		super(game);
		getGame().makeWindow(this);
	}




	@Override
	public void input() {
		//System.out.println("input");
		System.out.println(Mouse.getX() + "," + Mouse.getY());
		didClickGreenBox();
		
		GL11.glColor3f((float)(Math.random()),(float)(Math.random()),(float)(Math.random()));
		glBegin(GL11.GL_QUADS);
		glVertex2d(Mouse.getX()-5,520-Mouse.getY()+5);
		glVertex2d(Mouse.getX()+5,520-Mouse.getY()+5);
		glVertex2d(Mouse.getX()+5,520-Mouse.getY()-5);
		glVertex2d(Mouse.getX()-5,520-Mouse.getY()-5);
		glEnd();
		
	}

	@Override
	public void update() {

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
		if (((Mouse.getX() >= 295) && (Mouse.getX() <= 405)) && ((Mouse.getY() >= 215) && (Mouse.getY() <= 280))) {
			System.out.println("the mouse is in the box");
			if (Mouse.isButtonDown(0)) {
				System.out.println("left click");
				Display.destroy();
				getGame().changeState(new PlayingState(getGame()));
			}
		}


	}
	
}