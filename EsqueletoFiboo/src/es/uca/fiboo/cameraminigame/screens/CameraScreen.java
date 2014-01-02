package es.uca.fiboo.cameraminigame.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.cameraminigame.actors.*;
import es.uca.fiboo.screens.AbstractScreen;

public class CameraScreen extends AbstractScreen {
	
	public CameraScreen(fibooGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
		
	private List<ObjetoActor> objetoActors;
	
	private List<ObjetoMenuActor> objetoMenuActors;
	
	private final static int N=10;
	int w,h;
	
	//private OrthographicCamera camera;
	
	private int indice;
	
	public void show() {	
		
		Gdx.input.setInputProcessor(stage);
		
		Gdx.app.log(fibooGame.LOG, "Cargando imagen de fondo y a�adiendola al escenario");
		Image imgFondo = new Image(fibooGame.MANAGER.get("cameraminigame/cesped.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		// Antes que nada, preparamos la carga de texturas.
		// TODO: Esto podría moverse a otra parte y optimizarse más adelante.

		//camera = new OrthographicCamera();
		
		objetoActors = new ArrayList<ObjetoActor>();
		objetoMenuActors = new ArrayList<ObjetoMenuActor>();
		// Construimos los elementos que vamos a usar en nuestro juego.
		for(indice=0; indice<N; ++indice) {
			//tipoImagen = TipoImagen.values()[ind];
			objetoActors.add(new ObjetoActor(indice));
			objetoMenuActors.add(new ObjetoMenuActor(indice));

			/*objetoActors[indice].addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer,
						int button) {
					//Gdx.app.log(Makipong.LOG, "Screen: x=" + x + "; y=" + y);
					objetoActors[indice].remove();
					objetoMenuActor[indice].remove();
					//TODO: Mostrar pantalla de Bien Hecho!
					return true;
				}
			});*/
		}
		// Posicionamos los objetoActors en la pantalla. La pelota se sitúa en el
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
			objetoActors.get(ind).setPosition(MathUtils.random(0f, w), MathUtils.random(0f, h));
			objetoMenuActors.get(ind).setPosition(0,espaciado+=50);
			stage.addActor(objetoActors.get(ind));
			stage.addActor(objetoMenuActors.get(ind));
		}	
	}
	
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		/*stage.setCamera(camera);
		stage.setViewport(1024, 512, false);
		
		this.handleInput();*/
		
		stage.act();		// Dejamos que el escenario se actualice...
		stage.draw();		// Y lo renderizamos todo.
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}


	/*public void handleInput() {
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
	}*/

	
}