package es.uca.fiboo.actores;

import java.util.ArrayList;
import java.util.TreeMap;

public class Avatar {

	ArrayList<Complemento> comps;
	TreeMap<Complemento.Tipo,String> complem;
	
	public Avatar() {
		//complem= new TreeMap<Complemento.Tipo, String>();
		comps = new ArrayList<Complemento>();
		
	}
	
	/*
	 * Faltan algunas comprobaciones:
	 * - Si le pone camisa o pantal�n se elimina disfraz si lo hay
	 * - Si le pone m�scara se elimina el pelo y gafas si lo hay
	 * - Si le pone disfraz se elimina camisa y pantal�n
	 */
	public void addComplemento(Complemento c) {
		int size = comps.size();
		
		for(int i = 0; i < size; i++) {
			Complemento comp = comps.get(i);
			if(comp.getTipo() == c.getTipo()) {
				if(comp.getActions().size > 0) {
					//Se elimina el action a�adido al insertarlo
					comp.removeAction(comp.getActions().first());
				}
				comp.remove(); //Eliminar del stage
				comps.remove(i); //Eliminar del vector
				break;
			}
		}	
		comps.add(c);
	}
	/*public void addComplem(Complemento.Tipo tipo, String ruta) {	
		complem.put(tipo,ruta);
	}*/ 

	/**
	 * Saca todos los complementos del vector,
	 * lo que dejar�a s�lo al avatar base (default)
	 */
	public void getDefault() {
		for(Complemento c : comps) {
			if(c.getActions().size > 0) {
				c.removeAction(c.getActions().first());
			}
			c.remove(); //Eliminar del stage
		}
		comps.clear();
	}
	
	public boolean cointains(Complemento c) {
		return comps.contains(c);
	}

}
