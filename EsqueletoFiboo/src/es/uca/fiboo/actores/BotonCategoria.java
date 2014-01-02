package es.uca.fiboo.actores;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import es.uca.fiboo.actores.Complemento.Tipo;
import es.uca.fiboo.screens.PruebaComplementosScreen;

public class BotonCategoria {

	private PruebaComplementosScreen parent;
	private ArrayList<BotonComplemento> complementos;
	private Tipo tipo;
	
	private Image icono;
	

	public BotonCategoria(PruebaComplementosScreen parent, ArrayList complementos, Tipo tipo) {
		this.parent = parent;
		this.complementos = complementos;
		this.tipo = tipo;
		
		setImagen();
		setAcciones();
	}
	
	public Image getIcono() {
		return icono;
	}
	
	private void setImagen() {
		switch(tipo) {
		case OJOS: 
			icono = new Image(new Texture("iconos/ojos.png")); break;
		case PELO: 
			icono = new Image(new Texture("iconos/pelo.png")); break;
		case ACCPELO: 
			icono = new Image(new Texture("iconos/accpelo.png")); break;
		case BIGOTE: 
			icono = new Image(new Texture("iconos/bigote.png")); break;
		case BOCA: 
			icono = new Image(new Texture("iconos/boca.png")); break;
		case GAFAS:
			icono = new Image(new Texture("iconos/gafas.png")); break;
		case DISFRAZ:
			icono = new Image(new Texture("iconos/disfraz.png")); break;
		case CAMISA:
			icono = new Image(new Texture("iconos/camisa.png")); break;
		case PANTALON:
			icono = new Image(new Texture("iconos/pantalon.png")); break;
		case MASCARA:
			icono = new Image(new Texture("iconos/mascara.png")); break;
		}
		
	}

	private void setAcciones() {
		final Window popup = new Window("Tipo", parent.getSkin());
		popup.setX(Gdx.graphics.getWidth() - 200f);
		popup.setY((Gdx.graphics.getHeight() - 512f) / 2f);
		
		int newRow = 0;
		for(BotonComplemento b : complementos) {
			popup.add(b).width(128).height(128);
			newRow++;
			// 3 complementos por cada fila
			if(newRow > 2) {
				newRow = 0;
				popup.row();
			}
		}
		
		icono.addListener(new InputListener() {

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				parent.getStage().addActor(popup);
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
	}
		
}
