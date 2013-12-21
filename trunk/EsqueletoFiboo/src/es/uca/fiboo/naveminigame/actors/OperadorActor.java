package es.uca.fiboo.naveminigame.actors;

import es.uca.fiboo.fibooGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class OperadorActor extends Actor {
	
	private TextureRegion operador;
	
	public OperadorActor() {
		operador = new TextureRegion(fibooGame.MANAGER.get("naveminigame/suma.png", Texture.class), 65, 60);
		setSize(operador.getRegionWidth(), operador.getRegionHeight());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(operador, getX(), getY());
	}
	

}
