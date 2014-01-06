package es.uca.fiboo.naveminigame.actors;

import es.uca.fiboo.fibooGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BarraVaciaActor extends Actor {
	
	private TextureRegion barra;
	
	public BarraVaciaActor() {
		barra = new TextureRegion(fibooGame.MANAGER.get("naveminigame/vidaVacia.png", Texture.class));
		setSize(barra.getRegionWidth(), barra.getRegionHeight());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(barra, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
