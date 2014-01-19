package es.uca.fiboo.cameraminigame.actors;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.cameraminigame.screens.CameraScreen;

public class ObjetoMenuActor extends Actor {
	
	private Texture texture;
	
	private int tipoInt;
	
	public ObjetoMenuActor(int tipoInt) {
		String nombreFichero = "menu" + Integer.toString(tipoInt) + ".png";
		texture = FibooGame.MANAGER.get("cameraminigame/" + nombreFichero, Texture.class);
		
		// Ahora que tenemos la textura de la pala aprovechamos para cambiar
		// el tama??o de este actor y hacer que coincida con el tama??o de
		// la pala.
		this.tipoInt = tipoInt;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), CameraScreen.tamanoMundo.x*0.055f, CameraScreen.tamanoMundo.x*0.055f);
	}

	public int getTipoInt() {
		return tipoInt;
	}
	
}
