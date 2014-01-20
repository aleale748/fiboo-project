package es.uca.fiboo.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.uca.fiboo.personalizar.actores.Complemento;
import es.uca.fiboo.personalizar.actores.Complemento.Tipo;

/**
 * Aun una versión muy básica, sólo con el avatar
 * 
 * @author Sergio
 */
public class Personaje {
	
	private final Avatar avatar;
	
	public Personaje() {
		avatar = new Avatar();
	}
	
	public void addComplemento(final Complemento complemento) {
		avatar.addComplemento(complemento);
	}

	public void drawAvatar(final SpriteBatch batch, final float escala) {
		avatar.draw(batch, escala);
	}
	
	public Avatar getAvatar() {
		return avatar;
	}

	public void removeComplemento(final Tipo tipo) {
		avatar.removeComplemento(tipo);		
	}
}
