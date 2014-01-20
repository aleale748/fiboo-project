package es.uca.fiboo.robotgame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;

public class RobotActor extends Actor {

	public transient Rectangle robotRect;
	
	public transient Vector2 velocidad = new Vector2(0, 0);
	private transient final TextureRegion[] robot;
	public int puntos;
	private transient float positionX;
	private transient int direccion;
	private transient int cont;
	
	public RobotActor() {
		super();
		robot = new TextureRegion[3];
		robot[0] = new TextureRegion(FibooGame.MANAGER.get("robotgame/robotfrente.png", Texture.class));
		robot[1] = new TextureRegion(FibooGame.MANAGER.get("robotgame/robotderecha.png", Texture.class));
		robot[2] = new TextureRegion(FibooGame.MANAGER.get("robotgame/robotizquierda.png", Texture.class));
		direccion = 0;
		cont = 0;
		setSize(Gdx.graphics.getWidth()*0.2f, Gdx.graphics.getWidth()*0.25f);
		robotRect = new Rectangle(getX(), getY(), getWidth(),getHeight());
		positionX= getX();
	}
	
	@Override
	public void act(final float delta) {
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
	public void draw(final Batch batch, final float parentAlpha) {
		int i = direccion();
		
		batch.draw(robot[i], getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
	private int direccion(){
		if(getX() > positionX) {
			direccion = 1; 
		}
		else if(getX() < positionX) {
			direccion = 2;
		}
		else {
			if (cont > 20) {
				direccion = 0;
				cont = 0;
			}
			cont++;
		}
		positionX = getX();
		return direccion;
	}
}
