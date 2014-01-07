package es.uca.fiboo.robotgame.screens;


import java.util.Iterator;

import com.badlogic.gdx.Gdx;
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
	int planetsGathered;
	int moonsGathered;
	int starsGathered;
	int ufosGathered;
	SpriteBatch batch;
	BitmapFont font;
	BitmapFont font2;
	float timer;
	float aleatorio1, aleatorio2;
	int rand;
	int ultima_posicion;
	private ImageButton atrasBoton;
	public RobotGameScreen(fibooGame game){
		super(game);
		Gdx.input.setInputProcessor(stage);
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
				camera.setToOrtho(false, 800, 480);
		 
				// create a Rectangle to logically represent the bucket
				hucha = new Rectangle();
				hucha.x = 800 / 2 - 64 / 2; // center the bucket horizontally
				hucha.y = 20; // bottom left corner of the bucket is 20 pixels above
								// the bottom screen edge
				hucha.width = 64;
				hucha.height = 64;
		 
				// create the raindrops array and spawn the first raindrop
				raindrops = new Array<DropObject>();
				batch = new SpriteBatch();
				// Use LibGDX's default Arial font.
				font = new BitmapFont();
				font2 = new BitmapFont();
				spawnRaindrop();
	}
	private void spawnRaindrop() {
		int rand= MathUtils.random(0, 3);
		DropObject raindrop = new DropObject(rand);
		raindrop.x = MathUtils.random(0, Gdx.graphics.getWidth() - 128);
		raindrop.y = Gdx.graphics.getHeight();
		raindrop.width = 128;
		raindrop.height = 128;
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
		
		Gdx.input.setInputProcessor(stage);
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
		TextureRegion atrasBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("portada/atrasbotonpeque.png")));
		Drawable atrasBotonDrawable = new TextureRegionDrawable(atrasBotonRegion);
		atrasBoton = new ImageButton(atrasBotonDrawable);
		atrasBoton.setPosition(Gdx.graphics.getWidth()/(4f/0.17f) - atrasBoton.getWidth()/2, 
				Gdx.graphics.getHeight()/(4f/0.2f) - atrasBoton.getHeight()/2);
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
	}
	
	
	
	
	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		batch.begin();
		font.draw(batch, "Planets Collected: " + planetsGathered, 0,Gdx.graphics.getHeight()-10);
		font2.draw(batch, "Stars Collected: " + starsGathered, 200,Gdx.graphics.getHeight()-10);
		font.draw(batch, "Moons Collected: " + moonsGathered,400,Gdx.graphics.getHeight()-10);
		font2.draw(batch, "UFO's Collected: " + ufosGathered, 600,Gdx.graphics.getHeight()-10);
		rand = MathUtils.random(0, 1);
		for (DropObject raindrop : raindrops) {
			switch(raindrop.getTipo()){
			case PLANETA:
				batch.draw(planetaImage, raindrop.x, raindrop.y);break;
			case ESTRELLA:
				batch.draw(estrellaImage, raindrop.x, raindrop.y);break;	
			case LUNA:
				batch.draw(lunaImage, raindrop.x, raindrop.y);break;
			case MARCIANO:
				batch.draw(marcianoImage, raindrop.x, raindrop.y);break;
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
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + 64 < 0)
				iter.remove();
			if (raindrop.overlaps(robot.robotRect)) {
				switch(raindrop.getTipo()){
				case PLANETA: planetsGathered++;
					dropSound.play();break;
				case ESTRELLA: starsGathered++;
					dropSound.play();break;
				case LUNA: moonsGathered++;
					dropSound.play();break;
				case MARCIANO: ufosGathered++;
					planetsGathered=0;
					moonsGathered=0;
					starsGathered=0;
					failSound.play();
					break;
				default:
					break;
				}
				dropSound.play();
				iter.remove();
			}
		}
	}	

	@Override
	public void hide() {
	}
 
	@Override
	public void pause() {
	}
 
	@Override
	public void resume() {
	}
 
	@Override
	public void dispose() {
		lunaImage.dispose();
		estrellaImage.dispose();
		planetaImage.dispose();
		marcianoImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
	}
 
}
