package es.uca.fiboo.cameraminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

//import es.uca.fiboo.camera.ObjetoMenuActor.TipoImagen;

/**
 * La paleta es el elemento que se usa para golpear la bola.
 * 
 * @author danirod
 */
public class ObjetoActor extends Actor {
	
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
	
	public ObjetoActor(int tipoInt) {
		String nombreFichero = Integer.toString(tipoInt) + ".png";
		texture = fibooGame.MANAGER.get(nombreFichero);
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
