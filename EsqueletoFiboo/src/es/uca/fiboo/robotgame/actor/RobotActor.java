package es.uca.fiboo.robotgame.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RobotActor extends Actor {

	//private TextureRegion robot;
	
	
	public Rectangle robotRect;
	
	public Vector2 velocidad = new Vector2(0, 0);
	private TextureRegion[] robot;
	public int puntos;
	private float positionX;
	private int direccion;
	private int cont;
	public RobotActor() {
		robot= new TextureRegion[3];
		robot[0] = new TextureRegion(new Texture("robotgame/robotfrente.png"));
		robot[1] = new TextureRegion(new Texture("robotgame/robotderecha.png"));
		robot[2] = new TextureRegion(new Texture("robotgame/robotizquierda.png"));
		direccion= 0;
		cont= 0;
		setSize(Gdx.graphics.getWidth()*0.2f, Gdx.graphics.getWidth()*0.25f);
		robotRect = new Rectangle(getX(), getY(), getWidth(),getHeight());
		positionX= getX();
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
		int i= direccion();
		
		batch.draw(robot[i], getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	private int direccion(){
		if(getX()>positionX) direccion= 1; 
		else if(getX()<positionX) direccion= 2;
		else {
			if (cont>20){
			direccion= 0;
			cont=0;
			}
			cont++;
		}
		positionX= getX();
		return direccion;
	}
}
