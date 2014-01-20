package es.uca.fiboo.robotgame.screens;


import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.robotgame.actors.DropObject;
import es.uca.fiboo.robotgame.actors.RobotActor;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;
import es.uca.fiboo.screens.WinScreen;
 

public class RobotGameScreen extends AbstractScreen {
	
	private transient RobotActor robot;

	private transient final Texture planetaImage;
	private transient final Texture lunaImage;
	private transient final Texture estrellaImage;
	private transient final Texture marcianoImage;
	private transient final Texture basecontroles;
	
	private transient final Sound bienSound;
	private transient final Sound malSound;
	private transient final Sound reguSound;
	private transient final Music musicaFondo;

	private transient final Array<DropObject> raindrops;
	private transient long lastDropTime;
	private transient int objetosGathered;
	
	private transient final int objeto;

	private transient int numobjetos;
	private transient final Numeros numeros;
	private transient final float height, width;
	
	public RobotGameScreen(final FibooGame game){
		super(game);
		
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		planetaImage = FibooGame.MANAGER.get("robotgame/planeta_.png", Texture.class);
		lunaImage = FibooGame.MANAGER.get("robotgame/luna_.png", Texture.class);
		estrellaImage = FibooGame.MANAGER.get("robotgame/estrella_.png",Texture.class);
		marcianoImage = FibooGame.MANAGER.get("robotgame/marciano_.png", Texture.class);
		
		basecontroles = FibooGame.MANAGER.get("portada/base.png", Texture.class);
		musicaFondo = FibooGame.MANAGER.get("sonidos/robot.ogg", Music.class);
		bienSound = FibooGame.MANAGER.get("robotgame/bien.ogg", Sound.class);
		malSound = FibooGame.MANAGER.get("robotgame/mal.ogg", Sound.class);
		reguSound = FibooGame.MANAGER.get("robotgame/regu.ogg", Sound.class);
		
		numobjetos = 10;
		objeto = MathUtils.random(0, 2);

		raindrops = new Array<DropObject>();
		numeros = new Numeros();
		musicaFondo.setLooping(true);
		musicaFondo.play();
		spawnRaindrop();
	}
	
	private void spawnRaindrop() {
		float rand = MathUtils.random();
		int elec = 0;
		if(rand < 0.4f) {
			elec = objeto;
		}
		else {    
			rand = MathUtils.random();
			if(rand > 0.5f && objeto != 0) {
				elec = MathUtils.ceil(MathUtils.random(0, objeto));
			}
			else {
				elec = MathUtils.random(objeto + 1, 3);
			}
		}
		DropObject raindrop = new DropObject(elec);
		raindrop.x = MathUtils.random(0, width - height*0.1f*2f);
		raindrop.y = height;
		raindrop.width = height*0.1f;
		raindrop.height = height*0.1f;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}
	
