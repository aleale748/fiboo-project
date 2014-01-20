package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class PalitosActor extends Actor {

	private transient final TextureRegion palitos;
	private transient final int num;
	
	public PalitosActor(final int num) {
		super();
		palitos = new TextureRegion(FibooGame.MANAGER.get("naveminigame/palitos" + Integer.toString(num) + ".png", Texture.class));
		setSize(palitos.getRegionWidth()/2f, palitos.getRegionHeight()/2f);
		this.num = num;
	}
	
	public int getNum() {
		return num;
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(palitos, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
}
