package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class BarraActor extends Actor {
	
	private TextureRegion barra;
	
	private HealthActor h;
	
	public BarraActor(HealthActor h) {
		if (h.getClass().getSimpleName().equals("NaveActor")) 
				barra = fibooGame.atlasNaveMiniGame.findRegion("vida");
		else
			if (h.getClass().getSimpleName().equals("EscudoActor"))
				barra = fibooGame.atlasNaveMiniGame.findRegion("vidaEscudo");
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
