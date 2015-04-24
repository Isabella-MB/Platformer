/**
 * 
 */
package com.pixelaborate.platformer;

/**
 * @author s010727
 *
 */
public abstract class GameState {

	private Game game;
	
	public GameState(Game game) {
		this.game = game;
	}
	
	protected Game getGame() {
		return game;
	}
	
	public abstract void input();
	public abstract void update();
	public abstract void render();

}