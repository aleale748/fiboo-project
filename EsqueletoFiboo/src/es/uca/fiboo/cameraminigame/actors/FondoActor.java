package es.uca.fiboo.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class FondoActor extends Actor {
	
	Texture texture = new Texture(Gdx.files.internal("parque.png"));
	
	@Override
	public void draw(SpriteBatch batch, float alpha){
		batch.draw(texture,0,0);
	}
	
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		Gdx.app.log(Makipong.LOG, "Screen: x=" + x + "; y=" + y);
		return true;
	}
}
