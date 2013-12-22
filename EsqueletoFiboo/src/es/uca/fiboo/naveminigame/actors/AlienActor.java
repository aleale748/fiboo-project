package es.uca.fiboo.naveminigame.actors;

import es.uca.fiboo.fibooGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AlienActor extends Actor {

	private TextureRegion alien;
	private int numero;
	private float velocidad;
	
	public Rectangle bb;
	
	public AlienActor(int numero, float aumentoVelocidad) {
		this.numero = numero;
		alien = new TextureRegion(fibooGame.MANAGER.get("naveminigame/asteroide" + Integer.toString(numero) + ".png", Texture.class));
		setSize(alien.getRegionWidth(), alien.getRegionHeight());
		bb = new Rectangle(getX(), getY(), getWidth(),getHeight());
		this.velocidad = 200 * aumentoVelocidad + 100;
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
		batch.draw(alien, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
	public int getNumero() {
		return numero;
	}

}
