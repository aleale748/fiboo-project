package es.uca.fiboo.naveminigame.actors;

import es.uca.fiboo.fibooGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AsteroideActor extends Actor {

	private TextureRegion asteroide;
	private int numero;
	public float velocidad;
	
	public Rectangle bb;
	
	public AsteroideActor(int numero, float aumentoVelocidad) {
		this.numero = numero;
		asteroide = fibooGame.atlasNaveMiniGame.findRegion("asteroide" + Integer.toString(numero));
		setSize(asteroide.getRegionWidth(), asteroide.getRegionHeight());
		bb = new Rectangle(getX(), getY(), getWidth(),getHeight());
		this.velocidad = aumentoVelocidad;
	}
	
	@Override
	public void act(float delta) {
		translate(-velocidad * delta, 0);
		bb.x = getX();
		bb.y = getY();
		bb.width = getWidth();
		bb.height = getHeight();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(asteroide, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
	public int getNumero() {
		return numero;
	}

}
