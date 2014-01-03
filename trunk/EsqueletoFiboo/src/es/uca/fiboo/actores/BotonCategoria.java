package es.uca.fiboo.actores;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.actores.Complemento.Tipo;
import es.uca.fiboo.screens.PruebaComplementosScreen;

public class BotonCategoria {

	private PruebaComplementosScreen parent;
	private ArrayList<BotonComplemento> complementos;
	private Tipo tipo;
	
	private Image icono;
	

	public BotonCategoria(PruebaComplementosScreen parent, ArrayList<BotonComplemento> complementos, Tipo tipo) {
		this.parent = parent;
		this.complementos = complementos;
		if(!complementos.isEmpty()) {
			this.complementos.get(0).setStage(parent.getStage());
		}
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
		final Window popup = new Window(tipo.toString(), parent.getSkin());
		//popup.setX(Gdx.graphics.getWidth() - 200f);
		//popup.setY((Gdx.graphics.getHeight() - 512f) / 2f);

		if(complementos.isEmpty()) {
			popup.add("No tienes complementos\nde este tipo");
		}
		else {
			int newRow = 0;
			for(BotonComplemento b : complementos) {
				popup.add(b).width(128).height(128);
				newRow++;
				// 2 complementos por cada fila
				if(newRow > 1) {
					newRow = 0;
					popup.row().fill().expandX();
				}
			}
		}
		popup.pack();
		
		icono.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Pulsando icono...");
				//Si habia una ventana antes la quitamos
				for(Actor c : parent.getStage().getActors()) {
					if(c instanceof Window) {
						c.remove();
						break;
					}
				}
				
				popup.setX(Gdx.input.getX());
				popup.setY(Gdx.graphics.getHeight() - Gdx.input.getY());
				parent.getStage().addActor(popup);
				
				return super.touchDown(event, x, y, pointer, button);
			}
		
		});
	}
		
}
