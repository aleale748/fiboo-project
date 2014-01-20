package es.uca.fiboo.piano.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import es.uca.fiboo.FibooGame;
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
	//private Texture fondo;
	private Texture partitura;
	TextureRegion logopianoBotonRegion;
	TextureRegion logotrompetaBotonRegion;
	
	private Teclas tecla;
	float avance;
	float imgWidth, imgHeight, numeroTam;
	int notas[], tempo[], silen[];
	float time;
	float timesil;
	float timetocada;
	int nota;
	boolean mute, isplay, isstop, ismute, ispause;
	float h, w;
	int tocada;
	float posPartitura;
	float posactual;
	public PianoScreen(final FibooGame game) {
		super(game);
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					//FibooGame.MANAGER.get("sonidos/fondo.ogg", Sound.class).loop();
					dispose();
					game.setScreen(new MenuMiniJuegosScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		//FibooGame.MANAGER.get("sonidos/fondo.ogg", Sound.class).stop();
		Gdx.app.log(FibooGame.LOG, "Constructor piano empieza;");
		seleccion = 0;
		h = Gdx.graphics.getHeight();
		w = Gdx.graphics.getWidth();
		Gdx.app.log(FibooGame.LOG, "Constructor piano ok;");
		
		alturapiano= h - h*0.68359375f;
		alturanotas= h * 0.7f;
		tecla = new Teclas();
		imgWidth = w * 0.18f;
		imgHeight = imgWidth;
		numeroTam = imgWidth*10f;
		tocada= -1;
		notas = new int[]{1,1,2,1,4,3,1,1,2,1,5,4,1,1,8,6,4,4,3,2,8,8,6,4,5,4};
		tempo = new int[]{3,3,4,4,4,6,3,3,4,4,4,6,3,3,4,4,3,3,4,6,2,2,4,4,4,4};
		silen = new int[]{10,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1};
		mute = true;
		isplay = false;
		isstop = true;
		ismute = false;
		ispause = false;
		nota = 0;
		posPartitura= 0.55f;
		avance= 55.4f;
		posactual= (w*posPartitura)-avance;
	}
	
	@Override
	public void show() {
		super.show();
		Gdx.app.log(FibooGame.LOG, "super.show() ok;");
		//fondo = FibooGame.MANAGER.get("robotgame/fondoestrellas.png", Texture.class);
		partitura = FibooGame.MANAGER.get("pianogame/partitura.png", Texture.class);
		//fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		anchopiano = w;

		Gdx.app.log(FibooGame.LOG, "multiplexer ok;");
		imgWidth = (Gdx.graphics.getWidth() * 0.2f)/2f;
		imgHeight = imgWidth;
		TextureRegion pantallaBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/piano.png", Texture.class));
		Drawable pantallaBotonDrawable = new TextureRegionDrawable(pantallaBotonRegion);
		doSound = FibooGame.MANAGER.get("pianogame/do.ogg", Sound.class);
		reSound = FibooGame.MANAGER.get("pianogame/re.ogg", Sound.class);
		miSound = FibooGame.MANAGER.get("pianogame/mi.ogg", Sound.class);
		faSound = FibooGame.MANAGER.get("pianogame/fa.ogg", Sound.class);
		solSound = FibooGame.MANAGER.get("pianogame/sol.ogg", Sound.class);
		laSound = FibooGame.MANAGER.get("pianogame/la.ogg", Sound.class);
		siSound = FibooGame.MANAGER.get("pianogame/si.ogg", Sound.class);
		zdoSound = FibooGame.MANAGER.get("pianogame/zdo.ogg", Sound.class);
		dotSound = FibooGame.MANAGER.get("pianogame/dotrom.ogg", Sound.class);
		retSound = FibooGame.MANAGER.get("pianogame/retrom.ogg", Sound.class);
		mitSound = FibooGame.MANAGER.get("pianogame/mitrom.ogg", Sound.class);
		fatSound = FibooGame.MANAGER.get("pianogame/fatrom.ogg", Sound.class);
		soltSound = FibooGame.MANAGER.get("pianogame/soltrom.ogg", Sound.class);
		latSound = FibooGame.MANAGER.get("pianogame/latrom.ogg", Sound.class);
		sitSound = FibooGame.MANAGER.get("pianogame/sitrom.ogg", Sound.class);
		zdotSound = FibooGame.MANAGER.get("pianogame/zdotrom.ogg", Sound.class);
		
		// Creamos botones, los posicionamos y los a�adimos al stage
		imagenPiano = new Image(pantallaBotonDrawable);
		imagenPiano.setFillParent(true);
		imagenPiano.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching down on " + imagenPiano.getClass().getSimpleName()+" Y: "+Gdx.input.getY()+"tres cuartos:"+alturapiano);
				
				float click = Gdx.input.getX();
				if(Gdx.input.getY() > alturapiano) {
					if(seleccion == 0) {
						if(click < anchopiano/8){
							doSound.play();
							tocada= 0;
						}
						else if(click < (anchopiano/8)*2){
							reSound.play();
							tocada= 1;
						}
						else if(click < (anchopiano/8)*3){
							miSound.play();
							tocada= 2;
						}
						else if(click < (anchopiano/8)*4){
							faSound.play();
							tocada= 3;
						}
						else if(click < (anchopiano/8)*5){
							solSound.play();
							tocada= 4;
						}
						else if(click < (anchopiano/8)*6){
							laSound.play();
							tocada= 5;
						}
						else if(click < (anchopiano/8)*7){
							siSound.play();
							tocada= 6;
						}
						else{
							zdoSound.play();
							tocada= 7;
						}
						}
					else {
						if(click < anchopiano/8){
							dotSound.play();
							tocada= 0;
						}
						else if(click < (anchopiano/8)*2){
							retSound.play();
							tocada= 1;
						}
						else if(click < (anchopiano/8)*3){
							mitSound.play();
							tocada= 2;
						}
						else if(click < (anchopiano/8)*4){
							fatSound.play();
							tocada= 3;
						}
						else if(click < (anchopiano/8)*5){
							soltSound.play();
							tocada= 4;
						}
						else if(click < (anchopiano/8)*6){
							latSound.play();
							tocada= 5;
						}
						else if(click < (anchopiano/8)*7){
							sitSound.play();
							tocada= 6;
						}
						else{
							zdotSound.play();
							tocada= 7;
						}
					}
				}
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching up on " + imagenPiano.getClass().getSimpleName());
				tocada=-1;
				}
		});
		stage.addActor(imagenPiano);
		logopianoBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/logopiano.png", Texture.class));
		Drawable logopianoBotonDrawable = new TextureRegionDrawable(logopianoBotonRegion);
		logotrompetaBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/logotrompeta.png", Texture.class));
		Drawable logotrompetaBotonDrawable = new TextureRegionDrawable(logotrompetaBotonRegion);
		logopiano = new ImageButton(logopianoBotonDrawable);
		logopiano.setSize(imgWidth, imgHeight);
		logopiano.setPosition(Gdx.graphics.getWidth()*0.02f, 
				Gdx.graphics.getHeight()*0.98f- imgWidth);
		logopiano.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching down on " + logopiano.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching up on " + logopiano.getClass().getSimpleName());
						seleccion = 0;
				}
		});
		stage.addActor(logopiano);
		logotrompeta = new ImageButton(logotrompetaBotonDrawable);
		logotrompeta.setSize(imgWidth, imgHeight);
		logotrompeta.setPosition(Gdx.graphics.getWidth()*0.02f, 
				Gdx.graphics.getHeight()*0.83f- imgWidth);
		logotrompeta.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching down on " + logotrompeta.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching up on " + logotrompeta.getClass().getSimpleName());
						seleccion= 1;
				}
		});
		stage.addActor(logotrompeta);
		TextureRegion playBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/play.png", Texture.class));
		Drawable playBotonDrawable = new TextureRegionDrawable(playBotonRegion);
		TextureRegion stopBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/stop.png", Texture.class));
		Drawable stopBotonDrawable = new TextureRegionDrawable(stopBotonRegion);
		TextureRegion pauseBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/pause.png", Texture.class));
		Drawable pauseBotonDrawable = new TextureRegionDrawable(pauseBotonRegion);
		TextureRegion sonidoBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/sonido.png", Texture.class));
		Drawable sonidoBotonDrawable = new TextureRegionDrawable(sonidoBotonRegion);
		TextureRegion silencioBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/mute.png", Texture.class));
		Drawable silencioBotonDrawable = new TextureRegionDrawable(silencioBotonRegion);
		
		play = new ImageButton(playBotonDrawable);
		play.setSize(w*0.08f, w*0.08f);
		play.setPosition(w*0.24f, h*0.7f);
		play.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching down on play" + play.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching up on " + play.getClass().getSimpleName());
						isplay= true;
						ispause= false;
				}
		});
		stage.addActor(play);
		
		stop = new ImageButton(stopBotonDrawable);
		stop.setSize(w*0.08f, w*0.08f);
		stop.setPosition(w*0.34f,  h*0.7f);
		stop.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching down on stop" + stop.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching up on " + stop.getClass().getSimpleName());
				isplay = false;
				ispause = false;
				nota = 0;
				time = 0;
				timesil = 0;
				timetocada= 0;
				mute = true;
				posactual= (w*posPartitura)-avance;
				}
		});
		stage.addActor(stop);
		
		pause = new ImageButton(pauseBotonDrawable);
		pause.setSize(w*0.08f, w*0.08f);
		pause.setPosition(w*0.44f, h*0.7f);
		pause.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching down on pause" + pause.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching up on " + pause.getClass().getSimpleName());
				isplay = false;
				ispause = true;
				}
		});
		stage.addActor(pause);
		
		sonido = new ImageButton(sonidoBotonDrawable);
		sonido.setSize(w*0.08f, w*0.08f);
		sonido.setPosition(w*0.54f,  h*0.7f);
		sonido.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching down on pause" + sonido.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching up on " + sonido.getClass().getSimpleName());
				ismute = false;
			}
		});
		stage.addActor(sonido);
		
		silencio = new ImageButton(silencioBotonDrawable);
		silencio.setSize(w*0.08f, w*0.08f);
		silencio.setPosition(w*0.64f,  h*0.7f);
		silencio.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching down on silencio" + silencio.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(FibooGame.LOG, "Touching up on " + silencio.getClass().getSimpleName());
				ismute = true;
			}
		});
		stage.addActor(silencio);
		
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		batch.begin();
		//batch.draw(fondo, 0, 0, w,h);
		
		batch.draw(partitura, posactual, h*0.77f, w*3f, h*0.26f);
		
		//posPartitura-= 0.0001f;
		if(isplay) {
			if(!mute) {
				
				time += 0.1;
				if(time > tempo[nota]){
					time = 0;
					mute = true;
					nota =(nota+1)%26;
					if (nota== 0) posactual= (w*posPartitura)-avance;
					else
						posactual-= avance;
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
		batch.end();
		stage.act();
		stage.draw();
		batch.begin();
		if(tocada >= 0){
			//timetocada+= 0.1; 
			switch(tocada){ 
				case 0: batch.draw(tecla.tecla(tocada), 0, 0, w/8,h);
					break;
				case 1: batch.draw(tecla.tecla(tocada), (anchopiano/8), 0, w/8,h);
					break;
				case 2: batch.draw(tecla.tecla(tocada), (anchopiano/8)*2, 0, w/8,h);break;
				case 3: batch.draw(tecla.tecla(tocada), (anchopiano/8)*3, 0, w/8,h);break;
				case 4: batch.draw(tecla.tecla(tocada), (anchopiano/8)*4, 0, w/8,h);break;
				case 5: batch.draw(tecla.tecla(tocada), (anchopiano/8)*5, 0, w/8,h);break;
				case 6: batch.draw(tecla.tecla(tocada), (anchopiano/8)*6, 0, w/8,h);break;
				case 7: batch.draw(tecla.tecla(tocada), (anchopiano/8)*7, 0, w/8,h);break;
				default: break;
			}
		}
		/*if(timetocada>=1){
			tocada= -1;
			timetocada= 0;
		}*/
		batch.end();
		
		
		/*logotrompeta.setSize(imgWidth, imgHeight);
		logotrompeta.setPosition(Gdx.graphics.getWidth()*0.02f, 
				Gdx.graphics.getHeight()*0.83f- imgWidth);
		*/
		if ((seleccion)== 0){
				logopiano.setSize(imgWidth*1.3f, imgHeight*1.3f);
				logopiano.setPosition(w*0.11f-imgWidth, h*0.93f-imgHeight);
				logotrompeta.setSize(imgWidth, imgHeight);
				logotrompeta.setPosition(w*0.24f-imgWidth, h*0.93f-imgHeight);
				
		}
		else{
			logopiano.setSize(imgWidth, imgHeight);
			logopiano.setPosition(w*0.11f-imgWidth, h*0.93f-imgHeight);
			logotrompeta.setSize(imgWidth*1.3f, imgHeight*1.3f);
			logotrompeta.setPosition(w*0.24f-imgWidth, h*0.93f-imgHeight);
		}
		//stage.act();
		//stage.draw();
	
	}
	
	@Override
	public void dispose() {
		FibooGame.MANAGER.unloadPianoMiniGameSounds();
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
		super.dispose();
	}

	private class Teclas {
		private static final int FRAME_COLS = 8;
		Texture teclado;
		TextureRegion[] teclas;
		
		public Teclas(){
			teclado = FibooGame.MANAGER.get("pianogame/teclas.png", Texture.class);   
			TextureRegion[][] tmp = TextureRegion.split(teclado, teclado.getWidth() / FRAME_COLS, teclado.getHeight()); 
			teclas = new TextureRegion[FRAME_COLS];
			int index = 0;
			for (int i = 0; i < FRAME_COLS; i++) {
				teclas[index++] = tmp[0][i];
			}
		}
		
		public TextureRegion tecla(int n) {
			return teclas[n];
		}
	}
}
