package es.uca.fiboo.robotgame.actors;

import com.badlogic.gdx.math.Rectangle;

public class DropObject extends Rectangle{

	private static final long serialVersionUID = 1L;
	private Drop tipo;
	public static enum Drop {PLANETA, ESTRELLA, LUNA, MARCIANO};

	public DropObject(final int tipo){
		super();
		switch (tipo){
			case 0: 
				this.setTipo(Drop.PLANETA);
				break;
			case 1:
				this.setTipo(Drop.ESTRELLA);
				break;
			case 2:
				this.setTipo(Drop.LUNA);
				break;
			case 3:
				this.setTipo(Drop.MARCIANO);
				break;
			default: break;
		}
	}
	
	public Drop getTipo() {
		return tipo;
	}
	
	public void setTipo(final Drop tipo) {
		this.tipo = tipo;
	}
	
}
