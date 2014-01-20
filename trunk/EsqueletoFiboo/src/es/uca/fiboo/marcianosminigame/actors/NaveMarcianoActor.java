package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class NaveMarcianoActor extends Actor {
	
	private transient final TextureRegion nave;
	
	public NaveMarcianoActor() {
		super();
		nave = new TextureRegion(FibooGame.MANAGER.get("marcianosminigame/naveMarciano.png", Texture.class));
		setSize(nave.getRegionWidth(), nave.getRegionHeight());
	}
	
	@Override
	public void act(final float delta) {
		translate(Gdx.graphics.getWidth()/500f, 0);
	}
	
	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(nave, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
