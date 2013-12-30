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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * La bola es el objetivo al que hay que golpear.
 * 
 * @author danirod
 */
public class BolaActor extends Actor {
	
	/** Textura usada por la bola. */
	private Texture bolaTex;
	
	private Vector2 velocidad;

	public BolaActor() {
		bolaTex = Makipong.MANAGER.get("bola.png");
		
		// Ahora que tenemos la textura de la bola aprovechamos para cambiar
		// el tamaño de este actor y hacer que coincida con el tamaño de
		// la bola.
		setSize(bolaTex.getWidth(), bolaTex.getHeight());
		velocidad = new Vector2(90.0f, 90.0f);
	}
	
	/*@Override
	public void act(float delta) {
		super.act(delta);
		translate(velocidad.x * delta, velocidad.y * delta);
		comprobarBolaEnPantalla();
	}
	
	private void comprobarBolaEnPantalla() {
		if(getX() < 0) {
			setX(0);
			velocidad.x *= -1;
		} else if(getRight() > Gdx.graphics.getWidth()) {
			setX(Gdx.graphics.getWidth() - getWidth());
			velocidad.x *= -1;
		}
		
		if(getY() < 0) {
			setY(0);
			velocidad.y *= -1;
		} else if(getTop() > Gdx.graphics.getHeight()) {
			setY(Gdx.graphics.getHeight() - getHeight());
			velocidad.y *= -1;
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(bolaTex, getX(), getY());
	}
	*/
}
