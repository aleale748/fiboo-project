package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class BulletActor extends Actor {

	private TextureRegion bullet;
	public Rectangle bb;
	float velocidad;
	
	public BulletActor(float velocidad) {
		bullet = new TextureRegion(FibooGame.MANAGER.get("naveminigame/laserPeque.png", Texture.class));
		bullet.setRegionWidth(38);
		bullet.setRegionHeight(19);
		setSize(bullet.getRegionWidth(), bullet.getRegionHeight());
		bb = new Rectangle(getX(), getY(), getWidth(),getHeight());	
		this.velocidad = velocidad;
	}
	
	@Override
	public void act(float delta) {
		translate(velocidad, 0);
		bb.x = getX();
		bb.y = getY();
		bb.width = getWidth();
		bb.height = getHeight();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(bullet, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
