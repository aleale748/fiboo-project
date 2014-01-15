package es.uca.fiboo.personalizar.actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import es.uca.fiboo.fibooGame;

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
	private transient Texture imagen, icono;
	
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
	
	public Texture getIcon() {
		if(icono == null) {	
			Gdx.app.log("Complemento", "Cargando " + imagePath + "Icon");
			icono = fibooGame.MANAGER.get("complementos/" + imagePath + "Icon.png", Texture.class);
			icono.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		return icono;
	}
	
	public boolean isTipo(Tipo tipo) {
		if(tipo == this.tipo)
			return true;
		return false;
	}
	
	public Texture getImagen() {
		if(imagen == null) {
			Gdx.app.log("Complemento", "Cargando " + imagePath);
			imagen = fibooGame.MANAGER.get("complementos/" + imagePath + ".png", Texture.class);
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
