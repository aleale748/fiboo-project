package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class BarraActor extends Actor {
	
	private transient TextureRegion barra;
	
	private transient final HealthActor varHealth;
	
	public BarraActor(final HealthActor varHealth) {
		super();
		if (varHealth.getClass().getSimpleName().equals("NaveActor")) {
				barra = new TextureRegion(FibooGame.MANAGER.get("naveminigame/vida.png", Texture.class));
		}
		else {
			if (varHealth.getClass().getSimpleName().equals("EscudoActor")) {
				barra = new TextureRegion(FibooGame.MANAGER.get("naveminigame/vidaEscudo.png", Texture.class));
			}
		}
		setSize(barra.getRegionWidth(), barra.getRegionHeight());
		this.varHealth = varHealth;
	}
	
	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(barra, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth() * varHealth.getHealth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
