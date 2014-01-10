package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class MiniAsteroideVacioActor extends Actor {

	private TextureRegion asteroide;
	
	public MiniAsteroideVacioActor() {
		asteroide = new TextureRegion(fibooGame.MANAGER.get("naveminigame/asteroideVacio.png", Texture.class));
		setSize(asteroide.getRegionWidth(), asteroide.getRegionHeight());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(asteroide, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
}
