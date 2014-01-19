package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class BarraActor extends Actor {
	
	private TextureRegion barra;
	
	private HealthActor h;
	
	public BarraActor(HealthActor h) {
		if (h.getClass().getSimpleName().equals("NaveActor")) 
				barra = new TextureRegion(FibooGame.MANAGER.get("naveminigame/vida.png", Texture.class));
		else
			if (h.getClass().getSimpleName().equals("EscudoActor"))
				barra = new TextureRegion(FibooGame.MANAGER.get("naveminigame/vidaEscudo.png", Texture.class));
		setSize(barra.getRegionWidth(), barra.getRegionHeight());
		this.h = h;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(barra, getX(), getY(), getOriginX(), getOriginY(), 
				(getWidth() * h.getHealth()), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
