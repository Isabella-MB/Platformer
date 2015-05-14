package com.pixelaborate.platformer;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class MenuState extends GameState {

	private Image img;
	
	Texture texture;
	
	public MenuState(Game game)  {
		super(game);

		loadTextures();

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
	}

	public void loadTextures() {
		try {

			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/images/Begin.png"));
			img = new Image("res/images/Begin.png");
			

		}

		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void input() {
		//System.out.println("input");
		System.out.println(Mouse.getX() + ", " + Mouse.getY());
		didClickGreenBox();
		Color.white.bind();
		GL11.glColor3f((float)(Math.random()),(float)(Math.random()),(float)(Math.random()));


		glBegin(GL11.GL_TRIANGLES);
		glVertex2d(Mouse.getX(), 720-Mouse.getY());
		glVertex2d(Mouse.getX()-5, 720-Mouse.getY() + 20);
		glVertex2d(Mouse.getX()+5, 720-Mouse.getY() + 20);
		glEnd();
		
//		glBegin(GL11.GL_QUADS);
//				glVertex2d(405,520-215);
//				glVertex2d(405,520-280);
//				glVertex2d(295,520-280);
//				glVertex2d(295,520-215);
//				glEnd();



	}

	@Override
	public void update() {

	}

	@Override
	public void render() {
		//Display.update();
		//System.out.println("render");

		//will insert image of text 'begin' overtop the green button. code will be implemented here. 

		//GL11.glColor3f(0.0f,0.8f, 0.0f);
		//		glBegin(GL11.GL_QUADS);
		//		glVertex2d(405,520-215);
		//		glVertex2d(405,520-280);
		//		glVertex2d(295,520-280);
		//		glVertex2d(295,520-215);
		//		glEnd();
		//


		//img.startUse();
				loadTextures();
				
				img.bind();
				img.setTexture(texture);
				img.draw(530, 300);
				
				
				
				//img.drawFlash(10, 10);
				//img.drawFlash(490, 10);
				//img.drawFlash(490, 410);
				//img.drawFlash(10, 410);
		
				
		
				//img.rotate((int)(x+=.1));
				texture.release();
		

		
	}





	public void didClickGreenBox() {
		if (((Mouse.getX() >= 530) && (Mouse.getX() <= 730)) && ((Mouse.getY() >= 300) && (Mouse.getY() <= 400))) {
			System.out.println("the mouse is in the box");
			if (Mouse.isButtonDown(0)) {
				System.out.println("left click");
				getGame().changeState(new PlayingState(getGame()));
			}
		}


	}

}