package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class BarraVaciaActor extends Actor {
	
	private transient final TextureRegion barra;
	
	public BarraVaciaActor() {
		super();
		barra = new TextureRegion(FibooGame.MANAGER.get("naveminigame/vidaVacia.png", Texture.class));
		setSize(barra.getRegionWidth(), barra.getRegionHeight());
	}
	
	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(barra, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
