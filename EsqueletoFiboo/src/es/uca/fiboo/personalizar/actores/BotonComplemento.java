package es.uca.fiboo.personalizar.actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.personalizar.actores.Complemento.Tipo;

/**
 * Clase que contiene el botón en miniatura del complemento y su complemento real asignado.
 * Además contiene un DragListener responsable de arrastrar y colocar los complementos.
 * 
 * @version 0.5
 * @author Sergio
 * 
 */
public class BotonComplemento extends Image {

	//Necesario para añadir las imagenes y sus acciones
	private static Stage stage;
	
	private Complemento complemento;
	private float escala;
	
	public BotonComplemento(Tipo tipo) {
		super(fibooGame.atlasComplementos.findRegion("vacio"));
		addQuitarComplementoListener(tipo);
	}
	
	public BotonComplemento(Complemento complemento) {
		super(complemento.getIcon());
		this.complemento = complemento;
		this.escala = Gdx.graphics.getHeight() * 0.7f;
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
			Gdx.app.log(fibooGame.LOG, "Creando imagen de complemento " + complemento.getImagePath());
			imagen.setSize(escala, escala);
			
			float posAvatarX = 0 + escala / 4f;
			float posAvatarY;
			float widthRectAvatar = escala / 2f;
			float heightRectAvatar;
			float widthRectImg = widthRectAvatar;
			float heightRectImg;
			
			//Si son complementos grandes el Rect de avatar será completo.
			//Si son complementos de la cara/cabeza el Rect de avatar será la mitad.
			switch(complemento.getTipo()) {
			case DISFRAZ:
			case CAMISA:
			case PANTALON:
				posAvatarY = (Gdx.graphics.getHeight() - escala) / 2f;
				heightRectAvatar = escala;
				heightRectImg = escala;
				break;
			default:
				posAvatarY = Gdx.graphics.getHeight() / 2f;
				heightRectAvatar = escala / 2f;
				heightRectImg = heightRectAvatar;
			}
			
			avatar = new Rectangle(posAvatarX, posAvatarY, widthRectAvatar, heightRectAvatar);
			rImagen = new Rectangle(-500f, -500f, widthRectImg, heightRectImg);
		}
				
		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			if(complemento.isDisponible()) {
				
				float dxImagen = Gdx.input.getX() - imagen.getWidth() * 0.5f;
				float dyImagen = getPosicionY();
				float dxRect = Gdx.input.getX() - rImagen.getWidth() * 0.5f;
				float dyRect = Gdx.input.getY() + rImagen.getHeight() * 0.5f;

				imagen.setPosition(dxImagen, Gdx.graphics.getHeight() - dyImagen);
				rImagen.setPosition(dxRect, Gdx.graphics.getHeight() - dyRect);
				stage.addActor(imagen);
			}
			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchDragged(InputEvent event, float x, float y, int pointer) {
			if(complemento.isDisponible()) {
				float dxImagen = Gdx.input.getX() - imagen.getWidth() * 0.5f;
				float dyImagen = getPosicionY();
				float dxRect = Gdx.input.getX() - rImagen.getWidth() * 0.5f;
				float dyRect = Gdx.input.getY() + rImagen.getHeight() * 0.5f;

				imagen.setPosition(dxImagen, Gdx.graphics.getHeight() - dyImagen);
				rImagen.setPosition(dxRect, Gdx.graphics.getHeight() - dyRect);
			}
		}
		
		private float getPosicionY() {
			float dy;
			switch(complemento.getTipo()) {
			case DISFRAZ:
			case CAMISA:
			case PANTALON:
				dy = Gdx.input.getY() + imagen.getHeight() * 0.5f; break;
			case BIGOTE:
			case BOCA:
				dy = Gdx.input.getY() + imagen.getHeight() * 0.7f; break;
			case ACCPELO:
				dy = Gdx.input.getY() + imagen.getHeight() * 0.9f; break;
			default:
				dy = Gdx.input.getY() + imagen.getHeight() * 0.8f; break;
			}
			
			return dy;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y,	int pointer, int button) {
			if(complemento.isDisponible()) 
			{
				if (rImagen.overlaps(avatar)) {
					float toX = avatar.x - escala / 4f;
					float toY;
					
					switch(complemento.getTipo()) {
					case DISFRAZ:
					case CAMISA:
					case PANTALON:
						toY = avatar.y;
						break;
					default:
						toY = (Gdx.graphics.getHeight() - escala) / 2f;
					}
					
					imagen.addAction(Actions.sequence(
							Actions.moveTo(toX, toY, 0.8f),
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
