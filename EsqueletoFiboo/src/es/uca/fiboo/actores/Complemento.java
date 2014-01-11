package es.uca.fiboo.actores;

import com.badlogic.gdx.graphics.Texture;

/**
 * El complemento guarda la imagen de este y su disponibilidad, 
 * 
 * @version 0.2
 * @author Sergio
 *
 */
public class Complemento {
	
	public static enum Tipo {
		PELO, ACCPELO, OJOS, BIGOTE, BOCA, GAFAS, DISFRAZ, CAMISA, PANTALON, MASCARA;
	}
	
	private Tipo tipo;
	private String imagePath;
	private boolean disponible;
	private transient Texture imagen;
	
	//Necesario para Json
	public Complemento() {}
	
	public Complemento(String imagePath, Tipo tipo) {
		this.imagePath = imagePath;
		this.tipo = tipo;
		this.disponible = false;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	//Provisional mientras no hay Atlas.
	// disfraz1.png -> disfraz1Icon.png
	public String getIconPath() {
		String[] split = imagePath.split("[.]+");
		String path = split[0] + "Icon.png";
		
		return path;
	}
	
	public boolean isTipo(Tipo tipo) {
		if(tipo == this.tipo)
			return true;
		return false;
	}
	
	//Se instancia la imagen sólo si se necesita
	public Texture getImagen() {
		if(imagen == null) {
			imagen = new Texture(imagePath);
		}
		return imagen;
	}

	public String getImagePath() {
		return imagePath;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public boolean equals(Object object) {
		if(object instanceof Complemento) {
			if( ((Complemento)object).getImagePath().equals(imagePath) ) {
			return true;
			}
		}
		return false;
	}
	
}
