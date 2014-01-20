package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class BotonActor extends Actor {
	
	private transient final TextureRegion boton;
	
	public BotonActor() {
		super();
		boton = new TextureRegion(FibooGame.MANAGER.get("marcianosminigame/boton.png", Texture.class));
		setSize(202, 205);
	}
	
	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(boton, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
