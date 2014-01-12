package es.uca.fiboo.robotgame.screens;


import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;
import es.uca.fiboo.screens.MenuScreen;
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
	Sound dropSound;
	Sound failSound;
	Music rainMusic;
	OrthographicCamera camera;
	Rectangle hucha;
	Array<DropObject> raindrops;
	long lastDropTime;
	int objetosGathered;
	SpriteBatch batch;
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
				planetaImage = new Texture(Gdx.files.internal("robotgame/planeta_.png"));
				//huchaImage = new Image(new Texture(Gdx.files.internal("hucha.png")));
				lunaImage = new Texture(Gdx.files.internal("robotgame/luna_.png"));
				estrellaImage = new Texture(Gdx.files.internal("robotgame/estrella_.png"));
				marcianoImage = new Texture(Gdx.files.internal("robotgame/marciano_.png"));
				//stage.addActor(huchaImage);
				
				// load the drop sound effect and the rain background "music"
				dropSound = Gdx.audio.newSound(Gdx.files.internal("robotgame/smw_coin.wav"));
				failSound = Gdx.audio.newSound(Gdx.files.internal("robotgame/failSound.mp3"));
				rainMusic = Gdx.audio.newMusic(Gdx.files.internal("robotgame/rain.mp3"));
				rainMusic.setLooping(true);
		 
				// create the camera and the SpriteBatch
				camera = new OrthographicCamera();
				
				camera.setToOrtho(false, Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
		 
				// create a Rectangle to logically represent the bucket
				
				numobjetos= 10;
				objeto= MathUtils.random(0, 2);
				// create the raindrops array and spawn the first raindrop
				raindrops = new Array<DropObject>();
				batch = new SpriteBatch();
				// Use LibGDX's default Arial font.
				font = new BitmapFont();
				font2 = new BitmapFont();
				numeros= new Numeros();
				spawnRaindrop();
	}
	
	private void spawnRaindrop() {
		int rand= MathUtils.random(0, 3);
		DropObject raindrop = new DropObject(rand);
		raindrop.x = MathUtils.random(0, Gdx.graphics.getWidth() - Gdx.graphics.getHeight()*0.1f);
		raindrop.y = Gdx.graphics.getHeight();
		raindrop.width = Gdx.graphics.getHeight()*0.1f;
		raindrop.height = Gdx.graphics.getHeight()*0.1f;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}/*
	@Override
	public void render(float delta) {
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Acciones de los actores
		stage.act(delta);


		//Pintar el resto de actores
		stage.draw();
		
		
		
		/*
		// process user input
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			hucha.x = touchPos.x - 64 / 2;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			hucha.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			hucha.x += 200 * Gdx.graphics.getDeltaTime();
 
		// make sure the bucket stays within the screen bounds
		if (hucha.x < 0)
			hucha.x = 0;
		if (hucha.x > 800 - 64)
			hucha.x = 800 - 64;
 
		// check if we need to create a new raindrop
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
			spawnRaindrop();
 
		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we play back
		// a sound effect as well.
		Iterator<Rectangle> iter = raindrops.iterator();
		while (iter.hasNext()) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + 64 < 0)
				iter.remove();
			if (raindrop.overlaps(hucha)) {
				dropsGathered++;
				dropSound.play();
				iter.remove();
			}
		}
	}
 
	@Override
	public void resize(int width, int height) {
	}
*/
	@Override
	public void show() {
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					dispose();
					game.setScreen(new MenuMiniJuegosScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		Image imgFondo = new Image(new Texture("robotgame/fondonave.png"));
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
		pause();
		batch.begin();
		batch.draw(numeros.rojos(numobjetos), Gdx.graphics.getWidth()*0.03f,Gdx.graphics.getHeight()*0.95f-Gdx.graphics.getWidth()*0.08f/2, Gdx.graphics.getWidth()*0.08f, Gdx.graphics.getWidth()*0.08f);	
		if(objeto==0)
			batch.draw(planetaImage, Gdx.graphics.getWidth()*0.03f+Gdx.graphics.getWidth()*0.08f, Gdx.graphics.getHeight()*0.96f-Gdx.graphics.getWidth()*0.08f/2,  Gdx.graphics.getWidth()*0.06f, Gdx.graphics.getWidth()*0.06f);
		if(objeto==1)
			batch.draw(estrellaImage, Gdx.graphics.getWidth()*0.03f+Gdx.graphics.getWidth()*0.08f,Gdx.graphics.getHeight()*0.96f-Gdx.graphics.getWidth()*0.08f/2,  Gdx.graphics.getWidth()*0.06f, Gdx.graphics.getWidth()*0.06f);
		if(objeto==2)
			batch.draw(lunaImage, Gdx.graphics.getWidth()*0.03f+Gdx.graphics.getWidth()*0.08f,  Gdx.graphics.getHeight()*0.96f-Gdx.graphics.getWidth()*0.08f/2,  Gdx.graphics.getWidth()*0.06f, Gdx.graphics.getWidth()*0.06f);
		batch.draw(numeros.verdes(objetosGathered), Gdx.graphics.getWidth()*0.03f+Gdx.graphics.getWidth()*0.08f+Gdx.graphics.getWidth()*0.05f, Gdx.graphics.getHeight()*0.95f-Gdx.graphics.getWidth()*0.08f/2, Gdx.graphics.getWidth()*0.08f, Gdx.graphics.getWidth()*0.08f);
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
				case PLANETA: if(objeto == 0){objetosGathered++;numobjetos--;}
					dropSound.play();break;
				case ESTRELLA: if(objeto == 1){objetosGathered++;numobjetos--;}
					dropSound.play();break;
				case LUNA: if(objeto == 2){objetosGathered++;numobjetos--;}
					dropSound.play();break;
				case MARCIANO:
					objetosGathered=0;numobjetos= 10;
					failSound.play();
					break;
				default:
					break;
				}
				if(numobjetos==0){
					game.setScreen(new WinScreen(game));
				}
				dropSound.play();
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
		lunaImage.dispose();
		estrellaImage.dispose();
		planetaImage.dispose();
		marcianoImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		super.dispose();
	}
 
	private class Numeros{
		private static final int FRAME_COLS= 11;
		Texture numrojos;
		Texture numverdes;
		TextureRegion[] numerosrojos;
		TextureRegion[] numerosverdes;
		//SpriteBatch spriteBatch;
		
		public Numeros(){
			numrojos= new Texture(Gdx.files.internal("robotgame/numerosrojos.png"));  
			numverdes= new Texture(Gdx.files.internal("robotgame/numerosverdes.png"));  
			TextureRegion[][] tmp1 = TextureRegion.split(numrojos, numrojos.getWidth() / FRAME_COLS, numrojos.getHeight());                                // #10
			TextureRegion[][] tmp2 = TextureRegion.split(numverdes, numverdes.getWidth() / FRAME_COLS, numverdes.getHeight()); 
			numerosrojos = new TextureRegion[FRAME_COLS];
			numerosverdes = new TextureRegion[FRAME_COLS];
			int index = 0;
			for (int i = 0; i < FRAME_COLS; i++) {
				numerosrojos[index] = tmp1[0][i];
				numerosverdes[index++] = tmp2[0][i];
				}
			//spriteBatch = new SpriteBatch();                                // #12 
		}
		
		public TextureRegion rojos(int n){
			return numerosrojos[n];
		}
		
		public TextureRegion verdes(int n){
			return numerosverdes[n];
		}
	 }
	
}
