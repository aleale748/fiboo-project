package es.uca.fiboo.personalizar.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import es.uca.fiboo.FibooGame;

/**
 * El complemento guarda la imagen de este y su disponibilidad, 
 * 
 * @version 0.4
 * @author Sergio
 *
 */
public class Complemento {
	
	private Tipo tipo;
	private String imagePath;
	private boolean disponible;
	private transient Texture imagen, icono;
	
	public static enum Tipo {
		PELO, ACCPELO, OJOS, BIGOTE, BOCA, GAFAS, DISFRAZ, CAMISETA, PANTALON, MASCARA;
	}
	
	//Necesario para Json
	public Complemento() {}
	
	public Complemento(final String imagePath, final Tipo tipo) {
		this.imagePath = imagePath;
		this.tipo = tipo;
		this.disponible = false;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public Texture getIcon() {
		if(icono == null) {	
			//Gdx.app.log("Complemento", "Cargando " + imagePath + "Icon");
			icono = FibooGame.MANAGER.get("complementos/" + imagePath + "Icon.png", Texture.class);
			icono.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		return icono;
	}
	
	public Texture getImagen() {
		if(imagen == null) {
			//Gdx.app.log("Complemento", "Cargando " + imagePath);
			imagen = FibooGame.MANAGER.get("complementos/" + imagePath + ".png", Texture.class);
			imagen.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		return imagen;
	}

	public String getImagePath() {
		return imagePath;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(final boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public int hashCode() {
		return 1; //No se utlilizará HashMap ni HashTable
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
