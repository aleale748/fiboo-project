package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class NaveActor extends Actor {
	
	private TextureRegion nave;
	public Rectangle bb;
	private boolean colocado;
	
	public NaveActor() {
		nave = new TextureRegion(fibooGame.MANAGER.get("marcianosminigame/nave.png", Texture.class));
		setSize(nave.getRegionWidth(), nave.getRegionHeight());
		bb = new Rectangle(getX(), getY(), getWidth(),getHeight());
		colocado = false;
	}
	
	public void colocar() {
		colocado = true;
	}
	
	public boolean colocado() {
		return colocado;
	}
	
	@Override
	public void act(float delta) {
		bb.x = getX();
		bb.y = getY();
		bb.width = getWidth();
		bb.height = getHeight();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(nave, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
