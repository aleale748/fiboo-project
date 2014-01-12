package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import es.uca.fiboo.fibooGame;

public class MarcianoActor extends Actor {

	private TextureRegion marciano;
	public Rectangle bb;
	private boolean colocado;
	private float posicionX, posicionY;
	private NaveActor nave;
	
	public MarcianoActor() {
		marciano = new TextureRegion(fibooGame.MANAGER.get("marcianosminigame/marciano.png", Texture.class));
		setSize(marciano.getRegionWidth()*0.5f, marciano.getRegionHeight()*0.5f);
		bb = new Rectangle(getX(), getY() + getHeight()/3f, getWidth(),getHeight()/1.8f);
		colocado = false;
		this.addListener(new DragListenerPropio(this));
	}
	
	@Override
	public void act(float delta) {
		bb.x = getX();
		bb.y = getY() + getHeight()/3f;
		bb.width = getWidth();
		bb.height = getHeight()/1.8f;
		for (Action a : this.getActions()) 
			a.act(delta);
	}
	
	public void colocar() {
		colocado = true;
	}
	
	public boolean colocado() {
		return colocado;
	}
	
	public void donde(NaveActor nave) {
		this.nave = nave;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(marciano, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
	class DragListenerPropio extends DragListener {
		
		private MarcianoActor marciano;
		
		public DragListenerPropio(MarcianoActor marciano) {
			this.marciano = marciano;
		}
		
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

			if (nave!=null && !nave.colocado() && marciano.bb.overlaps(nave.bb)) {
				Gdx.app.log(fibooGame.LOG, "Else de touchup empezado.");
				marciano.addAction(Actions.moveTo(nave.getX(), nave.getY(), 0.5f));
				posicionX = getX();
				posicionY = getY();
				marciano.colocar();
				nave.colocar();
				Gdx.app.log(fibooGame.LOG, "Else terminado.");
			}
			else  {
				setPosition(posicionX, posicionY);
				bb.setPosition(posicionX, posicionY);
			}
			super.touchUp(event, x, y, pointer, button);
		}
		
	}
	
}
