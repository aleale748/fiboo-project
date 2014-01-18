package es.uca.fiboo.pianocreen.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

public class PianoScreen extends AbstractScreen {

	//Piano
	Sound doSound, reSound, miSound, faSound, solSound, laSound, siSound, zdoSound;
	//Trompeta
	Sound dotSound, retSound, mitSound, fatSound, soltSound, latSound, sitSound, zdotSound;
	
	private ImageButton logopiano, logotrompeta, play, stop, pause, silencio, sonido;
	int seleccion;
	float anchopiano, proporcion, alturapiano, alturanotas;
	private Image imagenPiano;
	private Texture fondo;
	private Texture basecontroles;
	private Numeros numeros;
	
	float imgWidth, imgHeight, numeroTam;
	int notas[], tempo[], silen[];
	float time;
	float timesil;
	int nota;
	boolean mute, isplay, isstop, ismute, ispause;
	float h, w;
	
	public PianoScreen(final fibooGame game) {
		super(game);
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					//fibooGame.MANAGER.get("sonidos/fondo.ogg", Sound.class).loop();
					dispose();
					game.setScreen(new MenuMiniJuegosScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		//fibooGame.MANAGER.get("sonidos/fondo.ogg", Sound.class).stop();
		Gdx.app.log(fibooGame.LOG, "Constructor piano empieza;");
		seleccion = 0;
		h = Gdx.graphics.getHeight();
		w = Gdx.graphics.getWidth();
		Gdx.app.log(fibooGame.LOG, "Constructor piano ok;");
		
		alturapiano= h - h*0.68359375f;
		alturanotas= h * 0.7f;
		numeros = new Numeros();
		imgWidth = (w * 0.2f)/2f;
		imgHeight = imgWidth;
		numeroTam = imgWidth*1.8f;
		
		notas = new int[]{1,1,2,1,4,3,1,1,2,1,5,4,1,1,8,6,4,4,3,2,8,8,6,4,5,4};
		tempo = new int[]{3,3,4,4,4,6,3,3,4,4,4,6,3,3,4,4,3,3,4,6,2,2,4,4,4,4};
		silen = new int[]{10,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1};
		mute = true;
		isplay = false;
		isstop = true;
		ismute = false;
		ispause = false;
		nota = 0;
	}
	
	@Override
	public void show() {
		super.show();
		Gdx.app.log(fibooGame.LOG, "super.show() ok;");
		fondo = fibooGame.MANAGER.get("robotgame/fondoestrellas.png", Texture.class);
		basecontroles = fibooGame.MANAGER.get("pianogame/base.png", Texture.class);
		fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		basecontroles.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		anchopiano = w;

		Gdx.app.log(fibooGame.LOG, "multiplexer ok;");
		imgWidth = (Gdx.graphics.getWidth() * 0.2f)/2f;
		imgHeight = imgWidth;
		TextureRegion pantallaBotonRegion = new TextureRegion(fibooGame.MANAGER.get("pianogame/piano.png", Texture.class));
		Drawable pantallaBotonDrawable = new TextureRegionDrawable(pantallaBotonRegion);
		doSound = fibooGame.MANAGER.get("pianogame/do.ogg", Sound.class);
		reSound = fibooGame.MANAGER.get("pianogame/re.ogg", Sound.class);
		miSound = fibooGame.MANAGER.get("pianogame/mi.ogg", Sound.class);
		faSound = fibooGame.MANAGER.get("pianogame/fa.ogg", Sound.class);
		solSound = fibooGame.MANAGER.get("pianogame/sol.ogg", Sound.class);
		laSound = fibooGame.MANAGER.get("pianogame/la.ogg", Sound.class);
		siSound = fibooGame.MANAGER.get("pianogame/si.ogg", Sound.class);
		zdoSound = fibooGame.MANAGER.get("pianogame/zdo.ogg", Sound.class);
		dotSound = fibooGame.MANAGER.get("pianogame/dotrom.ogg", Sound.class);
		retSound = fibooGame.MANAGER.get("pianogame/retrom.ogg", Sound.class);
		mitSound = fibooGame.MANAGER.get("pianogame/mitrom.ogg", Sound.class);
		fatSound = fibooGame.MANAGER.get("pianogame/fatrom.ogg", Sound.class);
		soltSound = fibooGame.MANAGER.get("pianogame/soltrom.ogg", Sound.class);
		latSound = fibooGame.MANAGER.get("pianogame/latrom.ogg", Sound.class);
		sitSound = fibooGame.MANAGER.get("pianogame/sitrom.ogg", Sound.class);
		zdotSound = fibooGame.MANAGER.get("pianogame/zdotrom.ogg", Sound.class);
		
		// Creamos botones, los posicionamos y los añadimos al stage
		imagenPiano = new Image(pantallaBotonDrawable);
		imagenPiano.setFillParent(true);
		imagenPiano.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + imagenPiano.getClass().getSimpleName()+" Y: "+Gdx.input.getY()+"tres cuartos:"+alturapiano);
				
				float click = Gdx.input.getX();
				if(Gdx.input.getY() > alturapiano) {
					if(seleccion == 0) {
						if(click < anchopiano/8)
							doSound.play();
						else if(click < (anchopiano/8)*2)
							reSound.play();
						else if(click < (anchopiano/8)*3)
							miSound.play();
						else if(click < (anchopiano/8)*4)
							faSound.play();
						else if(click < (anchopiano/8)*5)
							solSound.play();
						else if(click < (anchopiano/8)*6)
							laSound.play();
						else if(click < (anchopiano/8)*7)
							siSound.play();
						else
							zdoSound.play();
						}
					else {
						if(click < anchopiano/8)
							dotSound.play();
						else if(click < (anchopiano/8)*2)
							retSound.play();
						else if(click < (anchopiano/8)*3)
							mitSound.play();
						else if(click < (anchopiano/8)*4)
							fatSound.play();
						else if(click < (anchopiano/8)*5)
							soltSound.play();
						else if(click < (anchopiano/8)*6)
							latSound.play();
						else if(click < (anchopiano/8)*7)
							sitSound.play();
						else
							zdotSound.play();
					}
				}
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + imagenPiano.getClass().getSimpleName());
				}
		});
		stage.addActor(imagenPiano);
		TextureRegion logopianoBotonRegion = new TextureRegion(fibooGame.MANAGER.get("pianogame/logopiano.png", Texture.class));
		Drawable logopianoBotonDrawable = new TextureRegionDrawable(logopianoBotonRegion);
		TextureRegion logotrompetaBotonRegion = new TextureRegion(fibooGame.MANAGER.get("pianogame/logotrompeta.png", Texture.class));
		Drawable logotrompetaBotonDrawable = new TextureRegionDrawable(logotrompetaBotonRegion);
		logopiano = new ImageButton(logopianoBotonDrawable);
		logopiano.setSize(imgWidth, imgHeight);
		logopiano.setPosition(Gdx.graphics.getWidth()*0.03f, 
				Gdx.graphics.getHeight()*0.9f- imgWidth);
		logopiano.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + logopiano.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + logopiano.getClass().getSimpleName());
						seleccion = 0;
				}
		});
		stage.addActor(logopiano);
		logotrompeta = new ImageButton(logotrompetaBotonDrawable);
		logotrompeta.setSize(imgWidth, imgHeight);
		logotrompeta.setPosition(Gdx.graphics.getWidth()*0.03f +imgWidth*1.03f, 
				Gdx.graphics.getHeight()*0.9f- imgWidth);
		logotrompeta.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + logotrompeta.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + logotrompeta.getClass().getSimpleName());
						seleccion= 1;
				}
		});
		stage.addActor(logotrompeta);
		TextureRegion playBotonRegion = new TextureRegion(fibooGame.MANAGER.get("pianogame/play.png", Texture.class));
		Drawable playBotonDrawable = new TextureRegionDrawable(playBotonRegion);
		TextureRegion stopBotonRegion = new TextureRegion(fibooGame.MANAGER.get("pianogame/stop.png", Texture.class));
		Drawable stopBotonDrawable = new TextureRegionDrawable(stopBotonRegion);
		TextureRegion pauseBotonRegion = new TextureRegion(fibooGame.MANAGER.get("pianogame/pause.png", Texture.class));
		Drawable pauseBotonDrawable = new TextureRegionDrawable(pauseBotonRegion);
		TextureRegion sonidoBotonRegion = new TextureRegion(fibooGame.MANAGER.get("pianogame/sonido.png", Texture.class));
		Drawable sonidoBotonDrawable = new TextureRegionDrawable(sonidoBotonRegion);
		TextureRegion silencioBotonRegion = new TextureRegion(fibooGame.MANAGER.get("pianogame/mute.png", Texture.class));
		Drawable silencioBotonDrawable = new TextureRegionDrawable(silencioBotonRegion);
		
		play = new ImageButton(playBotonDrawable);
		play.setSize(w*0.1f, w*0.1f);
		play.setPosition(w*0.28f, h - imgWidth*1.1f);
		play.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on play" + play.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + play.getClass().getSimpleName());
						isplay= true;
						ispause= false;
				}
		});
		stage.addActor(play);
		
		stop = new ImageButton(stopBotonDrawable);
		stop.setSize(w*0.1f, w*0.1f);
		stop.setPosition(w*0.39f, h - imgWidth*1.1f);
		stop.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on stop" + stop.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + stop.getClass().getSimpleName());
				isplay = false;
				ispause = false;
				nota = 0;
				time = 0;
				timesil = 0;
				mute = true;
				}
		});
		stage.addActor(stop);
		
		pause = new ImageButton(pauseBotonDrawable);
		pause.setSize(w*0.1f, w*0.1f);
		pause.setPosition(w*0.50f, h - imgWidth*1.1f);
		pause.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on pause" + pause.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + pause.getClass().getSimpleName());
				isplay = false;
				ispause = true;
				}
		});
		stage.addActor(pause);
		
		sonido = new ImageButton(sonidoBotonDrawable);
		sonido.setSize(w*0.1f, w*0.1f);
		sonido.setPosition(w*0.61f, h - imgWidth*1.1f);
		sonido.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on pause" + sonido.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + sonido.getClass().getSimpleName());
				ismute = false;
			}
		});
		stage.addActor(sonido);
		
		silencio = new ImageButton(silencioBotonDrawable);
		silencio.setSize(w*0.1f, w*0.1f);
		silencio.setPosition(w*0.73f, h - imgWidth*1.1f);
		silencio.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on silencio" + silencio.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + silencio.getClass().getSimpleName());
				ismute = true;
			}
		});
		stage.addActor(silencio);
		
	}
	@Override
	public void render(float delta) {
		
		batch.begin();
		batch.draw(fondo, 0, 0, w,h);
		batch.draw(basecontroles, w*0.25f, h*0.8f, w*0.6f, h*0.2f);
		if(isplay) {
			if(!mute) {
				batch.draw(numeros.verdes(notas[nota]), anchopiano*0.80f, alturanotas, numeroTam,numeroTam);
				time += 0.1;
				if(time > tempo[nota]){
					time = 0;
					mute = true;
					nota =(nota+1)%26;
				}
			}
			else {
				timesil += 0.1;
				if(timesil > silen[nota]){
					mute = false;
					timesil = 0;
					if(!ismute){
						if(seleccion == 0){
							switch(notas[nota]){
							case 1: doSound.play();break;
							case 2: reSound.play();break;
							case 3:	miSound.play();break;
							case 4:	faSound.play();break;
							case 5:	solSound.play();break;
							case 6:	laSound.play();break;
							case 7:	siSound.play();break;
							case 8:	zdoSound.play();break;
							}
						}
						else{
							switch(notas[nota]){
							case 1: dotSound.play();break;
							case 2: retSound.play();break;
							case 3:	mitSound.play();break;
							case 4:	fatSound.play();break;
							case 5:	soltSound.play();break;
							case 6:	latSound.play();break;
							case 7:	sitSound.play();break;
							case 8:	zdotSound.play();break;
							}
						}
					}
				}
			}
		}
		else if(ispause) batch.draw(numeros.verdes(notas[nota]), anchopiano*0.80f, alturanotas, numeroTam,numeroTam);

		batch.end();
		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose() {
		fibooGame.MANAGER.unloadPianoMiniGameSounds();
		fibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
		fibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
		super.dispose();
	}

	private class Numeros {
		private static final int FRAME_COLS = 11;
		Texture numverdes;
		TextureRegion[] numerosverdes;
		
		public Numeros(){
			numverdes = fibooGame.MANAGER.get("robotgame/numerosverdes.png", Texture.class);   
			TextureRegion[][] tmp = TextureRegion.split(numverdes, numverdes.getWidth() / FRAME_COLS, numverdes.getHeight()); 
			numerosverdes = new TextureRegion[FRAME_COLS];
			int index = 0;
			for (int i = 0; i < FRAME_COLS; i++) {
				numerosverdes[index++] = tmp[0][i];
			}
		}
		
		public TextureRegion verdes(int n) {
			return numerosverdes[n];
		}
	}
}
