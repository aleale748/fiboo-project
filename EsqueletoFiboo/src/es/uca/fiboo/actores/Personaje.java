package es.uca.fiboo.actores;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Personaje {
	
	private Avatar avatar;
	
	public Personaje() {
		avatar = new Avatar();
	}
	
	public void addComplemento(Complemento complemento) {
		avatar.addComplemento(complemento);
	}
	
	public boolean contains(Complemento complemento) {
		return avatar.cointains(complemento);
	}
	
	public void drawAvatar(SpriteBatch batch) {
		avatar.draw(batch);
	}
	
	@Override
	public String toString() {
		String data = "";
		data += avatar.toString();
		return data;
	}
}
