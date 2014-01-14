package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class BarraVaciaActor extends Actor {
	
	private TextureRegion barra;
	
	public BarraVaciaActor() {
		barra = fibooGame.atlasNaveMiniGame.findRegion("vidaVacia");
		setSize(barra.getRegionWidth(), barra.getRegionHeight());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(barra, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
