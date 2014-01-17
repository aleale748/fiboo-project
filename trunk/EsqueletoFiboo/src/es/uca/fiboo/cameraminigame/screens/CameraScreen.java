package es.uca.fiboo.cameraminigame.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.cameraminigame.actors.*;
import es.uca.fiboo.naveminigame.screens.WinScreen;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

public class CameraScreen extends AbstractScreen {
	
	public CameraScreen(fibooGame game) {
		super(game);
	}
		
	private List<ObjetoActor> objetoActors;
	
	private List<ObjetoMenuActor> objetoMenuActors;
	
	private List<Rectangle> rectangles;
	
	private final static int N=10;
	int w,h;
	int numerosRestantes = N;

	private OrthographicCamera camera;

	public static Vector2 coordenadasMundo;

	private ImageButton atrasBoton;
	
	public void show() {	
		
		Gdx.input.setInputProcessor(stage);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		coordenadasMundo = new Vector2(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());//(4096,2048);
		
		camera = new OrthographicCamera(w,h);
		//stage.setViewport(1024, 512, false);
		stage.setCamera(camera);
		//camera.update();
		
		final Image imgFondo = new Image(fibooGame.MANAGER.get("cameraminigame/1.png", Texture.class));
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
					--numerosRestantes;
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
		
		TextureRegion atrasBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("portada/atrasbotonpeque.png")));
		Drawable atrasBotonDrawable = new TextureRegionDrawable(atrasBotonRegion);
		atrasBoton = new ImageButton(atrasBotonDrawable);
		atrasBoton.setPosition(0,0);
		atrasBoton.setSize(coordenadasMundo.x*0.1f, coordenadasMundo.y*0.1f);
		atrasBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + atrasBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + atrasBoton.getClass().getSimpleName());
						game.setScreen(new MenuMiniJuegosScreen(game));
				}
		}); 
		stage.addActor(atrasBoton);

		int ind = 0;
		int espaciado = 50;
		
		while (ind < N) {//for(int ind=0;ind<N;++ind) {
			float randomX = MathUtils.random(objetoMenuActors.get(ind).getWidth(), coordenadasMundo.x * 2 -objetoActors.get(ind).getWidth());//1280
			float randomY = MathUtils.random(objetoActors.get(ind).getHeight(), coordenadasMundo.y * 1.8f - objetoActors.get(ind).getHeight());//450
			Gdx.app.log(fibooGame.LOG, "xMAX=" + coordenadasMundo.x*2 + " yMAX=" + coordenadasMundo.y*2);
			float objetoActorWidth = objetoActors.get(ind).getWidth();
			float objetoActorHeight = objetoActors.get(ind).getHeight();
			
			rectangles.add(new Rectangle(randomX, randomY, objetoActorWidth, objetoActorHeight));
			if (noHayOverlaps(rectangles)) {
				Gdx.app.log(fibooGame.LOG, "dentro if ind:" + ind);
				objetoActors.get(ind).setPosition(randomX, randomY);
				objetoMenuActors.get(ind).setPosition(40,espaciado+=60);
				stage.addActor(objetoActors.get(ind));
				stage.addActor(objetoMenuActors.get(ind));
				
				ind++;
			}
			else {
				Gdx.app.log(fibooGame.LOG, "dentro else ind:" + ind);
				rectangles.remove(ind);
			}
		}	

		
		stage.addListener(new InputListener() {
			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				float coordenadaPulsadaXLocal = Gdx.input.getX() - coordenadaPulsadaX;
				float coordenadaPulsadaYLocal = Gdx.input.getY() - coordenadaPulsadaY;
				Gdx.app.log(fibooGame.LOG, "x=" + x);
				Gdx.app.log(fibooGame.LOG, "getX()=" + Gdx.input.getX());
				Gdx.app.log(fibooGame.LOG, "coordenadaPulsadaX=" + coordenadaPulsadaX);
				Gdx.app.log(fibooGame.LOG, "coordenadaPulsadaXLocal=" + coordenadaPulsadaXLocal);
				Gdx.app.log(fibooGame.LOG, "camera.position.x=" + camera.position.x);
				Gdx.app.log(fibooGame.LOG, "w/2=" + w/2);
				if ((camera.position.x + coordenadaPulsadaXLocal > w/2) && 
						(camera.position.x + coordenadaPulsadaXLocal + camera.viewportWidth <= (4096 - w/2)) && 
						(camera.position.y + coordenadaPulsadaYLocal > h/2) && 
						(camera.position.y + coordenadaPulsadaYLocal + camera.viewportHeight <= (2048 - h/2)))
				{
					for(int i=0;i<N;++i) {
						objetoMenuActors.get(i).translate(coordenadaPulsadaXLocal, coordenadaPulsadaYLocal);
					}
					atrasBoton.translate(coordenadaPulsadaXLocal, coordenadaPulsadaYLocal);
					camera.translate(coordenadaPulsadaXLocal, coordenadaPulsadaYLocal);
				}
				 
				//camera.translate(coordenadaPulsadaXLocal, coordenadaPulsadaYLocal);
				// TODO Auto-generated method stub
				super.touchDragged(event, x, y, pointer);
			}

			private int coordenadaPulsadaX;
			private int coordenadaPulsadaY;

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				coordenadaPulsadaX = Gdx.input.getX();
				coordenadaPulsadaY = Gdx.input.getY();
				Gdx.app.log(fibooGame.LOG, "Touching down on (" + coordenadaPulsadaX + "," + coordenadaPulsadaY + ")");
				return true;
			}
		});
		
		/*stage.addListener((new DragListener() {
			float mitadFondoWidth = imgFondo.getImageWidth()/2;
	    	float mitadFondoHeight = imgFondo.getImageHeight()/2;
	    	
		    public void touchDragged (InputEvent event, float x, float y, int pointer) {
	            		    	//camera.translate(Gdx.input.getX()*0.01f, Gdx.input.getY()*0.01f, 0);
		    	//imgFondo.setPosition(imgFondo.getX(), imgFondo.getY());
	            System.out.println("touchdragged" + x + ", " + y);

	        }

	    }));*/
		
		camera.position.set(imgFondo.getImageWidth()/2, imgFondo.getImageHeight()/2, 0);
	}
	
	public void render(float delta) {
		//Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		//Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		//Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    //Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
	    Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		
		
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
		coordenadasMundo.x = width;
		coordenadasMundo.y = height;
		//show();
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
			
			if (camera.position.x > w/2)
			{
				Gdx.app.log(fibooGame.LOG, "x=" + camera.position.x);
				Gdx.app.log(fibooGame.LOG, "y=" + camera.position.y);
				for(int i=0;i<N;++i) {
					objetoMenuActors.get(i).translate(-3,0);
				}
				atrasBoton.translate(-3, 0);
				camera.translate(-3, 0, 0);
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			Gdx.app.log(fibooGame.LOG, "pulsado right");
			if (camera.position.x + camera.viewportWidth <= (4096 - w/2))
			{
				for(int i=0;i<N;++i) {
					objetoMenuActors.get(i).translate(3,0);
				}
				atrasBoton.translate(3,0);
				camera.translate(3, 0, 0);
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			Gdx.app.log(fibooGame.LOG, "pulsado down con y:" + camera.position.y);
			if (camera.position.y > h/2) {
				for(int i=0;i<N;++i) {
					objetoMenuActors.get(i).translate(0,-3);
				}
				atrasBoton.translate(0, -3);
				camera.translate(0, -3, 0);
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			Gdx.app.log(fibooGame.LOG, "pulsado up con y:" + camera.position.y);
			if (camera.position.y + camera.viewportHeight <= (2048 - h/2)) {
				for(int i=0;i<N;++i) {
					objetoMenuActors.get(i).translate(0,3);
				}
				atrasBoton.translate(0, 3);
				camera.translate(0, 3);
			}
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