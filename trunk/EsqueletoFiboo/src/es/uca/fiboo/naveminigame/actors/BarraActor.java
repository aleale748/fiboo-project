package es.uca.fiboo.naveminigame.actors;

import es.uca.fiboo.fibooGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BarraActor extends Actor {
	
	private Texture barra;
	
	private HealthActor h;
	
	public BarraActor(HealthActor h) {
		if (h.getClass().getSimpleName().equals("NaveActor")) 
				barra = fibooGame.MANAGER.get("naveminigame/vida.png", Texture.class);
		else
			if (h.getClass().getSimpleName().equals("EscudoActor"))
				barra = fibooGame.MANAGER.get("naveminigame/vidaEscudo.png", Texture.class);
		setSize(barra.getWidth(), barra.getHeight());
		this.h = h;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(barra, getX(), getY(), 0, 0, (int) (getWidth() * h.getHealth()), (int) getHeight());
	}
	

}
