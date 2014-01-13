package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class NaveMarcianoActor extends Actor {
	
	private TextureRegion nave;
	
	public NaveMarcianoActor() {
		nave = fibooGame.atlasMarcianosMiniGame.findRegion("naveMarciano");
		setSize(nave.getRegionWidth(), nave.getRegionHeight());
	}
	
	@Override
	public void act(float delta) {
		translate(Gdx.graphics.getWidth()/500f, 0);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(nave, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
