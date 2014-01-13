package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class EmptyStarActor extends Actor {
	
	private TextureRegion star;
	
	public EmptyStarActor() {
		star = fibooGame.atlasNaveMiniGame.findRegion("starVacia");
		star.setRegionWidth(42);
		star.setRegionHeight(40);
		setSize(star.getRegionWidth(), star.getRegionHeight());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(star, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
