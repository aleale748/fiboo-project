package es.uca.fiboo.actores;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.uca.fiboo.personalizar.actores.Complemento;
import es.uca.fiboo.personalizar.actores.Complemento.Tipo;

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

	public void removeComplemento(Tipo tipo) {
		avatar.removeComplemento(tipo);		
	}
}
