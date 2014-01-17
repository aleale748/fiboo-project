package es.uca.fiboo.robotgame.screens;


import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
//import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;
import es.uca.fiboo.naveminigame.screens.WinScreen;
import es.uca.fiboo.robotgame.actor.DropObject;
import es.uca.fiboo.robotgame.actor.RobotActor;
 

public class RobotGameScreen extends AbstractScreen{
	RobotActor robot;
	Image imagen2;
	Image huchaImage;
	Texture planetaImage;
	Texture lunaImage;
	Texture estrellaImage;
	Texture marcianoImage;
	Sound bienSound;
	Sound malSound;
	Sound reguSound;
	//Sound musicaFondo;
	//Music rainMusic;
	OrthographicCamera camera;
	Rectangle hucha;
	Array<DropObject> raindrops;
	long lastDropTime;
	int objetosGathered;
	BitmapFont font;
	BitmapFont font2;
	float timer;
	float aleatorio1, aleatorio2;
	int rand;
	int objeto;
	int ultima_posicion;
	private int numobjetos;
	private Numeros numeros;
	
	public RobotGameScreen(fibooGame game){
		super(game);
		
		// load the images for the droplet and the bucket, 64x64 pixels each
		planetaImage = fibooGame.MANAGER.get("robotgame/planeta_.png", Texture.class);
		lunaImage = fibooGame.MANAGER.get("robotgame/luna_.png", Texture.class);
		estrellaImage = fibooGame.MANAGER.get("robotgame/estrella_.png",Texture.class);
		marcianoImage = fibooGame.MANAGER.get("robotgame/marciano_.png", Texture.class);
		//stage.addActor(huchaImage);
		
		// load the drop sound effect and the rain background "music"
		//musicaFondo = fibooGame.MANAGER.get("sonidos/robot.mp3", Sound.class);
		bienSound = fibooGame.MANAGER.get("robotgame/bien.ogg", Sound.class);
		malSound = fibooGame.MANAGER.get("robotgame/mal.ogg", Sound.class);
		reguSound = fibooGame.MANAGER.get("robotgame/regu.ogg", Sound.class);
		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		
		camera.setToOrtho(false, Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
 
		// create a Rectangle to logically represent the bucket
		
		numobjetos = 10;
		objeto = MathUtils.random(0, 2);
		// create the raindrops array and spawn the first raindrop
		raindrops = new Array<DropObject>();
		// Use LibGDX's default Arial font.
		font = new BitmapFont();
		font2 = new BitmapFont();
		numeros= new Numeros();
		//musicaFondo.loop();
		spawnRaindrop();
	}
	
	private void spawnRaindrop() {
		float rand= MathUtils.random();
		int elec= 0;
		if(rand<0.4f){
			elec= objeto;
		}
		else{    
			rand = MathUtils.random();
			if (rand > 0.5f && objeto !=0) 
				elec = MathUtils.ceil(MathUtils.random(0, objeto));
			else
				elec = MathUtils.random(objeto + 1, 3);
		}
		DropObject raindrop = new DropObject(elec);
		raindrop.x = MathUtils.random(0, Gdx.graphics.getWidth() - Gdx.graphics.getHeight()*0.1f*2f);
		raindrop.y = Gdx.graphics.getHeight();
		raindrop.width = Gdx.graphics.getHeight()*0.1f;
		raindrop.height = Gdx.graphics.getHeight()*0.1f;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}
	@Override
	public void show() {
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					//musicaFondo.stop();
					//fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).loop();
					game.setScreen(new MenuMiniJuegosScreen(game));
					dispose();
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		Image imgFondo = new Image(fibooGame.MANAGER.get("robotgame/fondoestrellas.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);

		robot = new RobotActor();
		robot.robotRect.x = robot.getX();
		robot.robotRect.y = robot.getY();
		stage.addActor(robot);
		
		robot.setPosition(10, 10);
		stage.setKeyboardFocus(robot);
		robot.addListener(new DragListener() {
			 public void touchDragged(InputEvent event, float x, float y, int pointer) {
				 float dx = x - robot.getWidth()*0.5f;
				// float dy = y - robot.getHeight()*0.5f; Para mover por toda la pantalla
				 robot.setPosition(robot.getX() + dx, robot.getY());
				 //robot.setPosition(robot.getX() + dx, robot.getY() + dy);
			 }
			 
			 public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				 super.touchUp(event, x, y, pointer, button);
			 }
		});
		timer = 2 + (float) Math.random();
		Gdx.app.log(fibooGame.LOG, "Show terminado");
	}
	
	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	
		batch.begin();
		batch.draw(numeros.rojos(numobjetos), Gdx.graphics.getWidth()*0.03f,Gdx.graphics.getHeight()*0.92f-Gdx.graphics.getWidth()*0.08f/2, Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getWidth()*0.1f);	
		if(objeto==0)
			batch.draw(planetaImage, Gdx.graphics.getWidth()*0.03f+Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getHeight()*0.93f-Gdx.graphics.getWidth()*0.08f/2,  Gdx.graphics.getWidth()*0.08f, Gdx.graphics.getWidth()*0.08f);
		if(objeto==1)
			batch.draw(estrellaImage, Gdx.graphics.getWidth()*0.03f+Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.93f-Gdx.graphics.getWidth()*0.08f/2,  Gdx.graphics.getWidth()*0.08f, Gdx.graphics.getWidth()*0.08f);
		if(objeto==2)
			batch.draw(lunaImage, Gdx.graphics.getWidth()*0.03f+Gdx.graphics.getWidth()*0.1f,  Gdx.graphics.getHeight()*0.93f-Gdx.graphics.getWidth()*0.08f/2,  Gdx.graphics.getWidth()*0.08f, Gdx.graphics.getWidth()*0.08f);
		batch.draw(numeros.verdes(objetosGathered), Gdx.graphics.getWidth()*0.03f+Gdx.graphics.getWidth()*0.1f+Gdx.graphics.getWidth()*0.08f, Gdx.graphics.getHeight()*0.92f-Gdx.graphics.getWidth()*0.08f/2, Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getWidth()*0.1f);
		rand = MathUtils.random(0, 1);
		for (DropObject raindrop : raindrops) {
			switch(raindrop.getTipo()){
			case PLANETA:
				batch.draw(planetaImage, raindrop.x, raindrop.y,  Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getWidth()*0.1f);break;
			case ESTRELLA:
				batch.draw(estrellaImage, raindrop.x, raindrop.y, Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getWidth()*0.1f);break;	
			case LUNA:
				batch.draw(lunaImage, raindrop.x, raindrop.y, Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getWidth()*0.1f);break;
			case MARCIANO:
				batch.draw(marcianoImage, raindrop.x, raindrop.y, Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getWidth()*0.1f);break;
			default:
				break;
			}
		}
		
		batch.end();
		
		timer -= delta;
		if (TimeUtils.nanoTime() - lastDropTime > 1500000000)
			spawnRaindrop();
 
		Iterator<DropObject> iter = raindrops.iterator();
		while (iter.hasNext()) {
			DropObject raindrop = iter.next();
			raindrop.y -= 100 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + (Gdx.graphics.getHeight()*0.1f)/2 < 0)
				iter.remove();
			if (raindrop.overlaps(robot.robotRect)) {
				switch(raindrop.getTipo()){
				case PLANETA: if(objeto == 0){objetosGathered++;numobjetos--;bienSound.play();break;}
				else {reguSound.play();break;}
				case ESTRELLA: if(objeto == 1){objetosGathered++;numobjetos--;bienSound.play();break;}
					else {reguSound.play();break;}
				case LUNA: if(objeto == 2){objetosGathered++;numobjetos--;bienSound.play();break;}
					else {reguSound.play();break;}
				case MARCIANO:
					if(numobjetos<10){objetosGathered--;numobjetos++;}
					malSound.play();
					break;
				default:
					break;
				}
				if(numobjetos==0){
					//musicaFondo.stop();
					//fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).loop();
					game.setScreen(new WinScreen(game));
				}
				try{
				iter.remove();
				}
				catch(NullPointerException e){
					Gdx.app.log(fibooGame.LOG, "iter null");
				}
			}
		}
	}	

