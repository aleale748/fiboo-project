package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class BotonActor extends Actor {
	
	private TextureRegion boton;
	
	public BotonActor() {
		boton = fibooGame.atlasMarcianosMiniGame.findRegion("boton");
		setSize(202, 205);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(boton, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
