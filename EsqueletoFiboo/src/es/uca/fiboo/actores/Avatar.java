package es.uca.fiboo.actores;

import java.util.ArrayList;

public class Avatar {

	ArrayList<Complemento> comps;
	
	public Avatar() {
		comps = new ArrayList<Complemento>();
	}
	
	/*
	 * Faltan algunas comprobaciones:
	 * - Si le pone camisa o pantalón se elimina disfraz si lo hay
	 * - Si le pone máscara se elimina el pelo y gafas si lo hay
	 * - Si le pone disfraz se elimina camisa y pantalón
	 */
	public void addComplemento(Complemento c) {
		int size = comps.size();
		
		for(int i = 0; i < size; i++) {
			Complemento comp = comps.get(i);
			if(comp.getTipo() == c.getTipo()) {
				if(comp.getActions().size > 0) {
					//Se elimina el action añadido al insertarlo
					comp.removeAction(comp.getActions().first());
				}
				comp.remove(); //Eliminar del stage
				comps.remove(i); //Eliminar del vector
				break;
			}
		}	
		comps.add(c);
	}

	/**
	 * Saca todos los complementos del vector,
	 * lo que dejaría sólo al avatar base (default)
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
