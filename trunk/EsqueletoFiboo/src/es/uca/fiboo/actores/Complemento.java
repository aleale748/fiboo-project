package es.uca.fiboo.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Complemento extends Image {
	
	public static enum Tipo {
		OJOS, PELO, GAFAS, DISFRAZ, CAMISA, PANTALON, MASCARA, BASE;
	}
	private String ruta;
	private Tipo tipo;
	private Rectangle bounds;
	
	public Complemento(Texture imagen, Tipo tipo) {
		super(imagen);
		this.tipo = tipo;
		this.bounds = new Rectangle(getX(), getY(), imagen.getWidth(), imagen.getHeight());
	}
	/*public Complemento(Texture imagen, Tipo tipo, String ruta) {
		super(imagen);
		this.ruta= ruta;
		this.tipo = tipo;
		this.bounds = new Rectangle(getX(), getY(), imagen.getWidth(), imagen.getHeight());
	}*/
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public boolean isTipo(Tipo tipo) {
		if(tipo == this.tipo)
			return true;
		return false;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		bounds.setPosition(x, y);
	}

	public String getRuta() {
		return ruta;
	}

	
}
