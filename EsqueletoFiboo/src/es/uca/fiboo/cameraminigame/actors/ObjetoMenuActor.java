package es.uca.fiboo.cameraminigame.actors;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class ObjetoMenuActor extends Actor {
	
	private Texture texture;
	
	private int tipoInt;
	
	public ObjetoMenuActor(int tipoInt) {
		String nombreFichero = "menu" + Integer.toString(tipoInt) + ".png";
		texture = fibooGame.MANAGER.get("cameraminigame/" + nombreFichero, Texture.class);
		
		// Ahora que tenemos la textura de la pala aprovechamos para cambiar
		// el tama??o de este actor y hacer que coincida con el tama??o de
		// la pala.
		setSize(texture.getWidth(), texture.getHeight());
		this.tipoInt = tipoInt;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY());
	}

	public int getTipoInt() {
		return tipoInt;
	}
	
}
