package es.uca.fiboo.actores;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Aun una versión muy básica, sólo con el avatar
 * 
 * @author Sergio
 */
public class Personaje {
	
	private Avatar avatar;
	
	public Personaje() {
		avatar = new Avatar();
	}
	
	public void addComplemento(Complemento complemento) {
		avatar.addComplemento(complemento);
	}

	public void drawAvatar(SpriteBatch batch) {
		avatar.draw(batch);
	}
	
	public Avatar getAvatar() {
		return avatar;
	}
}
