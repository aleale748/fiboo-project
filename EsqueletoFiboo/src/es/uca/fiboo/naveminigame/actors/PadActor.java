package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class PadActor extends Actor {

	private TextureRegion button;
	
	public PadActor() {
		button = new TextureRegion(fibooGame.MANAGER.get("naveminigame/disparar.png", Texture.class), 119, 57);
		setSize(button.getRegionWidth(), button.getRegionHeight());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(button, getX(), getY());
	}

}
