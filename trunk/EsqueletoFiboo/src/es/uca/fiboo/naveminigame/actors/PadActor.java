package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class PadActor extends Actor {

	private TextureRegion button;
	
	public PadActor(int x, int y) {
		button = new TextureRegion(fibooGame.MANAGER.get("naveminigame/older/pad.png", Texture.class), 32 * x, 32 * y, 32, 32);
		setSize(32, 32);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(button, getX(), getY());
	}

}
