package es.uca.fiboo.ascensor.screens;

import com.badlogic.gdx.graphics.Texture;

public class Herramienta {
	public static enum Tipo {
		BUILD, MAGIC, PAINT, CABLE, COOKCAR, COOKTOM, MUSIC, BROOM, HAMMER, SMASH, MINUS, PLUS, GO, DOOR, ROOM, NUM;
	}
	
	private Tipo tipo;
	private String imagePath;
	private boolean disponible;
	private transient Texture imagen;
	
	//Necesario para Json
	public Herramienta() {}
	
	public Herramienta(String imagePath, Tipo tipo) {
		this.imagePath = imagePath;
		this.tipo = tipo;
		this.disponible = true;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	//Provisional mientras no hay Atlas.
	// disfraz1.png -> disfraz1Mini.png
	public String getIconPath() {
//		String[] split = imagePath.split("[.]+");
//		String path = split[0] + "droplet.png";
//		
//		if(tipo != Tipo.HAMMER && tipo != Tipo.BROOM && tipo != Tipo.PAINT && tipo != Tipo.CABLE && tipo != Tipo.COOKCAR)
//			return path;
		return imagePath;
	}
	
	public boolean isTipo(Tipo tipo) {
		if(tipo == this.tipo)
			return true;
		return false;
	}
	
	//Se instancia la imagen solo si se necesita
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
		if(object instanceof Herramienta) {
			if( ((Herramienta)object).getImagePath().equals(imagePath) ) {
			return true;
			}
		}
		return false;
	}
	
}


