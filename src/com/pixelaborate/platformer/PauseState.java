package com.pixelaborate.platformer;

import org.lwjgl.input.Keyboard;

public class PauseState extends GameState {

	public PauseState(Game game) {
		super(game);
	}

	@Override
	public void input() {
		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			System.out.println("p pressed");
			getGame().goToLastState(this);
		}

		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
