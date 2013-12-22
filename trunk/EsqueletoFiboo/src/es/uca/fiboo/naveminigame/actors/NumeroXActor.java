package es.uca.fiboo.naveminigame.actors;

import es.uca.fiboo.fibooGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NumeroXActor extends Actor {
	
	private TextureRegion numero;
	
	public int a;
	
	public NumeroXActor(int a) {
		numero = new TextureRegion(fibooGame.MANAGER.get("naveminigame/" + a + ".png", Texture.class), 60, 100);
		setSize(numero.getRegionWidth(), numero.getRegionHeight());
		this.a = a;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(numero, getX(), getY());
	}
	

}
