package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class BienActor extends Actor {
	
	private TextureRegion bien;
	
	public BienActor() {
		bien = fibooGame.atlasMarcianosMiniGame.findRegion("bien");
		setSize(bien.getRegionWidth(), bien.getRegionHeight());
		setWidth(getWidth()*0.5f);
		setHeight(getHeight()*0.5f);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(bien, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
