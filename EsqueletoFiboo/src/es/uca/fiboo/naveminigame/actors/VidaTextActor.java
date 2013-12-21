package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class VidaTextActor extends Actor {
	
	private BitmapFont font;
	
	public VidaTextActor(BitmapFont font) {
		this.font = font;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		font.draw(batch,  "VIDA ", getX(), getY());
	}

}
