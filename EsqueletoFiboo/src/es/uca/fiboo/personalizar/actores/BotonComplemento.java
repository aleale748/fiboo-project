package es.uca.fiboo.personalizar.actores;

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

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.personalizar.actores.Complemento.Tipo;

/**
 * Clase que contiene el botón en miniatura del complemento y su complemento real asignado.
 * Además contiene un DragListener responsable de arrastrar y colocar los complementos.
 * 
 * @version 0.6
 * @author Sergio
 * 
 */
public class BotonComplemento extends Image {

	//Necesario para añadir las imagenes y sus acciones
	private static Stage stage;
	
	private transient Complemento complemento;
	private transient float escala;
	
	public BotonComplemento(final Tipo tipo) {
		super(FibooGame.MANAGER.get("complementos/vacio.png", Texture.class));
		addQuitarComplementoListener(tipo);
	}
	
	public BotonComplemento(final Complemento complemento) {
		super(complemento.getIcon());
		this.complemento = complemento;
		this.escala = Gdx.graphics.getHeight() * 0.7f;
		addDragListener();
	}

	public Complemento getComplemento() {
		return complemento;
	}

	public static void setBotonComplementoStage(final Stage stage) {
		BotonComplemento.stage = stage;
	}

	private void addQuitarComplementoListener(final Tipo tipo) {
		this.addListener(new InputListener() {
			
			@Override
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				FibooGame.getPersonaje().removeComplemento(tipo);
				return super.touchDown(event, x, y, pointer, button);
			}
		});		
	}
	
	private class DragComplemento extends DragListener {
		private transient final Image imagen;
		private transient final Rectangle avatar, rImagen;

		public DragComplemento() {
			super();
			imagen = new Image(complemento.getImagen());
			Gdx.app.log(FibooGame.LOG, "Creando imagen de complemento " + complemento.getImagePath());
			imagen.setSize(escala, escala);
			
			float posAvatarX = 0 + escala / 4f;
			float posAvatarY;
			float widthRectAvatar = escala / 2f;
			float heightRectAvatar;
			float widthRectImg = widthRectAvatar;
			float heightRectImg;
			
			//Si son complementos grandes el Rect de avatar serï¿½ completo.
			//Si son complementos de la cara/cabeza el Rect de avatar serï¿½ la mitad.
			switch(complemento.getTipo()) {
			case DISFRAZ:
			case CAMISETA:
			case PANTALON:
				posAvatarY = (Gdx.graphics.getHeight() - escala) / 2f;
				heightRectAvatar = escala;
				break;
			default:
				posAvatarY = Gdx.graphics.getHeight() / 2f;
				heightRectAvatar = escala / 2f;
			}
			heightRectImg = heightRectAvatar;
			
			avatar = new Rectangle(posAvatarX, posAvatarY, widthRectAvatar, heightRectAvatar);
			rImagen = new Rectangle(-500f, -500f, widthRectImg, heightRectImg);
		}
				
		@Override
		public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
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
		public void touchDragged(final InputEvent event, final float x, final float y, final int pointer) {
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
				case CAMISETA:
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
		public void touchUp(final InputEvent event, final float x, final float y, final int pointer, final int button) {
			if(complemento.isDisponible()) 
			{
				if (rImagen.overlaps(avatar)) {
					float toX = avatar.x - escala / 4f;
					float toY;
					
					switch(complemento.getTipo()) {
					case DISFRAZ:
					case CAMISETA:
					case PANTALON:
						toY = avatar.y; break;
					default:
						toY = (Gdx.graphics.getHeight() - escala) / 2f;
					}
					
					imagen.addAction(Actions.sequence(
						Actions.moveTo(toX, toY, 0.8f),
						new Action() {
							@Override
							public boolean act(float delta) {
								FibooGame.getPersonaje().addComplemento(complemento);
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
