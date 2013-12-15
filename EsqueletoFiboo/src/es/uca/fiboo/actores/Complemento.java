package es.uca.fiboo.actores;

import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * @version 0.1
 * @author Sergio
 * 
 * @param tipo Tipo del complemento
 * @param imagePath Ruta donde se encuentra la imagen
 * @param imagen Textura cargada de la imagen
 *
 */
public class Complemento {
	
	public static enum Tipo {
		OJOS, PELO, GAFAS, DISFRAZ, CAMISA, PANTALON, MASCARA;
	}
	
	private Tipo tipo;
	private String imagePath;
	private transient Texture imagen;
	
	//Constructor vacío necesario para leer de Json
	public Complemento() {}
	
	public Complemento(String imagePath, Tipo tipo) {
		this.imagePath = imagePath;
		this.imagen = new Texture(imagePath);
		this.tipo = tipo;		
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public boolean isTipo(Tipo tipo) {
		if(tipo == this.tipo)
			return true;
		return false;
	}
	
	public Texture getImagen() {
		//Cuando se crea desde Json no se carga la imagen, con esto
		//nos aseguramos de que se cargue
		if(imagen == null) {
			imagen = new Texture(imagePath);
		}
		return imagen;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}


	@Override
	public String toString() {
		return "Complemento [tipo=" + tipo + ", imagePath=" + imagePath + "]";
	}
	
}
