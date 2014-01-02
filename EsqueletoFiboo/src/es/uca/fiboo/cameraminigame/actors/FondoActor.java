package es.uca.fiboo.cameraminigame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import es.uca.fiboo.fibooGame;

public class FondoActor extends Actor {
	
	Texture texture = fibooGame.MANAGER.get("cameraminigame/cesped.png", Texture.class);
	
	public void draw(SpriteBatch batch, float alpha){
		batch.draw(texture,0,0);
	}
	
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		Gdx.app.log(fibooGame.LOG, "Screen: x=" + x + "; y=" + y);
		return true;
	}
}
