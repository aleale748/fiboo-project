package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class PreguntaActor extends Actor {
	
	private TextureRegion pregunta;
	
	public PreguntaActor() {
		pregunta = new TextureRegion(fibooGame.MANAGER.get("marcianosminigame/pregunta.png", Texture.class));
		setSize(pregunta.getRegionWidth(), pregunta.getRegionHeight());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(pregunta, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}