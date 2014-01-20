package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class AsteroideActor extends Actor {

	private transient final TextureRegion asteroide;
	private transient final int numero;
	public transient float velocidad;
	
	public transient Rectangle rectangleAst;
	
	public AsteroideActor(final int numero, final float aumentoVelocidad) {
		super();
		this.numero = numero;
		asteroide = new TextureRegion(FibooGame.MANAGER.get("naveminigame/asteroide" + Integer.toString(numero) + ".png", Texture.class));
		setSize(asteroide.getRegionWidth(), asteroide.getRegionHeight());
		rectangleAst = new Rectangle(getX(), getY(), getWidth(),getHeight());
		this.velocidad = aumentoVelocidad;
	}
	
	@Override
	public void act(final float delta) {
		translate(-velocidad, 0);
		rectangleAst.x = getX();
		rectangleAst.y = getY();
		rectangleAst.width = getWidth();
		rectangleAst.height = getHeight();
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(asteroide, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
	public int getNumero() {
		return numero;
	}

}
