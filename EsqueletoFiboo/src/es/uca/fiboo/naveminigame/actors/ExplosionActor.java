package es.uca.fiboo.naveminigame.actors;

import es.uca.fiboo.fibooGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ExplosionActor extends Actor {
	
	private TextureRegion[] explosiones;
	
	public Animation explosionAnimation;
	
	private TextureRegion currentFrame;
	
	public float stateTime;
	
	public ExplosionActor() {
		explosiones = new TextureRegion[24];
		for (int i = 0; i < 24; ++i) {
			explosiones[i] = new TextureRegion(fibooGame.MANAGER.get("naveminigame/explosion" + Integer.toString(i) + ".png", Texture.class));
		}
		stateTime = 0f;
		
		explosionAnimation = new Animation(0.025f, explosiones);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		stateTime += Gdx.graphics.getDeltaTime();
		if (!explosionAnimation.isAnimationFinished(stateTime)) {
			currentFrame = explosionAnimation.getKeyFrame(stateTime, true);
			batch.draw(currentFrame, getX(), getY());
		}
	}

}
