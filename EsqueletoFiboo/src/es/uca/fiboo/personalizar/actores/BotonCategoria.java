package es.uca.fiboo.personalizar.actores;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.personalizar.actores.Complemento.Tipo;
import es.uca.fiboo.personalizar.screens.PersonalizacionScreen;

public class BotonCategoria {

	private PersonalizacionScreen parent;
	private ArrayList<BotonComplemento> complementos;
	private Tipo tipo;
	
	private Image icono;
	private Window popup;	

	public BotonCategoria(PersonalizacionScreen parent, ArrayList<BotonComplemento> complementos, Tipo tipo) {
		this.parent = parent;
		this.complementos = complementos;
		if(!complementos.isEmpty()) {
			this.complementos.get(0).setStage(parent.getStage());
		}
		this.tipo = tipo;
		
		String iconPath = "complementos/iconos/" + tipo.toString().toLowerCase() + ".png";
		Texture imagenIcono = fibooGame.MANAGER.get(iconPath, Texture.class);
		imagenIcono.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		icono = new Image(imagenIcono); 
		Gdx.app.log(fibooGame.LOG, "Creando imagen de icono " + tipo.toString());
		
		setAcciones();
	}
	
	public Image getIcono() {
		return icono;
	}
	
	private void setAcciones() {
		popup = new Window(tipo.toString(), parent.getSkin());
		TextButton exitButton = new TextButton("X", parent.getSkin());
		
		float winHeight = Gdx.graphics.getHeight() * 0.25f;
		float winWidth = winHeight;
		float padding = Gdx.graphics.getHeight() * 0.05f;
		popup.getButtonTable().add(exitButton).height(Gdx.graphics.getHeight() * 0.1f).width(Gdx.graphics.getHeight() * 0.1f);
		
		if(complementos.isEmpty()) {
			popup.add("No tienes\ncomplementos\nde este tipo");
		}
		else {
			int newRow = 0;
			int maxPorFila;
			if(complementos.size() > 5) 
				maxPorFila = 3;
			else
				maxPorFila = 2;
			
			for(BotonComplemento b : complementos) {
				popup.add(b).width(winWidth).height(winHeight).padTop(padding).padRight(padding / 2f).padLeft(padding / 2f);
				newRow++;
				if(newRow > maxPorFila-1) {
					newRow = 0;
					popup.row().fill().expandX();
				}
			}
		}
		popup.add(new BotonComplemento(tipo)).width(winWidth).height(winHeight).padTop(padding).padRight(padding / 2f).padLeft(padding / 2f);
		popup.pack();
		
		icono.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
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
