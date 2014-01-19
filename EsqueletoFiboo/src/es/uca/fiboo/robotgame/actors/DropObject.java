package es.uca.fiboo.robotgame.actors;

import com.badlogic.gdx.math.Rectangle;

public class DropObject extends Rectangle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static enum Drop {PLANETA, ESTRELLA, LUNA, MARCIANO};
	private Drop tipo;
	public DropObject(int tipo){
		switch (tipo){
		case 0: 
			this.setTipo(DropObject.Drop.PLANETA);
			break;
		case 1:
			this.setTipo(DropObject.Drop.ESTRELLA);
			break;
		case 2:
			this.setTipo(DropObject.Drop.LUNA);
			break;
		case 3:
			this.setTipo(DropObject.Drop.MARCIANO);
			break;
		default: break;
		}
	}
	public Drop getTipo() {
		return tipo;
	}
	public void setTipo(Drop tipo) {
		this.tipo = tipo;
	}
	
}
