package es.uca.fiboo.camera;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Camera extends AbstractScreen {
	
	public Camera(fibooGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	/** El gestor de recursos que usa todo el juego. */
	public static final AssetManager MANAGER = new AssetManager();

	public static String LOG;
	
	/** Escenario donde agruparemos los objetoActors. */
	private Stage stage;
		
	private ObjetoActor[] objetoActors;
	
	private ObjetoMenuActor[] objetoMenuActor;
	public Map<ObjetoActor,ObjetoActor> tabla;

	//public enum TipoImagen{UNO,DOS,TRES};
	//TipoImagen tipoImagen;
	
	private final static int N=10;
	int w,h;
	
	private OrthographicCamera camera;
	
	private int indice;
	
	public void create() {
		// Antes que nada, preparamos la carga de texturas.
		// TODO: Esto podría moverse a otra parte y optimizarse más adelante.
		
		//Texture.setEnforcePotImages(false);
		
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
		
		FondoActor fondoActor = new FondoActor();
		
		objetoActors = new ObjetoActor[N];
		objetoMenuActor = new ObjetoMenuActor[N];
		
		tabla = new HashMap<ObjetoActor,ObjetoActor>();
		// Construimos los elementos que vamos a usar en nuestro juego.
		for(indice=0; indice<N; ++indice) {
			//tipoImagen = TipoImagen.values()[ind];
			objetoActors[indice] = new ObjetoActor(indice);
			objetoMenuActor[indice] = new ObjetoMenuActor(indice);

			objetoActors[indice].addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer,
						int button) {
					//Gdx.app.log(Makipong.LOG, "Screen: x=" + x + "; y=" + y);
					objetoActors[indice].remove();
					objetoMenuActor[indice].remove();
					//TODO: Mostrar pantalla de Bien Hecho!
					return true;
				}
			});
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
			//float randomX = random(0f, w)
			objetoActors[ind].setPosition(MathUtils.random(0f, w), MathUtils.random(0f, h));
			objetoMenuActor[ind].setPosition(0,espaciado+=50);
		}		
		// Finalmente construimos el escenario y añadimos los actores.
		
		stage = new Stage();
		
		stage.addActor(fondoActor);
		for(int ind=0;ind<N;++ind) {
			stage.addActor(objetoActors[ind]);
			stage.addActor(objetoMenuActor[ind]);
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