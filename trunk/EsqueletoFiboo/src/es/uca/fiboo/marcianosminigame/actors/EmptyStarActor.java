package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class EmptyStarActor extends Actor {
	
	private TextureRegion star;
	
	public EmptyStarActor() {
		star = fibooGame.atlasNaveMiniGame.findRegion("starVacia");
		setSize(42, 40);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(star, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
