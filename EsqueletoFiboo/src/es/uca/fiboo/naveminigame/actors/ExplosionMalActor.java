package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class ExplosionMalActor extends Actor {
	
	private TextureRegion[] explosiones;
	public Animation explosionAnimation;
	private TextureRegion currentFrame;
	public float stateTime;
	
	public ExplosionMalActor() {
		explosiones = new TextureRegion[24];
		for (int i = 0; i < 24; ++i) {
			Gdx.app.log("ExplosionMalActor", "Asignando findRegion - explosionMal" + i);
			explosiones[i] = new TextureRegion(fibooGame.MANAGER.get("naveminigame/explosionMal" + Integer.toString(i) + ".png", Texture.class));
		}
		stateTime = 0f;
		
		explosionAnimation = new Animation(0.025f, explosiones);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		stateTime += Gdx.graphics.getDeltaTime();
		if (!explosionAnimation.isAnimationFinished(stateTime)) {
			currentFrame = explosionAnimation.getKeyFrame(stateTime, true);
			batch.draw(currentFrame, getX(), getY(), getOriginX(), getOriginY(), 
					getWidth(), getHeight(), getScaleX(), getScaleY(), 
					getRotation());
		}
	}

}
