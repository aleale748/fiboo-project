package es.uca.fiboo.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import es.uca.fiboo.fibooGame;

public class BotonComplemento extends Image {

	private Complemento complemento;
	
	public BotonComplemento(String filePath, Complemento complemento) {
		super(new Texture(filePath));
		this.complemento = complemento;
		addDragListener();
	}
	
	public Complemento getComplemento() {
		return complemento;
	}
	
	private void addDragListener() {
		this.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				fibooGame.personaje.addComplemento(complemento);
				return true;
			}
		});
	}

}
