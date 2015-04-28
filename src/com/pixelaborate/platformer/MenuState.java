package com.pixelaborate.platformer;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Image;

public class MenuState extends GameState {

	private Image img;
	private int x = 0;
	public MenuState(Game game)  {
		super(game);

		try {
			img = new Image("res/images/Begin.png");
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void input() {
		//System.out.println("input");
		System.out.println(Mouse.getX() + "," + Mouse.getY());
		didClickGreenBox();

		GL11.glColor3f((float)(Math.random()),(float)(Math.random()),(float)(Math.random()));


		glBegin(GL11.GL_TRIANGLES);
		glVertex2d(Mouse.getX(), 520-Mouse.getY());
		glVertex2d(Mouse.getX()-5, 520-Mouse.getY() + 20);
		glVertex2d(Mouse.getX()+5, 520-Mouse.getY() + 20);
		glEnd();





	}

	@Override
	public void update() {

	}

	@Override
	public void render() {
		//Display.update();
		//System.out.println("render");

		//will insert image of text 'begin' overtop the green button. code will be implemented here. 

		GL11.glColor3f(0.0f,0.8f, 0.0f);
		glBegin(GL11.GL_QUADS);
		glVertex2d(405,520-215);
		glVertex2d(405,520-280);
		glVertex2d(295,520-280);
		glVertex2d(295,520-215);
		glEnd();



		img.setRotation(x++);
		img.draw(20, 20);
		
		//110X65
	}





	public void didClickGreenBox() {
		if (((Mouse.getX() >= 295) && (Mouse.getX() <= 405)) && ((Mouse.getY() >= 215) && (Mouse.getY() <= 280))) {
			System.out.println("the mouse is in the box");
			if (Mouse.isButtonDown(0)) {
				System.out.println("left click");
				getGame().changeState(new PlayingState(getGame()));
			}
		}


	}

}