package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class EmptyStarActor extends Actor {
	
	private TextureRegion star;
	
	public EmptyStarActor() {
		star = new TextureRegion(fibooGame.MANAGER.get("marcianosminigame/starVacia.png", Texture.class), 42, 40);
		setSize(star.getRegionWidth(), star.getRegionHeight());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(star, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
