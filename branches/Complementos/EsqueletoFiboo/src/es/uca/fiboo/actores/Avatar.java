package es.uca.fiboo.actores;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * @version 0.1
 * @author Sergio
 * 
 * @param comps Complementos actuales del avatar
 * @param base Imagen del avatar base (sin complementos)
 *
 */
public class Avatar {

	private ArrayList<Complemento> comps;
	private transient Texture base;
	
	public Avatar() {
		comps = new ArrayList<Complemento>();
		base = new Texture("data/complementos/nino.png");
	}
	
	//Faltan comprobaciones	
	public void addComplemento(Complemento c) {
		int size = comps.size();
		
		for(int i = 0; i < size; i++) {
			Complemento comp = comps.get(i);
			
			if(comp.getTipo() == c.getTipo()) {
				comps.remove(i);
				break;
			}
		}
		//TODO: añadir comprobaciones
		comps.add(c);
	}
	
	public boolean cointains(Complemento c) {
		return comps.contains(c);
	}
	
	//Dibuja la base del avatar y todos sus complementos
	public void draw(SpriteBatch batch) {
		batch.draw(base, 100f, 100f);
		
		for(Complemento c : comps) {
			switch(c.getTipo()) {
			case OJOS:
			case PELO:
			case GAFAS:
				batch.draw(c.getImagen(), 102f, 318f);
				break;
			default:
				batch.draw(c.getImagen(), 102f, 97f);
				break;
			}
		}
	}
	
	public void setComplementos(ArrayList<Complemento> comps) {
		this.comps = comps;
	}

	@Override
	public String toString() {
		return "Avatar [comps=" + comps + "]";
	}
}
