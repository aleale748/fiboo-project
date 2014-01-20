package es.uca.fiboo.marcianosminigame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import es.uca.fiboo.FibooGame;

public class MarcianoActor extends Actor {

	private transient final TextureRegion marciano;
	public transient Rectangle rectangleMarciano;
	private transient boolean varColocado;
	private transient NaveActor nave;
	
	public MarcianoActor() {
		super();
		marciano = new TextureRegion(FibooGame.MANAGER.get("marcianosminigame/marciano.png", Texture.class));
		setSize(128, 128);
		rectangleMarciano = new Rectangle(getX(), getY() + getHeight()/3f, getWidth(),getHeight()/1.8f);
		varColocado = false;
		this.addListener(new DragListenerPropio(this));
	}
	
	@Override
	public void act(final float delta) {
		rectangleMarciano.x = getX();
		rectangleMarciano.y = getY() + getHeight()/3f;
		rectangleMarciano.width = getWidth();
		rectangleMarciano.height = getHeight()/1.8f;
		for (final Action accion : this.getActions()) 
			accion.act(delta);
	}
	
	public void colocar() {
		varColocado = true;
	}
	
	public boolean colocado() {
		return varColocado;
	}
	
	public void donde(final NaveActor nave) {
		this.nave = nave;
	}
	
	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(marciano, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}
	
	class DragListenerPropio extends DragListener {
		
		private transient final MarcianoActor marciano;
		private transient float distanciaX, distanciaY;
		
		public DragListenerPropio(final MarcianoActor marciano) {
			super();
			this.marciano = marciano;
		}
		
		public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
			if (!varColocado) {
				FibooGame.MANAGER.get("sonidos/guala.ogg", Sound.class).play();
				distanciaX = Gdx.input.getX() - getWidth() * 0.5f;
				distanciaY = Gdx.input.getY() + getHeight() * 0.5f;

				setPosition(distanciaX, Gdx.graphics.getHeight() - distanciaY); 
			}
			
			return super.touchDown(event, x, y, pointer, button);
		}
		
		public void touchDragged(final InputEvent event, final float x, final float y, final int pointer) {
			if (!varColocado) {
				distanciaX = x - getWidth()*0.5f;
				distanciaY = y - getHeight()*0.5f;
				setPosition(getX() + distanciaX, getY() + distanciaY);
				rectangleMarciano.setPosition(getX() + distanciaX, getY() + distanciaY);
			}
		}
			 
		public void touchUp(final InputEvent event, final float x, final float y, final int pointer, final int button) {

			if (nave!=null && !nave.colocado() && marciano.rectangleMarciano.overlaps(nave.rectangleNave)) {
				//Gdx.app.log(FibooGame.LOG, "Else de touchup empezado.");
				marciano.addAction(Actions.moveTo(nave.getX(), nave.getY(), 0.5f));
				marciano.colocar();
				nave.colocar();
				//Gdx.app.log(FibooGame.LOG, "Else terminado.");
			}
			super.touchUp(event, x, y, pointer, button);
		}
		
	}
	
}
