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
	Sound bienSound;
	Sound malSound;
	Sound reguSound;
	//Music rainMusic;
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
				planetaImage = fibooGame.MANAGER.get("robotgame/planeta_.png", Texture.class);
				lunaImage = fibooGame.MANAGER.get("robotgame/luna_.png", Texture.class);
				estrellaImage = fibooGame.MANAGER.get("robotgame/estrella_.png",Texture.class);
				marcianoImage = fibooGame.MANAGER.get("robotgame/marciano_.png", Texture.class);
				//stage.addActor(huchaImage);
				
				// load the drop sound effect and the rain background "music"
				bienSound = fibooGame.MANAGER.get("robotgame/bien.mp3", Sound.class);
				malSound = fibooGame.MANAGER.get("robotgame/mal.mp3", Sound.class);
				reguSound = fibooGame.MANAGER.get("robotgame/regu.mp3", Sound.class);
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
	}
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
		Image imgFondo = new Image(fibooGame.MANAGER.get("naveminigame/fondonave.png", Texture.class));
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
		batch.draw(numeros.rojos(numobjetos), Gdx.graphics.getWidth()*0.03f,Gdx.graphics.getHeight()*0.95f-Gdx.graphics.getWidth()*0.08f/2, Gdx.graphics.getWidth()*0.09f, Gdx.graphics.getWidth()*0.09f);	
		if(objeto==0)
			batch.draw(planetaImage, Gdx.graphics.getWidth()*0.03f+Gdx.graphics.getWidth()*0.08f, Gdx.graphics.getHeight()*0.96f-Gdx.graphics.getWidth()*0.08f/2,  Gdx.graphics.getWidth()*0.07f, Gdx.graphics.getWidth()*0.07f);
		if(objeto==1)
			batch.draw(estrellaImage, Gdx.graphics.getWidth()*0.03f+Gdx.graphics.getWidth()*0.08f,Gdx.graphics.getHeight()*0.96f-Gdx.graphics.getWidth()*0.08f/2,  Gdx.graphics.getWidth()*0.07f, Gdx.graphics.getWidth()*0.07f);
		if(objeto==2)
			batch.draw(lunaImage, Gdx.graphics.getWidth()*0.03f+Gdx.graphics.getWidth()*0.08f,  Gdx.graphics.getHeight()*0.96f-Gdx.graphics.getWidth()*0.08f/2,  Gdx.graphics.getWidth()*0.07f, Gdx.graphics.getWidth()*0.07f);
		batch.draw(numeros.verdes(objetosGathered), Gdx.graphics.getWidth()*0.03f+Gdx.graphics.getWidth()*0.08f+Gdx.graphics.getWidth()*0.05f, Gdx.graphics.getHeight()*0.95f-Gdx.graphics.getWidth()*0.08f/2, Gdx.graphics.getWidth()*0.09f, Gdx.graphics.getWidth()*0.09f);
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
		lunaImage.dispose();
		estrellaImage.dispose();
		planetaImage.dispose();
		marcianoImage.dispose();
		bienSound.dispose();
		malSound.dispose();
		reguSound.dispose();
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
