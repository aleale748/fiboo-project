package es.uca.fiboo.naveminigame.actors;

import es.uca.fiboo.fibooGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NaveActor extends Actor implements HealthActor {

	private TextureRegion nave;
	
	private float health;
	
	public Rectangle bb;
	
	public Vector2 velocidad = new Vector2(0, 0);
	
	public int puntos;
	
	public NaveActor() {
		nave = new TextureRegion(fibooGame.MANAGER.get("naveminigame/nave.png", Texture.class), 256, 135);
		setSize(nave.getRegionWidth(), nave.getRegionHeight());
		bb = new Rectangle(getX(), getY(), getWidth(),getHeight());
		health = 1;
		puntos = 0;
	}
	
	private float timer = 0;
	
	@Override
	public void act(float delta) {
		translate(velocidad.x * delta, velocidad.y * delta);
		bb.x = getX();
		bb.y = getY();
		bb.width = getWidth();
		bb.height = getHeight();
		if(getX() < 0) {
			setX(0);
			velocidad.x = 0;
		} else if(getRight() > getStage().getWidth()) {
			setX(getStage().getWidth() - getWidth());
			velocidad.x = 0;
		}
		
		if(getY() < 0) {
			setY(0);
			velocidad.y = 0;
		} else if(getTop() > getStage().getHeight()) {
			setY(getStage().getHeight() - getHeight());
			velocidad.y = 0;
		}
		
		timer += delta;
		if (timer > 2 && health < 0.99f) { 
			health += 0.01f;
			timer = 0;
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(nave, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

	@Override
	public float getHealth() {
		return health;
	}

	@Override
	public void setHealth(float health) {
		this.health = health;
	}

	@Override
	public void sumHealth(float sum) {
		this.health += sum;
		if (health > 1)
			health = 1;
		else
			if (health < 0)
				health = 0;
	}
	
}
