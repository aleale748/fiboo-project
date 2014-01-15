package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class PalitosActor extends Actor {

	private TextureRegion palitos;
	private int num;
	
	public PalitosActor(int num) {
		palitos = fibooGame.atlasNaveMiniGame.findRegion("palitos" + num);
		setSize(palitos.getRegionWidth()/2f, palitos.getRegionHeight()/2f);
		this.num = num;
	}
	
	public int getNum() {
		return num;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(palitos, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
}