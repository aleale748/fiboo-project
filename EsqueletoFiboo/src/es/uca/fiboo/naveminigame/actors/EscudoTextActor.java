package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EscudoTextActor extends Actor {
	
	private BitmapFont font;
	
	public EscudoTextActor(BitmapFont font) {
		this.font = font;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		font.draw(batch,  "ESCUDO ", getX(), getY());
	}

	public BitmapFont getFont() {
		return font;
	}
	
}
