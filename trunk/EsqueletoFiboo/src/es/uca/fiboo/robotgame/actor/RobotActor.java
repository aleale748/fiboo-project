package es.uca.fiboo.robotgame.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RobotActor extends Actor {

	private TextureRegion robot;
	
	
	public Rectangle robotRect;
	
	public Vector2 velocidad = new Vector2(0, 0);
	
	public int puntos;
	
	public RobotActor() {
		robot = new TextureRegion(new Texture("robotgame/robotfrente.png"));
		setSize(Gdx.graphics.getWidth()*0.2f, Gdx.graphics.getWidth()*0.25f);
		robotRect = new Rectangle(getX(), getY(), getWidth(),getHeight());
	}
	
	@Override
	public void act(float delta) {
		translate(velocidad.x * delta, velocidad.y * delta);
		robotRect.x = getX();
		robotRect.y = getY();
		robotRect.width = getWidth();
		robotRect.height = getHeight();
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
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(robot, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
