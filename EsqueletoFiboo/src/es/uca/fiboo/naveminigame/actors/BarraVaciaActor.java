package es.uca.fiboo.naveminigame.actors;

import es.uca.fiboo.fibooGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BarraVaciaActor extends Actor {
	
	private Texture barra;
	
	public BarraVaciaActor() {
		barra = fibooGame.MANAGER.get("naveminigame/vidaVacia.png", Texture.class);
		setSize(barra.getWidth(), barra.getHeight());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(barra, getX(), getY(), 0, 0, (int) getWidth(), (int) getHeight());
	}
	

}
