package es.uca.fiboo.actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.actores.Complemento.Tipo;

/**
 * Clase que contiene el bot�n en miniatura del complemento y su complemento real asignado.
 * Adem�s contiene un DragListener responsable de arrastrar y colocar los complementos.
 * 
 * @version 0.5
 * @author Sergio
 * 
 */
public class BotonComplemento extends Image {

	//Necesario para a�adir las imagenes y sus acciones
	private static Stage stage;
	
	private Complemento complemento;
	
	public BotonComplemento(Tipo tipo) {
		super(new Texture("data/complementos/vacio.png"));
		addQuitarComplementoListener(tipo);
	}
	
	public BotonComplemento(Complemento complemento) {
		super(new Texture(complemento.getIconPath()));
		this.complemento = complemento;
		addDragListener();
	}

	public Complemento getComplemento() {
		return complemento;
	}

	public void setStage(Stage stage) {
		BotonComplemento.stage = stage;
	}

	private void addQuitarComplementoListener(final Tipo tipo) {
		this.addListener(new InputListener() {
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				fibooGame.getPersonaje().removeComplemento(tipo);
				return super.touchDown(event, x, y, pointer, button);
			}
		});		
	}
	
	private class DragComplemento extends DragListener {
		private Image imagen;
		private Rectangle avatar, rImagen;

		public DragComplemento() {
			imagen = new Image(complemento.getImagen());
			
			float width = imagen.getWidth();
			float height = imagen.getHeight();
			rImagen = new Rectangle(imagen.getImageX(), imagen.getImageY(),	width, height);

			switch (complemento.getTipo()) {
			case OJOS:
			case PELO: 
			case GAFAS: 
			case MASCARA:
			case BIGOTE: 
			case ACCPELO: 
			case BOCA: 
				avatar = new Rectangle(102f, 318f, 256f, 256f);
				break;
			default:
				avatar = new Rectangle(102f, 97f, 256f, 512f);
				break;
			}
		}
		
		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			if(complemento.isDisponible()) {
				
				float dx = Gdx.input.getX() - imagen.getWidth() * 0.5f;
				float dy = Gdx.input.getY() + imagen.getHeight() * 0.5f;

				imagen.setPosition(dx, Gdx.graphics.getHeight() - dy);
				stage.addActor(imagen);
			}
			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchDragged(InputEvent event, float x, float y, int pointer) {
			if(complemento.isDisponible()) {
				float dx = Gdx.input.getX() - imagen.getWidth() * 0.5f;
				float dy = Gdx.input.getY() + imagen.getHeight() * 0.5f;
				imagen.setPosition(dx, Gdx.graphics.getHeight() - dy);
				rImagen.setPosition(imagen.getX(), imagen.getY());
			}
		}

		@Override
		public void touchUp(InputEvent event, float x, float y,	int pointer, int button) {
			if(complemento.isDisponible()) {
				if (rImagen.overlaps(avatar)) {
					imagen.addAction(Actions.sequence(
							Actions.moveTo(avatar.x, avatar.y, 0.8f),
							new Action() {
								@Override
								public boolean act(float delta) {
									fibooGame.getPersonaje().addComplemento(complemento);
									imagen.remove();
									return true;
								}
							}));
				} else {
					imagen.remove();
				}
			}
			super.touchUp(event, x, y, pointer, button);
		}
	}
	
	private void addDragListener() {
		this.addListener(new DragComplemento());
	}
}
