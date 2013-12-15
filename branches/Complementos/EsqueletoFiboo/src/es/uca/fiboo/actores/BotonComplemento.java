package es.uca.fiboo.actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import es.uca.fiboo.fibooGame;

/**
 * Clase que contiene el botón en miniatura del complemento y su complemento real asignado.
 * 
 * @param complemento Complemento asignado al botón
 * @param nino Rectángulo con el que interacciona el complemento
 * @param stage Stage de la screen donde añadir los actores
 */
public class BotonComplemento extends Image {

	private Complemento complemento;
	private Rectangle nino;
	private Stage stage;
	
	public BotonComplemento(Texture icon, Complemento complemento) {
		super(icon);
		this.complemento = complemento;
		addDragListener();
	}
	
	public Complemento getComplemento() {
		return complemento;
	}
	
	public void setStage(Stage stage) {
		//if(stage == null) {
			this.stage = stage;
			Gdx.app.log(fibooGame.LOG, "asignando stage");
		//}
	}
	
	public void setOverlap(Rectangle nino) {
		//if(nino == null) {
			this.nino = nino;
		//}
	}
	
	private void addDragListener() {
		this.addListener(new DragListener() {

			private boolean contenido = false;

			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				if(!contenido) {
					float dx = Gdx.input.getX() - complemento.getWidth() * 0.5f;
					float dy = Gdx.input.getY() + complemento.getHeight() * 0.5f;
					complemento.setPosition(dx, Gdx.graphics.getHeight() - dy);
				}
			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(fibooGame.personaje.contains(complemento)) {
					Gdx.app.log(fibooGame.LOG, "va ser que ya esta dentro");
					contenido = true;
				}
				else {
					float dx = Gdx.input.getX() - complemento.getWidth() * 0.5f;
					float dy = Gdx.input.getY() + complemento.getHeight() * 0.5f;
					complemento.setPosition(dx, Gdx.graphics.getHeight() - dy);
					contenido = false;
					stage.addActor(complemento);
				}
				
				return super.touchDown(event, x, y, pointer, button);
			}

			public void touchUp(InputEvent event, float x, float y, 	int pointer, int button) {
				if (!contenido && complemento.getBounds().overlaps(nino)) {
					// Gdx.app.log(MyGdxGame.LOG, "intersecting...");
					complemento.addAction(Actions.moveTo(nino.x, nino.y, 1f));
					fibooGame.personaje.addComplemento(complemento);
				} else if(!contenido){
					Gdx.app.log(fibooGame.LOG, "didn't intersect");
					complemento.remove();
				}
				super.touchUp(event, x, y, pointer, button);
			}
		});
	}

}
