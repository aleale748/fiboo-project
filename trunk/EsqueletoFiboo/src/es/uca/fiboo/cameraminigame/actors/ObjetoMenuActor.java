package es.uca.fiboo.cameraminigame.actors;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class ObjetoMenuActor extends Actor {
	
	private Texture texture;
	
	enum TipoImagen {
		UNO,DOS,TRES;
		private int tipoInt;
		TipoImagen() {}
		TipoImagen(int tipoInt) {
			this.tipoInt = tipoInt;
		}
		public int getTipo() {
			return tipoInt;
		}
	}
	public TipoImagen tipoImagen;
	private int tipoInt;
	
	public ObjetoMenuActor(int tipoInt) {
		String nombreFichero = "menu" + Integer.toString(tipoInt) + ".png";
		texture = fibooGame.MANAGER.get(nombreFichero);
		
		// Ahora que tenemos la textura de la pala aprovechamos para cambiar
		// el tamaño de este actor y hacer que coincida con el tamaño de
		// la pala.
		setSize(texture.getWidth(), texture.getHeight());
		
		this.tipoInt = tipoInt;
	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY());
	}

	/*public TipoImagen getTipoImagen() {
		return tipoImagen;
	}*/

	public int getTipoInt() {
		return tipoInt;
	}
	
}
