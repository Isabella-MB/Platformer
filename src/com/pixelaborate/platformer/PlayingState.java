package com.pixelaborate.platformer;




import org.lwjgl.input.Keyboard;

import org.lwjgl.opengl.GL11;

public class PlayingState extends GameState {

	private Entity e;

	public PlayingState(Game game) {
		super(game);
		e = new Entity();
		
		
		
		
		
	}



	@Override
	public void input() {
		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			System.out.println("p pressed");
			getGame().changeState(new PauseState(getGame()));
		}

		//GL11.glColor3f((float)(Math.random()),(float)(Math.random()),(float)(Math.random()));
		
		e.input();
		
		
	}

	@Override
	public void update() {
		e.update();

	}

	@Override
	public void render() {
		GL11.glColor3f(1,1,1);
		
		e.render();
	}
}


