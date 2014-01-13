package es.uca.fiboo.actores;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
			icono = new Image(fibooGame.atlasComplementos.findRegion("ojos")); break;
		case PELO: 
			icono = new Image(fibooGame.atlasComplementos.findRegion("pelo")); break;
		case ACCPELO: 
			icono = new Image(fibooGame.atlasComplementos.findRegion("accpelo")); break;
		case BIGOTE: 
			icono = new Image(fibooGame.atlasComplementos.findRegion("bigote")); break;
		case BOCA: 
			icono = new Image(fibooGame.atlasComplementos.findRegion("boca")); break;
		case GAFAS:
			icono = new Image(fibooGame.atlasComplementos.findRegion("gafas")); break;
		case DISFRAZ:
			icono = new Image(fibooGame.atlasComplementos.findRegion("disfraz")); break;
		case CAMISA:
			icono = new Image(fibooGame.atlasComplementos.findRegion("camisa")); break;
		case PANTALON:
			icono = new Image(fibooGame.atlasComplementos.findRegion("pantalon")); break;
		case MASCARA:
			icono = new Image(fibooGame.atlasComplementos.findRegion("mascara")); break;
		}
		
	}

	private void setAcciones() {
		final Window popup = new Window(tipo.toString(), parent.getSkin());
		TextButton exitButton = new TextButton("X", parent.getSkin());
		
		float winHeight = Gdx.graphics.getHeight() * 0.3f;
		float winWidth = winHeight;
		popup.getButtonTable().add(exitButton).height(Gdx.graphics.getHeight() * 0.1f).width(Gdx.graphics.getHeight() * 0.1f);
		
		if(complementos.isEmpty()) {
			popup.add("No tienes\ncomplementos\nde este tipo");
		}
		else {
			int newRow = 0;
			for(BotonComplemento b : complementos) {
				popup.add(b).width(winWidth).height(winHeight);
				newRow++;
				// 2 complementos por cada fila
				if(newRow > 1) {
					newRow = 0;
					popup.row().fill().expandX();
				}
			}
		}
		popup.add(new BotonComplemento(tipo)).width(winWidth).height(winHeight);
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
				
				popup.setX(Gdx.graphics.getWidth() / 2f);
				popup.setY(Gdx.graphics.getHeight() / 2f);
				parent.getStage().addActor(popup);
				
				return super.touchDown(event, x, y, pointer, button);
			}
		
		});
		
		exitButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				popup.remove();
				super.clicked(event, x, y);
			}
		});
	}
		
}
