package es.uca.fiboo.ascensor.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class AccionHerramienta extends Image{
	//Necesario para anadir las imagenes y sus acciones
	private static Stage stage;
	
	private Herramienta herramienta;
	
	public AccionHerramienta(Herramienta herramienta) {
		super(new Texture(herramienta.getIconPath()));
		this.herramienta = herramienta;
		addDragListener();
	}

	public Herramienta getHerramienta() {
		return herramienta;
	}

	public void setStage(Stage stage) {
		AccionHerramienta.stage = stage;
	}

	private void addDragListener() {
		this.addListener(new DragListener() {

			private Image imagen;
			private Rectangle cajaHer, rImagen;

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(herramienta.isDisponible()) {
					
					imagen = new Image(herramienta.getImagen());
					float dx = Gdx.input.getX() - imagen.getWidth() * 0.5f;
					float dy = Gdx.input.getY() + imagen.getHeight() * 0.5f;
	
					imagen.setPosition(dx, Gdx.graphics.getHeight() - dy);
					float width = imagen.getWidth();
					float height = imagen.getHeight();
					stage.addActor(imagen);
	
					rImagen = new Rectangle(imagen.getImageX(), imagen.getImageY(),	width, height);
	
					switch (herramienta.getTipo()) {
					case HAMMER:
					case BROOM: 
					case PAINT: 
					case CABLE:
					case COOKCAR: 
					case COOKTOM: 
					case MUSIC: 
						cajaHer = new Rectangle(102f, 318f, 256f, 256f);
						break;
					default:
						cajaHer = new Rectangle(102f, 97f, 256f, 512f);
						break;
					}
				}
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				if(herramienta.isDisponible()) {
					float dx = Gdx.input.getX() - imagen.getWidth() * 0.5f;
					float dy = Gdx.input.getY() + imagen.getHeight() * 0.5f;
					imagen.setPosition(dx, Gdx.graphics.getHeight() - dy);
					rImagen.setPosition(imagen.getX(), imagen.getY());
				}
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,	int pointer, int button) {
				if(herramienta.isDisponible()) {
					if (rImagen.overlaps(cajaHer)) {
						imagen.addAction(Actions.sequence(
								Actions.moveTo(cajaHer.x, cajaHer.y, 0.8f),
								new Action() {
									@Override
									public boolean act(float delta) {
										//Drop.getPersonaje().addComplemento(herramienta);
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
		});
	}



}