	@Override
	public void dispose() {
		/*lunaImage.dispose();
		estrellaImage.dispose();
		planetaImage.dispose();
		marcianoImage.dispose();
		bienSound.dispose();
		malSound.dispose();
		reguSound.dispose();
		*/
		fibooGame.MANAGER.unloadRobotMiniGameTextures();
		super.dispose();
	}
 
	private class Numeros{
		private static final int FRAME_COLS = 11;
		Texture numrojos;
		Texture numverdes;
		TextureRegion[] numerosrojos;
		TextureRegion[] numerosverdes;

		public Numeros(){
			numrojos= fibooGame.MANAGER.get("robotgame/numerosrojos.png", Texture.class);  
			numverdes= fibooGame.MANAGER.get("robotgame/numerosverdes.png", Texture.class);  
			TextureRegion[][] tmp1 = TextureRegion.split(numrojos, numrojos.getWidth() / FRAME_COLS, numrojos.getHeight());                                // #10
			TextureRegion[][] tmp2 = TextureRegion.split(numverdes, numverdes.getWidth() / FRAME_COLS, numverdes.getHeight()); 
			numerosrojos = new TextureRegion[FRAME_COLS];
			numerosverdes = new TextureRegion[FRAME_COLS];
			int index = 0;
			for (int i = 0; i < FRAME_COLS; i++) {
				numerosrojos[index] = tmp1[0][i];
				numerosverdes[index++] = tmp2[0][i];
			}
		}
		
		public TextureRegion rojos(int n){
			return numerosrojos[n];
		}
		
		public TextureRegion verdes(int n){
			return numerosverdes[n];
		}
	 }
	
}
