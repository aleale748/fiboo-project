package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class NumeroActor extends Actor {
	
	private TextureRegion numero;
	
	public int a;
	
	public NumeroActor(int a) {
		numero = fibooGame.atlasNaveMiniGame.findRegion(Integer.toString(a));
		setSize(60, 100);
		this.a = a;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(numero, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	

}
