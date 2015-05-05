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
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class PlayingState extends GameState {

	private Entity e;
	private Model m;
	public PlayingState(Game game) {
		super(game);
		e = new Entity();
		
		
		OBJ_Parser p = new OBJ_Parser();
		 m = p.parseOBJ();
		
		
	}



	@Override
	public void input() {
		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			System.out.println("p pressed");
			getGame().changeState(new PauseState(getGame()));
		}

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {

		GL11.glColor3f((float)(Math.random()),(float)(Math.random()),(float)(Math.random()));


		
		
		m.render();
		
		
		e.render();
	}
}


