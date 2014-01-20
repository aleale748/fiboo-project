package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class PreguntaActor extends Actor {
	
	private transient final TextureRegion pregunta;
	
	public PreguntaActor() {
		super();
		pregunta = new TextureRegion(FibooGame.MANAGER.get("marcianosminigame/pregunta.png", Texture.class));
		setSize(pregunta.getRegionWidth(), pregunta.getRegionHeight());
	}
	
	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(pregunta, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
