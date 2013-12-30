/*
 * Makipong, just another Pong clone
 * Copyright (C) 2013 Dani Rodríguez
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package es.uca.fiboo.camera;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * La paleta es el elemento que se usa para golpear la bola.
 * 
 * @author danirod
 */
public class ObjetosMenu extends Actor {
	
	/** Textura usada por la pala. */
	private Texture texture;
	
	enum TipoImagen {
		UNO,DOS,TRES;
		private int tipoInt;
		TipoImagen() {}
		TipoImagen(int tipoInt) {
			this.tipoInt = tipoInt;
		}
		public int getTipo() {
			return tipoInt;
		}
	}
	public TipoImagen tipoImagen;
	private int tipoInt;
	
	public ObjetosMenu(int tipoInt) {
		String nombreFichero = "menu" + Integer.toString(tipoInt) + ".png";
		texture = Makipong.MANAGER.get(nombreFichero);
		
		// Ahora que tenemos la textura de la pala aprovechamos para cambiar
		// el tamaño de este actor y hacer que coincida con el tamaño de
		// la pala.
		setSize(texture.getWidth(), texture.getHeight());
		
		this.tipoInt = tipoInt;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY());
	}

	/*public TipoImagen getTipoImagen() {
		return tipoImagen;
	}*/

	public int getTipoInt() {
		return tipoInt;
	}
	
}
