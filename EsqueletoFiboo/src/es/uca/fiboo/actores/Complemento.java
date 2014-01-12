package es.uca.fiboo.actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	private transient TextureRegion imagen;
	
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
	public TextureRegion getIcon() {
		//String[] split = imagePath.split("[.]+");
		//String path = split[0] + "Icon";
		
		TextureRegion icono = fibooGame.atlasComplementos.findRegion(imagePath + "Icon");
		//icono.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return icono;
	}
	
	public boolean isTipo(Tipo tipo) {
		if(tipo == this.tipo)
			return true;
		return false;
	}
	
	//Se instancia la imagen sólo si se necesita
	public TextureRegion getImagen() {
		if(imagen == null) {
			Gdx.app.log(fibooGame.LOG, "Cargando " + imagePath);
			imagen = fibooGame.atlasComplementos.findRegion(imagePath);
			//imagen.setFilter(TextureFilter.Linear, TextureFilter.Linear);
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
