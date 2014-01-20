package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class BulletActor extends Actor {

	private transient final TextureRegion bullet;
	public transient Rectangle rectangleBul;
	private transient final float velocidad;
	
	public BulletActor(final float velocidad) {
		super();
		bullet = new TextureRegion(FibooGame.MANAGER.get("naveminigame/laserPeque.png", Texture.class));
		bullet.setRegionWidth(38);
		bullet.setRegionHeight(19);
		setSize(bullet.getRegionWidth(), bullet.getRegionHeight());
		rectangleBul = new Rectangle(getX(), getY(), getWidth(),getHeight());	
		this.velocidad = velocidad;
	}
	
	@Override
	public void act(final float delta) {
		translate(velocidad, 0);
		rectangleBul.x = getX();
		rectangleBul.y = getY();
		rectangleBul.width = getWidth();
		rectangleBul.height = getHeight();
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(bullet, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
