package es.uca.fiboo.naveminigame.actors;

import es.uca.fiboo.fibooGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NumeroYActor extends Actor {
	
	private TextureRegion numero;
	
	public int b;
	
	public NumeroYActor(int b) {
		numero = new TextureRegion(fibooGame.MANAGER.get("naveminigame/" + b + ".png", Texture.class), 60, 100);
		setSize(numero.getRegionWidth(), numero.getRegionHeight());
		this.b = b;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(numero, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	

}
