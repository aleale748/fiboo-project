package es.uca.fiboo.cameraminigame.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.cameraminigame.actors.*;
import es.uca.fiboo.naveminigame.screens.WinScreen;
import es.uca.fiboo.screens.AbstractScreen;

public class CameraScreen extends AbstractScreen {
	
	public CameraScreen(fibooGame game) {
		super(game);
	}
		
	private List<ObjetoActor> objetoActors;
	
	private List<ObjetoMenuActor> objetoMenuActors;
	
	private List<Rectangle> rectangles;
	
	private final static int N=10;
	int w,h;
	int numerosRestantes;

	private OrthographicCamera camera;

	private Vector2 coordenadasMundo;
	
	public void show() {	
		
		Gdx.input.setInputProcessor(stage);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		coordenadasMundo = new Vector2(4096,2048);
		
		camera = new OrthographicCamera(w,h);
		//stage.setViewport(1024, 512, false);
		stage.setCamera(camera);
		//camera.update();
		numerosRestantes = N;
		
		Image imgFondo = new Image(fibooGame.MANAGER.get("cameraminigame/cesped2.png", Texture.class));
		//imgFondo.setFillParent(true);
		stage.addActor(imgFondo);

		objetoActors = new ArrayList<ObjetoActor>();
		objetoMenuActors = new ArrayList<ObjetoMenuActor>();
		rectangles = new ArrayList<Rectangle>();
		
		// Construimos los elementos que vamos a usar en nuestro juego.
		for(int indice=0; indice<N; ++indice) {
			objetoActors.add(new ObjetoActor(indice));
			objetoMenuActors.add(new ObjetoMenuActor(indice));

			final int indiceAux = indice;
			objetoActors.get(indice).addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer,
						int button) {
					Gdx.app.log(fibooGame.LOG, "pulsado en " + indiceAux);
					objetoActors.get(indiceAux).remove();
					objetoMenuActors.get(indiceAux).remove();
					numerosRestantes--;
					if (numerosRestantes == 0) {
						game.setScreen(new WinScreen(game));
					}
					return true;
				}
			});
		}
		
		// Posicionamos los objetoActors en la pantalla. La pelota se sit??a en el
		// centro de toda la pantalla. Ambas paletas se centran verticalmente,
		// una se alinea a la izquierda y la otra se alinea a la derecha
		// separadas 30 p??xeles de los bordes de la pantalla.
		
		// Para centrar cosas en la pantalla simplemente hay que restar el
		// ancho o el alto de la pantalla (seg??n la coordenada en la que
		// estemos) al ancho o el alto del objeto que pretendamos centrar,
		// y dividir esa resta entre dos. Geom??tricamente puede demostrarse
		// esto, que tambi??n puede expresarse como restar la mitad del ancho
		// o del alto de la pantalla a la mitad del ancho o el alto del objeto.
		
		int espaciado = -50;
		int ind = 0;
		while (ind < N) {//for(int ind=0;ind<N;++ind) {
			float randomX = MathUtils.random(objetoMenuActors.get(ind).getWidth(), coordenadasMundo.x -objetoActors.get(ind).getWidth());
			float randomY = MathUtils.random(objetoActors.get(ind).getHeight(), coordenadasMundo.y - objetoActors.get(ind).getHeight());
			float objetoActorWidth = objetoActors.get(ind).getWidth();
			float objetoActorHeight = objetoActors.get(ind).getHeight();
			
			rectangles.add(new Rectangle(randomX, randomY, objetoActorWidth, objetoActorHeight));
			Gdx.app.log(fibooGame.LOG, "rectangle:" + ind + ", size:" + rectangles.size());
			if (noHayOverlaps(rectangles)) {
				Gdx.app.log(fibooGame.LOG, "dentro if ind:" + ind);
				objetoActors.get(ind).setPosition(randomX, randomY);
				objetoMenuActors.get(ind).setPosition(0,espaciado+=50);
				stage.addActor(objetoActors.get(ind));
				stage.addActor(objetoMenuActors.get(ind));
				
				ind++;
			}
			else {
				Gdx.app.log(fibooGame.LOG, "dentro else ind:" + ind);
				rectangles.remove(ind);
			}
		}	
		camera.position.set(imgFondo.getImageWidth()/2, imgFondo.getImageHeight()/2, 0);
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		camera.apply(Gdx.graphics.getGL10());

		//stage.setCamera(camera);
		//stage.setViewport(1024, 512, false);

		batch.setProjectionMatrix(camera.combined);
		//cameraHelper.applyTo(camera);
		
		//stage.setCamera(camera);
		
		this.handleInput();
		//cameraHelper.update();

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
	public void handleInput() {
		/*if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			if (camera.zoom < 1)
				camera.zoom += 0.02;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
			if (camera.zoom > 0.5f)
				camera.zoom -= 0.02;
		}*/
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			Gdx.app.log(fibooGame.LOG, "pulsado left");
			//if (camera.position.x > w/2) {
			if (camera.position.x > w/2)//+ camera.viewportWidth)
			{
				Gdx.app.log(fibooGame.LOG, "x=" + camera.position.x);
				Gdx.app.log(fibooGame.LOG, "y=" + camera.position.y);
				for(int i=0;i<N;++i) {
					objetoMenuActors.get(i).translate(-3,0);
				}
				camera.translate(-3, 0, 0);
			}
			/*x -= 5;
			camera.translate(x, y);
			cameraHelper.applyTo(camera);*/
			//}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			Gdx.app.log(fibooGame.LOG, "pulsado right");
			//if (camera.position.x < w/2) {
			if (camera.position.x + camera.viewportWidth <= (4096 - w/2))//+ camera.viewportWidth)
			{
				for(int i=0;i<N;++i) {
					objetoMenuActors.get(i).translate(3,0);
				}
				camera.translate(3, 0, 0);
			}
			/*x += 5;
			camera.translate(x, y);
			cameraHelper.applyTo(camera);*/
			//}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			Gdx.app.log(fibooGame.LOG, "pulsado down con y:" + camera.position.y);
			if (camera.position.y > h/2) {
				camera.translate(0, -3, 0);
				for(int i=0;i<N;++i) {
					objetoMenuActors.get(i).translate(0,-3);
				}
			/*cameraHelper.setPosition(x, y);
			cameraHelper.applyTo(camera);*/
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			Gdx.app.log(fibooGame.LOG, "pulsado up con y:" + camera.position.y);
			if (camera.position.y + camera.viewportHeight <= (2048 - h/2)) {
				for(int i=0;i<N;++i) {
					objetoMenuActors.get(i).translate(0,3);
				}
				camera.translate(0, 3, 0);
			}
			/*cameraHelper.setPosition(x, y);
			cameraHelper.applyTo(camera);*/
			//}
		}
		/*float minCameraX = camera.zoom * (camera.viewportWidth / 2);
		float maxCameraX = coordenadasMundo.x - minCameraX;
		float minCameraY = camera.zoom * (camera.viewportHeight / 2);
		float maxCameraY = coordenadasMundo.y - minCameraY;
*/
		/*camera.position.set(Math.min(maxCameraX, Math.max(camera.position.x, minCameraX)),
		        Math.min(maxCameraY, Math.max(camera.position.y, minCameraY)),
		        0);*/
	}
	
	private boolean noHayOverlaps (List<Rectangle> rectangles) {
		int indice = 0;
		Gdx.app.log(fibooGame.LOG, "dentro fun ind:" + indice + "size:" + rectangles.size());
		while (indice < rectangles.size()-1) {
			Gdx.app.log(fibooGame.LOG, "dentro fun bucle ind:" + indice + "size:" + rectangles.size());
			if (rectangles.get(indice).overlaps(rectangles.get(indice+1))) {
				return false;
			}
			indice++;
		}
		return true;
	}
}