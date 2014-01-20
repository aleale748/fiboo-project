package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class NaveActor extends Actor {
	
	private transient final TextureRegion nave;
	public transient Rectangle rectangleNave;
	private transient boolean varColocado, verificado;
	
	public NaveActor() {
		super();
		nave = new TextureRegion(FibooGame.MANAGER.get("marcianosminigame/nave.png", Texture.class));
		setSize(nave.getRegionWidth(), nave.getRegionHeight());
		rectangleNave = new Rectangle(getX(), getY() + getHeight()/4f, getWidth(),getHeight()/2f);
		varColocado = false;
		verificado = false;
	}
	
	public void colocar() {
		varColocado = true;
	}
	
	public void verificar() {
		verificado = true;
	}
	
	public boolean colocado() {
		return varColocado;
	}
	
	public boolean verificado() {
		return verificado;
	}
	
	@Override
	public void act(final float delta) {
		rectangleNave.x = getX();
		rectangleNave.y = getY() + getHeight()/4f;
		rectangleNave.width = getWidth();
		rectangleNave.height = getHeight()/2f;
	}
	
	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(nave, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
