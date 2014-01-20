package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class PadActor extends Actor {

	private transient final TextureRegion button;
	
	public PadActor() {
		super();
		button = new TextureRegion(FibooGame.MANAGER.get("naveminigame/disparar2.png", Texture.class), 119, 57);
		setSize(button.getRegionWidth(), button.getRegionHeight());
	}
	
	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(button, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
