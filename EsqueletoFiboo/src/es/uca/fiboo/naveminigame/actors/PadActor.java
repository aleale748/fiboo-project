package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class PadActor extends Actor {

	private TextureRegion button;
	
	public PadActor() {
		button = fibooGame.atlasNaveMiniGame.findRegion("disparar2");
		setSize(119, 57);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(button, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
