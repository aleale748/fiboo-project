package es.uca.fiboo.actores;

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
	
	public boolean contains(Complemento complemento) {
		return avatar.cointains(complemento);
	}
}
