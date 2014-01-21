package es.uca.fiboo.personalizar.actores;

import java.util.List;

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

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.personalizar.actores.Complemento.Tipo;
import es.uca.fiboo.personalizar.screens.PersonalizacionScreen;

public class BotonCategoria {

	private transient final PersonalizacionScreen parent;
	private transient final List<BotonComplemento> complementos;
	private transient final Tipo tipo;
	
	private final Image icono;
	private transient Window popup;	

	public BotonCategoria(final PersonalizacionScreen parent, final List<BotonComplemento> complementos, final Tipo tipo) {
		this.parent = parent;
		this.complementos = complementos;
		this.tipo = tipo;
		
		final String iconPath = "complementos/iconos/" + tipo.toString().toLowerCase() + ".png";
		final Texture imagenIcono = FibooGame.MANAGER.get(iconPath, Texture.class);
		imagenIcono.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		icono = new Image(imagenIcono); 
		//Gdx.app.log(FibooGame.LOG, "Creando imagen de icono " + tipo.toString());
		
		setAcciones();
	}
	
	public Image getIcono() {
		return icono;
	}
	
	private void setAcciones() {
		popup = new Window(tipo.toString(), parent.getSkin());
		final TextButton exitButton = new TextButton("X", parent.getSkin());
		
		final float winHeight = Gdx.graphics.getHeight() * 0.25f;
		final float winWidth = winHeight;
		final float padding = Gdx.graphics.getHeight() * 0.05f;
		popup.getButtonTable().add(exitButton).height(Gdx.graphics.getHeight() * 0.1f).width(Gdx.graphics.getHeight() * 0.1f);
		
		if(complementos.isEmpty()) {
			popup.add("No tienes\ncomplementos\nde este tipo");
		}
		else {
			int newRow = 0;
			int maxPorFila;
			int masComps = 5;
			if(complementos.size() > masComps) { 
				maxPorFila = 3;
			}
			else {
				maxPorFila = 2;
			}
			
			for(final BotonComplemento b : complementos) {
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
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				//Gdx.app.log(FibooGame.LOG, "Pulsando icono...");
				//Si habia una ventana antes la quitamos
				for(final Actor c : parent.getStage().getActors()) {
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
			public void clicked(final InputEvent event, final float x, final float y) {
				popup.remove();
				super.clicked(event, x, y);
			}
		});
	}
}
