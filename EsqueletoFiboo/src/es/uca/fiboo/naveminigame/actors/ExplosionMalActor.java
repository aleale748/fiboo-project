package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class ExplosionMalActor extends Actor {
	
	public transient Animation explosionAnim;
	private transient TextureRegion currentFrame;
	public transient float stateTime;
	
	public ExplosionMalActor() {
		super();
		TextureRegion[] explosiones;
		explosiones = new TextureRegion[24];
		for (int i = 0; i < 24; ++i) {
			Gdx.app.log("ExplosionMalActor", "Asignando findRegion - explosionMal" + i);
			explosiones[i] = new TextureRegion(FibooGame.MANAGER.get("naveminigame/explosionMal" + Integer.toString(i) + ".png", Texture.class));
		}
		stateTime = 0f;
		
		explosionAnim = new Animation(0.025f, explosiones);
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {

		stateTime += Gdx.graphics.getDeltaTime();
		if (!explosionAnim.isAnimationFinished(stateTime)) {
			currentFrame = explosionAnim.getKeyFrame(stateTime, true);
			batch.draw(currentFrame, getX(), getY(), getOriginX(), getOriginY(), 
					getWidth(), getHeight(), getScaleX(), getScaleY(), 
					getRotation());
		}
	}

}
