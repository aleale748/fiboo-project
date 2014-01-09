package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import es.uca.fiboo.fibooGame;

public class MarcianoActor extends Actor {

	private TextureRegion marciano;
	public Rectangle bb;
	private boolean colocado;
	private float posicionX, posicionY;
	
	public MarcianoActor() {
		marciano = new TextureRegion(fibooGame.MANAGER.get("marcianosminigame/marciano.png", Texture.class));
		setSize(marciano.getRegionWidth(), marciano.getRegionHeight());
		bb = new Rectangle(getX(), getY(), getWidth(),getHeight());
		colocado = false;
		this.addListener(new DragListener() {
			
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (!colocado) {
					posicionX = getX();
					posicionY = getY();
					float dx = Gdx.input.getX() - getWidth() * 0.5f;
					float dy = Gdx.input.getY() + getHeight() * 0.5f;
	
					setPosition(dx, Gdx.graphics.getHeight() - dy);
				}
				
				return super.touchDown(event, x, y, pointer, button);
			}
			
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				if (!colocado) {
					float dx = x - getWidth()*0.5f;
					float dy = y - getHeight()*0.5f;
					setPosition(getX() + dx, getY() + dy);
					bb.setPosition(getX() + dx, getY() + dy);
				}
			}
				 
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (!colocado) {
					setPosition(posicionX, posicionY);
					bb.setPosition(posicionX, posicionY);
				}
				else  {
					posicionX = getX();
					posicionY = getY();
				}
				super.touchUp(event, x, y, pointer, button);
			}
		});
	}
	
	@Override
	public void act(float delta) {
		bb.x = getX();
		bb.y = getY();
		bb.width = getWidth();
		bb.height = getHeight();
	}
	
	public void colocar() {
		colocado = true;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(marciano, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
}
