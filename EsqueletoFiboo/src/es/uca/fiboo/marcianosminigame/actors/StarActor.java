package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class StarActor extends Actor {
	
	private transient final TextureRegion star;
	
	public StarActor() {
		super();
		star = new TextureRegion(FibooGame.MANAGER.get("marcianosminigame/star.png", Texture.class), 42, 40);
		setSize(star.getRegionWidth(), star.getRegionHeight());
	}
	
	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(star, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
