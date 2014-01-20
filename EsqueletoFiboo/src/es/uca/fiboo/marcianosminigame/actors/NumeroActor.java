package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class NumeroActor extends Actor {
	
	private transient final TextureRegion numero;
	
	public transient int numeroAsignado;
	
	public NumeroActor(final int numeroAsignado) {
		super();
		numero = new TextureRegion(FibooGame.MANAGER.get("marcianosminigame/" + Integer.toString(numeroAsignado) + ".png", Texture.class), 60, 100);
		setSize(numero.getRegionWidth(), numero.getRegionHeight());
		this.numeroAsignado = numeroAsignado;
	}
	
	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(numero, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	

}