	@Override
	public void show() {
		final InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(final int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					musicaFondo.stop();
					game.setScreen(new MenuMiniJuegosScreen(game));
					dispose();
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		Image imgFondo = new Image(FibooGame.MANAGER.get("robotgame/fondoestrellas.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);

		robot = new RobotActor();
		robot.robotRect.x = robot.getX();
		robot.robotRect.y = robot.getY();
		stage.addActor(robot);
		
		robot.setPosition(10, 10);
		stage.setKeyboardFocus(robot);
		robot.addListener(new DragListener() {
			 public void touchDragged(final InputEvent event, final float x, final float y, final int pointer) {
				 float dx = x - robot.getWidth()*0.5f;
				 robot.setPosition(robot.getX() + dx, robot.getY());
			 }
			 
			 public void touchUp(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				 super.touchUp(event, x, y, pointer, button);
			 }
		});
		Gdx.app.log(FibooGame.LOG, "Show terminado");
	}
	
	@Override
	public void render(final float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	
		batch.begin();

		batch.draw(basecontroles, width*0.015f, height*0.8f, width*0.3f, height*0.2f);
		batch.draw(numeros.rojos(numobjetos), width*0.03f, height*0.92f-width*0.08f/2, width*0.1f, width*0.1f);	
		if(objeto == 0) {
			batch.draw(planetaImage, width*0.03f +width *0.1f, height*0.93f - width*0.08f/2, width*0.08f, width*0.08f);
		}
		if(objeto == 1) {
			batch.draw(estrellaImage, width*0.03f + width*0.1f, height*0.93f - width*0.08f/2, width*0.08f, width*0.08f);
		}
		if(objeto == 2) {
			batch.draw(lunaImage, width*0.03f + width*0.1f, height*0.93f - width*0.08f/2, width*0.08f, width*0.08f);
		}
		
		batch.draw(numeros.verdes(objetosGathered), width*0.03f + width*0.1f+width*0.08f, height*0.92f - width*0.08f/2, width*0.1f, width*0.1f);
		//rand = MathUtils.random(0, 1);
		
		for (final DropObject raindrop : raindrops) {
			switch(raindrop.getTipo()){
			case PLANETA:
				batch.draw(planetaImage, raindrop.x, raindrop.y,  width*0.1f, width*0.1f);break;
			case ESTRELLA:
				batch.draw(estrellaImage, raindrop.x, raindrop.y, width*0.1f, width*0.1f);break;	
			case LUNA:
				batch.draw(lunaImage, raindrop.x, raindrop.y, width*0.1f, width*0.1f);break;
			case MARCIANO:
				batch.draw(marcianoImage, raindrop.x, raindrop.y, width*0.1f, width*0.1f);break;
			default:
				break;
			}
		}
		
		batch.end();
		
		if (TimeUtils.nanoTime() - lastDropTime > 1500000000) {
			spawnRaindrop();
		}
 
		final Iterator<DropObject> iter = raindrops.iterator();
		while (iter.hasNext()) {
			DropObject raindrop = iter.next();
			raindrop.y -= 100 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + raindrop.getHeight() < 0) {
				iter.remove();
			}
			if (raindrop.overlaps(robot.robotRect)) {
				switch(raindrop.getTipo()) {
					case PLANETA: 
						if(objeto == 0) { objetosGathered++; numobjetos--; bienSound.play(); }
						else { reguSound.play(); }
						break; 
					case ESTRELLA: 
						if(objeto == 1) { objetosGathered++; numobjetos--; bienSound.play(); }
						else {reguSound.play(); }
						break;
					case LUNA: 
						if(objeto == 2) { objetosGathered++; numobjetos--; bienSound.play(); }
						else { reguSound.play(); }
						break; 
					case MARCIANO:
						if(numobjetos < 10) { objetosGathered--; numobjetos++; }
						malSound.play();
						break;
					default:
						break;
				}
				if(numobjetos == 0) {
					musicaFondo.stop();
					dispose();
					game.setScreen(new WinScreen(game));
				}
				try {
					iter.remove();
				}
				catch(NullPointerException e) {
					Gdx.app.log(FibooGame.LOG, "iter null");
				}
			}
		}
	}	

	@Override
	public void dispose() {
		FibooGame.MANAGER.unloadRobotMiniGameTextures();
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
		super.dispose();
	}
 
	private class Numeros {
		private static final int FRAME_COLS = 11;
		private transient final TextureRegion[] numerosrojos;
		private transient final TextureRegion[] numerosverdes;

		public Numeros(){
			Texture numrojos = FibooGame.MANAGER.get("robotgame/numerosrojos.png", Texture.class);
			numrojos.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			Texture numverdes = FibooGame.MANAGER.get("robotgame/numerosverdes.png", Texture.class); 
			numverdes.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
			TextureRegion[][] tmp1 = TextureRegion.split(numrojos, numrojos.getWidth() / FRAME_COLS, numrojos.getHeight());  // #10
			TextureRegion[][] tmp2 = TextureRegion.split(numverdes, numverdes.getWidth() / FRAME_COLS, numverdes.getHeight()); 
			numerosrojos = new TextureRegion[FRAME_COLS];
			numerosverdes = new TextureRegion[FRAME_COLS];
			int index = 0;
			for (int i = 0; i < FRAME_COLS; i++) {
				numerosrojos[index] = tmp1[0][i];
				numerosverdes[index++] = tmp2[0][i];
			}
		}
		
		public TextureRegion rojos(final int n){
			return numerosrojos[n];
		}
		
		public TextureRegion verdes(final int n){
			return numerosverdes[n];
		}
	 }
	
}
