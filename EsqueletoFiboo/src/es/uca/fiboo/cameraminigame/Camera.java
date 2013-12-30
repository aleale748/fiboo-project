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

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Makipong implements ApplicationListener {
	
	/** El gestor de recursos que usa todo el juego. */
	public static final AssetManager MANAGER = new AssetManager();

	public static String LOG;
	
	/** Escenario donde agruparemos los objetos. */
	private Stage stage;
		
	/** Palas usadas en nuestro juego. */
	private Objeto objeto1, objeto2;
	
	private Objeto[] Objetos;
	
	
	/** Bola usada en el juego. */
	private BolaActor objeto3;
	
	private ObjetosMenu[] objetosMenu;
	public Map<Objeto,Objeto> tabla;

	//public enum TipoImagen{UNO,DOS,TRES};
	//TipoImagen tipoImagen;
	
	private final static int N=10;
	int w,h;
	
	private OrthographicCamera camera;
	
	private Texture fondo;
	
	@Override
	public void create() {
		// Antes que nada, preparamos la carga de texturas.
		// TODO: Esto podría moverse a otra parte y optimizarse más adelante.
		
		Texture.setEnforcePotImages(false);
		
		MANAGER.load("bola.png", Texture.class);
		MANAGER.load("paleta.png", Texture.class);
		MANAGER.load("parque.png", Texture.class);
		MANAGER.load("0.png", Texture.class);
		MANAGER.load("1.png", Texture.class);
		MANAGER.load("2.png", Texture.class);
		MANAGER.load("3.png", Texture.class);
		MANAGER.load("4.png", Texture.class);
		MANAGER.load("5.png", Texture.class);
		MANAGER.load("6.png", Texture.class);
		MANAGER.load("7.png", Texture.class);
		MANAGER.load("8.png", Texture.class);
		MANAGER.load("9.png", Texture.class);
		MANAGER.load("menu0.png", Texture.class);
		MANAGER.load("menu1.png", Texture.class);
		MANAGER.load("menu2.png", Texture.class);
		MANAGER.load("menu3.png", Texture.class);
		MANAGER.load("menu4.png", Texture.class);
		MANAGER.load("menu5.png", Texture.class);
		MANAGER.load("menu6.png", Texture.class);
		MANAGER.load("menu7.png", Texture.class);
		MANAGER.load("menu8.png", Texture.class);
		MANAGER.load("menu9.png", Texture.class);
		MANAGER.finishLoading();

		camera = new OrthographicCamera();
		
		Fondo fondo = new Fondo();
		
		Objetos = new Objeto[N];
		objetosMenu = new ObjetosMenu[N];
		
		tabla = new HashMap<Objeto,Objeto>();
		// Construimos los elementos que vamos a usar en nuestro juego.
		for(int ind=0;ind<N;++ind) {
			//tipoImagen = TipoImagen.values()[ind];
			Objetos[ind] = new Objeto(ind);
			Objetos[ind].addListener(new PalaUserInput(Objetos[ind],objetosMenu[ind]));
			
			objetosMenu[ind] = new ObjetosMenu(ind);
			objetosMenu[ind].addListener(new PalaUserInput(Objetos[ind],objetosMenu[ind]));
		}
		objeto3 = new BolaActor();
		// Posicionamos los objetos en la pantalla. La pelota se sitúa en el
		// centro de toda la pantalla. Ambas paletas se centran verticalmente,
		// una se alinea a la izquierda y la otra se alinea a la derecha
		// separadas 30 píxeles de los bordes de la pantalla.
		
		// Para centrar cosas en la pantalla simplemente hay que restar el
		// ancho o el alto de la pantalla (según la coordenada en la que
		// estemos) al ancho o el alto del objeto que pretendamos centrar,
		// y dividir esa resta entre dos. Geométricamente puede demostrarse
		// esto, que también puede expresarse como restar la mitad del ancho
		// o del alto de la pantalla a la mitad del ancho o el alto del objeto.
		
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		int espaciado = -50;
		for(int ind=0;ind<N;++ind) {
			//float randomX = random(0f, w)
			Objetos[ind].setPosition(MathUtils.random(0f, w), MathUtils.random(0f, h));
			objetosMenu[ind].setPosition(0,espaciado+=50);
		}		
		// Finalmente construimos el escenario y añadimos los actores.
		
		stage = new Stage();
		
		stage.addActor(fondo);
		for(int ind=0;ind<N;++ind) {
			stage.addActor(Objetos[ind]);
			stage.addActor(objetosMenu[ind]);
		}		
		
		// El escenario es un InputProcessor. Como vamos a gestionar la entrada
		// directamente desde los actores, podemos capturar la entrada usando
		// el escenario directamente.
		Gdx.input.setInputProcessor(stage);
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO: Añadir código al escalar el escenario.
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT); // Limpiamos la pantalla
		
		//stage.batch.draw(bolaTex, getX(), getY());
		//stage.draw(fondo,this.w,this.h);
		
		stage.setCamera(camera);
		stage.setViewport(1024, 512, false);
		
		this.handleInput();
		
		stage.act();		// Dejamos que el escenario se actualice...
		stage.draw();		// Y lo renderizamos todo.
	}
	
	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		MANAGER.dispose();
	}

	public void handleInput() {
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			if (camera.zoom < 1)
				camera.zoom += 0.02;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
			if (camera.zoom > 0.1f)
				camera.zoom -= 0.02;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			//if (camera.position.x > 0)
				camera.translate(-3, 0, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			//if (camera.position.x < 1024)
				camera.translate(3, 0, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			//if (camera.position.y > 0)
				camera.translate(0, -3, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			//if (camera.position.y < 1024)
				camera.translate(0, 3, 0);
		}
	}

	
}