package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class BulletActor extends Actor {

	private TextureRegion bullet;
	public Rectangle bb;
	
	public BulletActor() {
		bullet = fibooGame.atlasNaveMiniGame.findRegion("laserPeque");
		bullet.setRegionWidth(38);
		bullet.setRegionHeight(19);
		setSize(bullet.getRegionWidth(), bullet.getRegionHeight());
		bb = new Rectangle(getX(), getY(), getWidth(),getHeight());	
	}
	
	@Override
	public void act(float delta) {
		translate(300 * delta, 0);
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
