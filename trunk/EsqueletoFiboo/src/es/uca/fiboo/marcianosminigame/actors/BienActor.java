package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class BienActor extends Actor {
	
	private transient final TextureRegion bien;
	
	public BienActor() {
		super();
		bien = new TextureRegion(FibooGame.MANAGER.get("marcianosminigame/bien.png", Texture.class));
		setSize(bien.getRegionWidth(), bien.getRegionHeight());
		setWidth(getWidth()*0.5f);
		setHeight(getHeight()*0.5f);
	}
	
	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(bien, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
